package com.cgutech.filesystem.dd.dao;

import java.util.List;

import com.cgutech.filesystem.dd.entity.DdUserEntity;
import com.cgutech.filesystem.dd.entity.DepartmentEntity;

public interface DUserDao {
	public  void saveDUser(List<DdUserEntity> users);
	public  void savaDpartment(List<DepartmentEntity> departments);
	public  List<String> findDuserNotInCguUser();
	public DdUserEntity findDuserByName(String name);
}

