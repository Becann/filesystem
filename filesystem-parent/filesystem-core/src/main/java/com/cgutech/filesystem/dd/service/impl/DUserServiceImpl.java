package com.cgutech.filesystem.dd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgutech.filesystem.dd.dao.DUserDao;
import com.cgutech.filesystem.dd.dto.DepartmentBean;
import com.cgutech.filesystem.dd.dto.ResponseBean;
import com.cgutech.filesystem.dd.dto.UserBean;
import com.cgutech.filesystem.dd.entity.DdUserEntity;
import com.cgutech.filesystem.dd.entity.DepartmentEntity;
import com.cgutech.filesystem.dd.service.DUserService;
import com.cgutech.filesystem.dd.utils.AccessTokenUtil;
import com.cgutech.filesystem.utils.HttpUtil;
import com.cgutech.filesystem.utils.ReadProperties;
import com.google.gson.Gson;

/**
 * DUserServiceImpl.java
 * 
 * @author Becan E-mail:becan@cgutech.com
 * @date 2016年9月5日 下午3:05:31
 */
@Service
public class DUserServiceImpl implements DUserService {
	private String path = ReadProperties.read("ddapi_url");
	@Autowired
	private DUserDao dUserDao;
	private String ACCESS_TOKEN = AccessTokenUtil.getAccessToken();

	/**
	 * 根据部门ID获取用户详情
	 */
	@Override
	public List<UserBean> getUserInfo(long departmentid, Long offset, Integer size) {
		String upath = null;
		if (offset == null && size == null) {
			upath = path + "user/list?access_token=" + ACCESS_TOKEN + "&department_id=" + departmentid;
		} else if (offset != null && size != null) {
			upath = path + "user/list?access_token=" + ACCESS_TOKEN + "&department_id=" + departmentid + "&offset="
					+ offset + "&size=" + size;
		} else {
			return null;
		}
		return commonGet(upath).getUserlist();
	}

	/**
	 * 获取部门列表
	 */
	@Override
	public List<DepartmentBean> getDepartmentInfo() {
		// TODO Auto-generated method stub
		String dpath = path + "department/list?access_token=" + ACCESS_TOKEN;
		return commonGet(dpath).getDepartment();
	}

	@Override
	public void saveDdUserInfo() {
		List<DdUserEntity> userEntities = new ArrayList<>();
		List<UserBean> userBeans = new ArrayList<>();
		
		// 获取部门列表
		List<DepartmentBean> departmentBeans = getDepartmentInfo();
		if (departmentBeans != null && departmentBeans.size() != 0) {
			for (DepartmentBean departmentBean : departmentBeans) {
				userBeans = getUserInfo(departmentBean.getId(), null, null);
				if (userBeans != null && userBeans.size() != 0) {
					for (UserBean userbean : userBeans) {
						String department = null;
						DdUserEntity userEntity = new DdUserEntity();
						userEntity.setDdId(userbean.getDingId());
						if (userbean.getDepartment().length != 0) {
							for (long l : userbean.getDepartment()) {
								if (department == null) {
									department = String.valueOf(l);
								} else {
									department = department + "," + String.valueOf(l);
								}
							}
						}
						//System.out.println("department----------------->"+department);
						userEntity.setDepartment(department);
						userEntity.setUname(userbean.getName());
						userEntity.setUserid(userbean.getUserid());
						userEntities.add(userEntity);
					}
				}
			}
		}
		userEntities=removeDouble(userEntities);
		List<DdUserEntity> users = new ArrayList<>();
		// 持久化用户信息
		for (DdUserEntity user : userEntities) {
			if(dUserDao.findDuserByName(user.getUname())!=null){
				users.add(dUserDao.findDuserByName(user.getUname()));
			}else{
				users.add(user);
			}
		}
		dUserDao.saveDUser(users);

	}

	
	@Override
	public void saveDepartmentInfo() {
		// TODO Auto-generated method stub
		List<DepartmentEntity> departments =new ArrayList<DepartmentEntity>();
		List<DepartmentBean> beans = getDepartmentInfo();
		if(beans!=null&&beans.size()!=0){
			for (DepartmentBean departmentBean : beans) {
				DepartmentEntity entity = new DepartmentEntity();
				entity.setDeid(departmentBean.getId());
				entity.setName(departmentBean.getName());
				//System.out.println(departmentBean.getName());
				entity.setParentid(departmentBean.getParentid());
				departments.add(entity);
				
			}
		}
		dUserDao.savaDpartment(departments);
	}
	
	
	
	
	
	
	
	
	public ResponseBean commonGet(String url) {
		String res = HttpUtil.doGet(url);
		if (res.equals("error") || !res.startsWith("{")) {
			return null;
		}
		Gson gson = new Gson();
		ResponseBean responseBean = gson.fromJson(res, ResponseBean.class);
		return responseBean;
	}
	
	public List<DdUserEntity> removeDouble(List<DdUserEntity> userEntities){
		DdUserEntity dEntity = null;
		// 去除重复的用户信息
		if (userEntities.size() != 0) {
			for (int i = 0; i < userEntities.size(); i++) {
				dEntity = userEntities.get(i);
				
				for (int j = i + 1; j < userEntities.size(); j++) {
					if (userEntities.get(j).equals(dEntity)) {
						/*System.out.println(dEntity.getUname()+"--i--"+dEntity.getUserid());
						System.out.println(userEntities.get(j).getUname()+"--j--"+userEntities.get(j).getUserid());*/
						userEntities.remove(j);
						j--;
					}
				}
			}
		}
		return userEntities;
	}

}
