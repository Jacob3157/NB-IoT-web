package com.panhong.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.panhong.dao.RoleDao;
import com.panhong.model.Auth_Role;


@Repository
public class RoleDaoImpl implements RoleDao {
	
	@Resource(name = "sessionFactory")
	public SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Auth_Role getRoleById(int Id) {
		String hql="from Auth_Role where id=?0";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", Id);
		return (Auth_Role) query.uniqueResult();
	}

	@Override
	public void add(Auth_Role role) {
		this.getSession().save(role);
	}

	@Override
	public void delete(Auth_Role role) {
		this.getSession().delete(role);
	}

	@Override
	public void update(Auth_Role role) {
		this.getSession().update(role);
	}

	@Override
	public List<Auth_Role> getRoles(Collection<String> names) {
		String hql="from Auth_Role where name IN (:nameList)";
		Query query=this.getSession().createQuery(hql);
		query.setParameterList("nameList", names);
		return query.list();
	}

	@Override
	public List<Auth_Role> getRoles(int pageNo, int pageSize) {
		String hql="from Auth_Role order by id";
		Query query=this.getSession().createQuery(hql);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public Auth_Role getRoleByName(String name) {
		String hql="from Auth_Role where name=?0 ";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", name);
		return (Auth_Role) query.uniqueResult();
	}

}
