/*package com.cgutech.filesystem.dd.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cgutech.filesystem.dd.dto.DepartmentBean;
import com.cgutech.filesystem.dd.dto.UserBean;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class DUserServiceTest {
	
	@Autowired
	private DUserService duserService;
	
	@Test
	public void getDepartmentTest(){
		List<DepartmentBean> departmentBeans = duserService.getDepartmentInfo();
		for (DepartmentBean departmentBean : departmentBeans){
			System.out.println(departmentBean.getId());
		}
		//System.out.println(duserService.getDepartmentInfo().size());
	}
	
	@Test
	public void getUserInfoTest(){
		List<UserBean> userBeans = new ArrayList<>();
		List<DepartmentBean> departmentBeans = duserService.getDepartmentInfo();
		if (departmentBeans != null && departmentBeans.size() != 0) {
			for (DepartmentBean departmentBean : departmentBeans) {
				userBeans = duserService.getUserInfo(departmentBean.getId(), null, null);
				if (userBeans != null && userBeans.size() != 0) {
					for (UserBean userbean : userBeans) {
						System.out.println(userbean.getName()+"---"+departmentBean.getId());
	}}}}
	}
		
	
	@Test
	public void saveDdUserInfoTest(){
		duserService.saveDdUserInfo();
	}
	
	@Test
	public void saveDepartmentInfo(){
		duserService.saveDepartmentInfo();
	}
}

*/