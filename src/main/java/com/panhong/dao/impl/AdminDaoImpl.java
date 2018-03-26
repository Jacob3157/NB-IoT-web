package com.panhong.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.panhong.dao.AdminDao;
import com.panhong.model.Admin;


@Repository
public class AdminDaoImpl implements AdminDao {

	@Resource
	public SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Admin getByUsername(String username) {
		String hql="from Admin where username=?";
		return (Admin) this.getSession().createQuery(hql).setParameter(0, username).uniqueResult();
	}

	@Override
	public Admin getByPhone(String phone) {
		String hql="from Admin where phone=?";
		return (Admin) this.getSession().createQuery(hql).setParameter(0, phone).uniqueResult();
	}

	@Override
	public Admin getByMail(String mail) {
		String hql="from Admin where mail=?";
		return (Admin) this.getSession().createQuery(hql).setParameter(0, mail).uniqueResult();
	}

	@Override
	public List<Admin> getAll() {
		String hql="from Admin";
		return this.getSession().createQuery(hql).list();
	}

	@Override
	public void add(Admin admin) {
		this.getSession().save(admin);
	}

	@Override
	public void delete(Admin admin) {
		this.getSession().delete(admin);
	}

	@Override
	public void update(Admin admin) {
		this.getSession().update(admin);
	}

}
