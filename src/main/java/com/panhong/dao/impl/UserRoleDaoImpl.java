package com.panhong.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.panhong.dao.UserRoleDao;
import com.panhong.model.Auth_User_Role;

@Repository
public class UserRoleDaoImpl implements UserRoleDao {
	
	@Resource(name = "sessionFactory")
	public SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int add(Auth_User_Role userRole) {
		Serializable result=this.getSession().save(userRole);
		return (Integer) result;
	}

	@Override
	public void update(Auth_User_Role userRole) {
		this.getSession().update(userRole);
	}

	@Override
	public void delete(Auth_User_Role userRole) {
		this.getSession().delete(userRole);
	}

	@Override
	public Auth_User_Role getById(int id) {
		String hql="from Auth_User_Role where id=?0";
		Query  query=this.getSession().createQuery(hql);
		query.setParameter("0", id);
		return (Auth_User_Role) query.uniqueResult();
	}

	@Override
	public List<Auth_User_Role> getUserRole(int pageNo, int pageSize) {
		String hql="from Auth_User_Role order by id";
		Query query=this.getSession().createQuery(hql);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<Auth_User_Role> getByUserId(int userId) {
		String hql="from Auth_User_Role where userId=?0";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", userId);
		return query.list();
	}

}
