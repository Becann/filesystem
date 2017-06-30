package com.cgutech.filesystem.service;


import java.util.List;

import com.cgutech.filesystem.entity.FileEntity;
import com.cgutech.filesystem.entity.ShareFileEntity;
import com.cgutech.filesystem.entity.TempFileEntity;
import com.cgutech.filesystem.entity.UserEntity;
import com.cgutech.filesystem.utils.Page;

public interface UserService {
	
	public UserEntity getUser(String username);
	public String getPassword(String username);
	public void saveFileInfo(FileEntity f);
	public void changePassword(String username,String password);
	public void createUser(UserEntity userEntity);
	public Page getUsers(String name,String role,int currentPage,int pageNum);
	public Page getFiles(String user,String filename,String before ,String after ,int currentPage,int pageNum);
	public FileEntity getFileById(int id);
	public void deleteUser(String name);
	public void updateUser(String name,String role,String status,String dd_admin);
	public void tempFileSaveOrUpdate(TempFileEntity tempFileEntity);
	public String getUidByName(String name);
	public Page getAuditFiles(String user,String applyDate ,String downDate ,
			int auditStatus,String  downStatus,String fielname,int currentPage,int pageNum);
	public void tempFileDelete(int id);
	public TempFileEntity getTempFile(int id);
	public void uploadDelete(int id);
	public UserEntity getUserByUUid(String uuid);
	
	public List<UserEntity> getAdminUsers();
	
	public void saveShareFile(String filename,String shareman,String beshareman,String filepath);
	
	public Page getShareList(ShareFileEntity shareFileEntity,int currentPage,int pageNum);
	
	public void shareDelete(int id);
	
	public ShareFileEntity getShareFileEntityById(int id);
}

