/*package com.cgutech.filesystem.dd.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cgutech.filesystem.dd.dto.DepartmentBean;
import com.cgutech.filesystem.dd.dto.UserBean;
import com.cgutech.filesystem.dd.entity.DepartmentEntity;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class DUserDaoTest {
	
	@Autowired
	private DUserDao dao;

	@Test
	public void saveDepartment(String user,String iii){
		List<DepartmentEntity> departments =new ArrayList<>();
		DepartmentEntity entity = new DepartmentEntity();
		entity.setDeid(14444);
		entity.setName("总部平台部");
		entity.setParentid(1);
		departments.add(entity);
		dao.savaDpartment(departments);
	}
		
}

*/