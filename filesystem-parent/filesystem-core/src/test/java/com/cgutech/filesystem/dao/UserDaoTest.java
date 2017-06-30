package com.cgutech.filesystem.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cgutech.filesystem.entity.FileEntity;





@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class UserDaoTest {
	
	@Autowired
	private UserDao dao;

	@Test
	public void getFilesTest(){
		/*List<FileEntity> files=dao.getFiles("sea", null, null, 0, 10);
		System.out.println(files);*/
	}
		
}

