package com.cgutech.filesystem.dd.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.cgutech.filesystem.dd.dao.DUserDao;
import com.cgutech.filesystem.dd.entity.DdUserEntity;
import com.cgutech.filesystem.dd.entity.DepartmentEntity;

@Service
public class DUserDaoImpl implements DUserDao {

	@Autowired
	HibernateTemplate ht;
	@Override
	public void saveDUser(List<DdUserEntity> users) {
		// TODO Auto-generated method stub
		ht.saveOrUpdateAll(users);
	}
	@Override
	public void savaDpartment(List<DepartmentEntity> departments) {
		// TODO Auto-generated method stub
		ht.saveOrUpdateAll(departments);
	}
	/**
	 * 查找钉钉中新用户，未建立虚拟桌面账户的用户
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findDuserNotInCguUser() {
		
		return this.ht
				.executeFind(new HibernateCallback<List<DdUserEntity>>() {

					@Override
					public List<DdUserEntity> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.
								createQuery("select uname from DdUserEntity where "
										+ "uname not in(select u.name from UserEntity as u)");
						return query.list();
					}

				});
	}
	@Override
	public DdUserEntity findDuserByName(String name) {
		@SuppressWarnings("unchecked")
		List<DdUserEntity> userEntities=ht.find("from DdUserEntity where uname=?",name);
		if(userEntities!=null&&userEntities.size()!=0){
			return userEntities.get(0);
		}
		return null;
	}

}

