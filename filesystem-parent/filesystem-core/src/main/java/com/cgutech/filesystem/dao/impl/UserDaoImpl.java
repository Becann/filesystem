package com.cgutech.filesystem.dao.impl;



import java.sql.SQLException;

import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.cgutech.filesystem.dao.UserDao;
import com.cgutech.filesystem.entity.FileEntity;
import com.cgutech.filesystem.entity.ShareFileEntity;
import com.cgutech.filesystem.entity.TempFileEntity;
import com.cgutech.filesystem.entity.UserEntity;
import com.cgutech.filesystem.utils.MD5;

@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	HibernateTemplate ht;

	@Override
	public UserEntity getUser(String username) {
		@SuppressWarnings("unchecked")
		List<UserEntity> userEntitys= ht.find("from UserEntity where name=?",username);
		if(userEntitys==null||userEntitys.size()==0){
			return null;
		}
		return userEntitys.get(0);
	}

	@Override
	public void saveFileInfo(FileEntity fileEntity) {
		ht.saveOrUpdate(fileEntity);
		
	}

	@Override
	public void changePassword(String username, String password) {
		@SuppressWarnings("unchecked")
		List<UserEntity> userEntitys= ht.find("from UserEntity where name=?",username);
		if(userEntitys!=null&&userEntitys.size()!=0){
			UserEntity userEntity =userEntitys.get(0);
			userEntity.setPassword(MD5.encode(password));
			ht.update(userEntity);
		}
		
		
	}

	@Override
	public void addUser(UserEntity userEntity) {
		//System.out.println(userEntity.getRole_id());
		userEntity.setPassword(MD5.encode(userEntity.getPassword()));
		ht.save(userEntity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> getUsers(final String username,final String role,final int offset,final int limit) {
		return this.ht
				.executeFind(new HibernateCallback<List<UserEntity>>() {

					@Override
					public List<UserEntity> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery("from UserEntity order by name");
						if(username!=null&&role==null){
							query =session.createQuery("from UserEntity where name=?");
							query.setParameter(0, username);
						}
						if(username!=null&&role!=null){
							query =session.createQuery("from UserEntity where name=:name and role=:role");
							query.setParameter("name", username);
							query.setParameter("role", role);
						}
						if(username==null&&role!=null){
							query =session.createQuery("from UserEntity where role=:role");
							query.setParameter("role", role);
						}
						if(limit==0){
							return query.list();
						}
						query.setFirstResult(offset);
						query.setMaxResults(limit);

						return query.list();
					}

				});
	}

	@Override
	public int countUser() {
		long  count = 0;
		String hql = "select count(*) from UserEntity";
		count = (long)this.ht.find(hql).iterator().next();	
		return (int)count;
	}

	@Override
	public void deleteUser(String name) {
		UserEntity userEntity=getUser(name);
		ht.delete(userEntity);
		
	}

	@Override
	public void updateUser(String name, String role, String status,String dd_admin) {
		UserEntity userEntity=getUser(name);
		userEntity.setRole(role);
		if(role.equals("admin")){
			userEntity.setRole_id(0);
		}else if(role.equals("user")){
			userEntity.setRole_id(1);
		}
		userEntity.setStatus(status);
		userEntity.setDd_admin(dd_admin);
		ht.update(userEntity);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FileEntity> getFiles(String user,String filename,String before,String after,int offset,int limit) {
		DetachedCriteria criteria = DetachedCriteria.forClass(FileEntity.class);
		if(user!=null){
			criteria.add(Restrictions.eq("f_user", user));
		}
		if(before!=null){
			criteria.add(Restrictions.ge("ctime", before));
		}
		if(after!=null){
			criteria.add(Restrictions.le("ctime", after));
		}
		if(filename!=null){
			criteria.add(Restrictions.like("f_name", "%"+filename+"%"));
		}
		criteria.addOrder(Order.desc("ctime"));
		if(limit==0){
			return ht.findByCriteria(criteria);
		}
		return ht.findByCriteria(criteria, offset, limit);
	}

	@Override
	public int countFile() {
		long  count = 0;
		String hql = "select count(*) from FileEntity";
		count = (long)this.ht.find(hql).iterator().next();	
		return (int)count;
	}

	@Override
	public void tempFileSaveOrUpdate(TempFileEntity tempFileEntity) {
		ht.saveOrUpdate(tempFileEntity);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TempFileEntity> getAuditFiles(String user, String applyDate, String downDate, int auditStatus,
			String  downStatus, String filename, int offset, int limit) {
		
		DetachedCriteria criteria=DetachedCriteria.forClass(TempFileEntity.class);
		if(user!=null){
			criteria.add(Restrictions.eq("auser", user));
		}
		if(applyDate!=null){
			criteria.add(Restrictions.ge("atime", applyDate));
		}
		if(downDate!=null){
			criteria.add(Restrictions.ge("dtime", downDate));
		}
		if(auditStatus!=3){
			criteria.add(Restrictions.eq("auditstatus", auditStatus));
		}

		if(downStatus!=null){
			criteria.add(Restrictions.eq("dstatus", downStatus));
		}
		if(filename!=null){
			criteria.add(Restrictions.like("fname", "%"+filename+"%"));
		}
		criteria.addOrder(Order.desc("atime"));
		if(limit==0){
			return ht.findByCriteria(criteria);			
		}
		return ht.findByCriteria(criteria, offset, limit);
	}

	@Override
	public int countAuditFile() {
		long  count = 0;
		String hql = "select count(*) from TempFileEntity";
		count = (long)this.ht.find(hql).iterator().next();	
		return (int)count;
	}

	@Override
	public void tempFileDelete(int id) {
		ht.delete(ht.get(TempFileEntity.class, id));	
	}

	@Override
	public TempFileEntity geTempFileEntity(int id) {

		return ht.get(TempFileEntity.class, id);
	}

	@Override
	public void uploadFileDelete(int id) {
		ht.delete(ht.get(FileEntity.class, id));	
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserEntity getUserByUUid(String uuid) {
		String hql="from UserEntity where uuid=?";
		List<UserEntity> users = ht.find(hql, uuid);
		if(users!=null&&users.size()!=0){
			return users.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> getAdminUsers() {
		String hql="from UserEntity where name!='admin' and role_id=?";
		List<UserEntity> users=ht.find(hql,0);
		return users;
	}

	@Override
	public void saveShareInfo(ShareFileEntity shareFileEntity) {
		ht.saveOrUpdate(shareFileEntity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShareFileEntity> getShares(ShareFileEntity shareFileEntity,int limit,int offset) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ShareFileEntity.class);
		if(shareFileEntity.getBesharedman()!=null){
			criteria.add(Restrictions.eq("besharedman", shareFileEntity.getBesharedman()));
		}
		if(shareFileEntity.getShareman()!=null){
			criteria.add(Restrictions.eq("shareman", shareFileEntity.getShareman()));
		}
		if(shareFileEntity.getFilename()!=null){
			criteria.add(Restrictions.like("filename", "%"+shareFileEntity.getFilename()+"%"));
		}
		criteria.addOrder(Order.desc("sharetime"));
		if(limit==0){
			return ht.findByCriteria(criteria);
		}
		return ht.findByCriteria(criteria, offset, limit);
	}

	@Override
	public int countShareFiles(String besharedman) {
		long  count = 0;
		String hql = "select count(*) from ShareFileEntity";
		if(besharedman!=null){
			hql =hql +" where besharedman=?";
			count = (long)this.ht.find(hql,besharedman).iterator().next();	
			return (int)count;
		}
		count = (long)this.ht.find(hql).iterator().next();	
		return (int)count;
	}

	@Override
	public void shareDelete(int id) {
		ht.delete(ht.get(ShareFileEntity.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> getUserNames() {
		return ht.find("from UserEntity");
	}

	@Override
	public FileEntity getFileEntityById(int id) {
		return ht.get(FileEntity.class, id);
	}

	@Override
	public ShareFileEntity getShareFileById(int id) {
		return ht.get(ShareFileEntity.class, id);
	}
	
	
}

