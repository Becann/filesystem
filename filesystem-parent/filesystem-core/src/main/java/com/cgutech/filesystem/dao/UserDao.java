package com.cgutech.filesystem.dao;

import java.util.List;


import com.cgutech.filesystem.entity.FileEntity;
import com.cgutech.filesystem.entity.ShareFileEntity;
import com.cgutech.filesystem.entity.TempFileEntity;
import com.cgutech.filesystem.entity.UserEntity;

public interface UserDao {
	
	public UserEntity getUser(String username);
	
	public void saveFileInfo(FileEntity fileEntity);
	
	public void changePassword(String username,String password);
	
	public void addUser(UserEntity userEntity);
	
	public List<UserEntity> getUsers(String username,String role,int offset,int limit);
	
	public List<FileEntity> getFiles(String user,String filename,String before ,String after ,int offset,int limit);
	public FileEntity getFileEntityById(int id);
	
	public List<TempFileEntity> getAuditFiles(String user,String applyDate ,String downDate ,
			int auditStatus,String  downStatus,String filename,	int offset,int limit);
	public int countUser();
	public int countFile();
	public int countAuditFile();
	public void deleteUser(String name);
	
	public void updateUser(String name,String role,String status,String dd_admin);
	
	public void tempFileSaveOrUpdate(TempFileEntity tempFileEntity);
	
	public void tempFileDelete(int id);
	public TempFileEntity geTempFileEntity(int id);
	public void uploadFileDelete(int id );

	public UserEntity getUserByUUid(String uuid);
	public List<UserEntity> getAdminUsers();
	
	public void saveShareInfo(ShareFileEntity shareFileEntity);
	public List<ShareFileEntity> getShares(ShareFileEntity shareFileEntity,int limit,int offset);
	public int countShareFiles(String besharedman);
	public void shareDelete(int id);
	public ShareFileEntity getShareFileById(int id);
	public List<UserEntity> getUserNames();
	
}

