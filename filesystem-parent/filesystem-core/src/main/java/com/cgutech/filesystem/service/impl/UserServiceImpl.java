package com.cgutech.filesystem.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgutech.filesystem.controller.UserController;
import com.cgutech.filesystem.dao.UserDao;
import com.cgutech.filesystem.dd.dao.DUserDao;
import com.cgutech.filesystem.entity.FileEntity;
import com.cgutech.filesystem.entity.ShareFileEntity;
import com.cgutech.filesystem.entity.TempFileEntity;
import com.cgutech.filesystem.entity.UserEntity;
import com.cgutech.filesystem.service.UserService;
import com.cgutech.filesystem.utils.Page;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private DUserDao dUserDao;
	private Logger log= LoggerFactory.getLogger(UserController.class);
	@Override
	public UserEntity getUser(String username) {
		
		return userDao.getUser(username);
	}
	@Override
	public String getPassword(String username) {
		if(userDao.getUser(username)==null){
			return "";
		}
		return userDao.getUser(username).getPassword();
	}
	@Override
	public void saveFileInfo(FileEntity f) {
		log.info("上传文件时间："+f.getCtime());
		userDao.saveFileInfo(f);
	}
	@Override
	public void changePassword(String username, String password) {
		//ExcuteCommand.modifyUserPassword(username, password);
		userDao.changePassword(username, password);
		
	}
	@Override
	public void createUser(UserEntity userEntity) {
		userEntity.setUuid(UUID.randomUUID().toString().substring(0, 16));
			/*String res=null;
			res=ExcuteCommand.addUser(userEntity.getName(), userEntity.getPassword());
			log.info("创建windows普通用户结果："+res);
			if(userEntity.getRole_id()==0){
				res=ExcuteCommand.toAdministrator(userEntity.getName());
				log.info("提升管理员执行结果："+res);
			}
			if(userEntity.getStatus().equals("INACTIVE")){
				res=ExcuteCommand.disableUser(userEntity.getName());
				log.info("禁用用户执行结果："+res);
			}*/
			userDao.addUser(userEntity);
	}
	@Override
	public Page getUsers(String name,String role, int currentPage, int pageNum) {
		//System.out.println(pageNum);
		int offset = (currentPage - 1) * pageNum;
		List<UserEntity> pageconfig =userDao.getUsers(name,role,offset, pageNum);
		List<UserEntity> users =userDao.getUsers(name,role,0, 0);
		int count =users==null?0:users.size();
		//int count=userDao.countUser();
		Page page = new Page(currentPage, count, pageNum);
		page.setTotalnum(count);
		
		page.setList(pageconfig);
		return page;
		
	}
	@Override
	public void deleteUser(String name) {
		//ExcuteCommand.deleteUser(name);
		userDao.deleteUser(name);
	}
	@Override
	public void updateUser(String name,String role,String status,String dd_admin) {
		UserEntity userEntity=userDao.getUser(name);
	/*	if(userEntity.getRole_id()==1&&role.equals("admin")){
			ExcuteCommand.toAdministrator(name);
		}
		if(userEntity.getRole_id()==0&&role.equals("user")){
			ExcuteCommand.toUser(name);
		}
		if(userEntity.getStatus().equals("ACTIVE")&&status.equals("INACTIVE")){
			ExcuteCommand.disableUser(name);
		}
		if(userEntity.getStatus().equals("INACTIVE")&&status.equals("ACTIVE")){
			ExcuteCommand.activeUser(name);
		}*/
		if(!(userEntity.getRole().equals(role)&&userEntity.getStatus().equals(status)
				&&userEntity.getDd_admin().equals(dd_admin))){
			userDao.updateUser(name, role, status,dd_admin);
		}
	}
	@Override
	public Page getFiles(String user,String filename, String before, String after, int currentPage, int pageNum) {
		int offset = (currentPage - 1) * pageNum;
		List<FileEntity> pageconfig =userDao.getFiles(user,filename, before, after, offset, pageNum);
		List<FileEntity> condationFile=userDao.getFiles(user,filename, before, after, 0, 0);
		int count =condationFile==null?0:condationFile.size();
		//int count=userDao.countFile();
		Page page = new Page(currentPage, count, pageNum);
		page.setTotalnum(count);
		
		page.setList(pageconfig);
		return page;
	}
	@Override
	public void tempFileSaveOrUpdate(TempFileEntity tempFileEntity) {
		userDao.tempFileSaveOrUpdate(tempFileEntity);
		
	}
	@Override
	public String getUidByName(String name) {
		return dUserDao.findDuserByName(name).getUserid();
	}
	@Override
	public Page getAuditFiles(String user, String applyDate, String downDate, int auditStatus, String downStatus,
			String fielname, int currentPage, int pageNum) {
		int offset = (currentPage - 1) * pageNum;
		List<TempFileEntity> pageconfig =userDao.getAuditFiles(user, applyDate, downDate,auditStatus,downStatus,
				fielname, offset, pageNum);
		List<TempFileEntity> temps =userDao.getAuditFiles(user, applyDate, downDate,auditStatus,downStatus,
				fielname, 0, 0);
		int count = temps==null?0:temps.size();
		//int count=userDao.countAuditFile();
		Page page = new Page(currentPage, count, pageNum);
		page.setTotalnum(count);
		
		page.setList(pageconfig);
		return page;
	}
	@Override
	public void tempFileDelete(int id) {
		TempFileEntity tempFileEntity=userDao.geTempFileEntity(id);
		//删除数据库数据
		userDao.tempFileDelete(id);
		//删除临时文件
		log.info("tempfilepath"+tempFileEntity.getPath());
		File file= new File(tempFileEntity.getPath());
		if(file.exists()){
			file.delete();
		}
	}
	@Override
	public TempFileEntity getTempFile(int id) {
		return userDao.geTempFileEntity(id);
		
	}
	@Override
	public void uploadDelete(int id) {
		// TODO Auto-generated method stub
		userDao.uploadFileDelete(id);
	}
	@Override
	public UserEntity getUserByUUid(String uuid) {
		// TODO Auto-generated method stub
		return userDao.getUserByUUid(uuid);
	}
	@Override
	public List<UserEntity> getAdminUsers() {
		// TODO Auto-generated method stub
		return userDao.getAdminUsers();
	}
	@Override
	public void saveShareFile(String filename, String shareman, String besharedman,String filepath) {
		ShareFileEntity shareFileEntity = new ShareFileEntity();
		shareFileEntity.setBesharedman(besharedman);
		shareFileEntity.setFilename(filename);
		shareFileEntity.setShareman(shareman);
		shareFileEntity.setFilepath(filepath);
		shareFileEntity.setSharetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
		userDao.saveShareInfo(shareFileEntity);
		
	}
	@Override
	public Page getShareList(ShareFileEntity shareFileEntity, int currentPage, int pageNum) {
		int offset = (currentPage - 1) * pageNum;
		List<ShareFileEntity> pageconfig =userDao.getShares(shareFileEntity, pageNum, offset);
		List<ShareFileEntity> shares =userDao.getShares(shareFileEntity, 0, 0);
		int count=shares==null?0:shares.size();
		//int count=userDao.countShareFiles(shareFileEntity.getBesharedman());
		Page page = new Page(currentPage, pageconfig.size(), pageNum);
		page.setTotalnum(count);
		page.setList(pageconfig);
		return page;
	}
	@Override
	public void shareDelete(int id) {
	
		userDao.shareDelete(id);
	}
	@Override
	public FileEntity getFileById(int id) {
		return userDao.getFileEntityById(id);
	}
	@Override
	public ShareFileEntity getShareFileEntityById(int id) {
		return userDao.getShareFileById(id);
	}

}

