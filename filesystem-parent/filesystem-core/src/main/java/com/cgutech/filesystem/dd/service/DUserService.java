package com.cgutech.filesystem.dd.service;

import java.util.List;

import com.cgutech.filesystem.dd.dto.DepartmentBean;
import com.cgutech.filesystem.dd.dto.UserBean;

/**
 *DUserService.java
 *@author Becan E-mail:becan@cgutech.com
 *@date 2016年9月5日 下午3:00:37
 */
public interface DUserService {
	
	public List<UserBean> getUserInfo(long departmentid,Long offset,Integer size);
	public List<DepartmentBean> getDepartmentInfo();
	
	public void saveDdUserInfo();
	public void saveDepartmentInfo();
}

