package com.panhong.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.panhong.dao.UserPropertyDao;
import com.panhong.model.UserProperty;


@Repository
public class UserPropertyDaoImpl implements UserPropertyDao {
	
	@Resource
	public SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public UserProperty getUserPropertyById(int property) {
		String hql="from UserProperty where id=?";
		return (UserProperty) this.getSession().createQuery(hql).setParameter(0, property).uniqueResult();
	}

	@Override
	public void addUserProperty(UserProperty up) {
		this.getSession().save(up);
	}

	@Override
	public void updateProperty(UserProperty up) {
		this.getSession().update(up);
	}

	@Override
	public void deleteProperty(UserProperty up) {

		this.getSession().delete(up);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserProperty> getAllUserProperty() {
		String hql="from UserProperty";
		return this.getSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserProperty> getVIPUserProperty(String isVip) {
		String hql="from Userproperty where isVIP=?";
		return this.getSession().createQuery(hql).setParameter(0, isVip).list();
	}

	@Override
	public UserProperty getUserPropertyByGradeName(String gradeName) {
		String hql="from UserProperty where gradeName=?";
		return (UserProperty) this.getSession().createQuery(hql).setParameter(0, gradeName).uniqueResult();
	}

}
