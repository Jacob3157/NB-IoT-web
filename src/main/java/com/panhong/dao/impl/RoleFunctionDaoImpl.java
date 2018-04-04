package com.panhong.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.panhong.dao.RoleFunctionDao;
import com.panhong.model.Auth_Role_Function;

@Repository
public class RoleFunctionDaoImpl implements RoleFunctionDao {
	
	@Resource(name = "sessionFactory")
	public SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void add(Auth_Role_Function roleFunction) {
		this.getSession().save(roleFunction);
	}

	@Override
	public void delete(Auth_Role_Function roleFunction) {
		this.getSession().delete(roleFunction);
	}

	@Override
	public void update(Auth_Role_Function roleFunction) {
		this.getSession().update(roleFunction);
	}

	@Override
	public Auth_Role_Function getById(int id) {
		String hql="from Auth_Role_Function where id=?0";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", id);
		return (Auth_Role_Function) query.uniqueResult();
	}

	@Override
	public List<Auth_Role_Function> getByRoleId(int roleId) {
		String hql="from Auth_Role_Function where roleId=?0";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", roleId);
		return query.list();
	}

	@Override
	public void deleteByRoleId(int roleId) {
		String hql="delete Auth_Role_Function where roleId=?0";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", roleId).executeUpdate();
	}

}
