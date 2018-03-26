package com.panhong.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.panhong.dao.FunctionDao;
import com.panhong.model.Auth_Function;

@Repository
public class FunctionDaoImpl implements FunctionDao {
	
	@Resource
	public SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int add(Auth_Function function) {
		Serializable result=this.getSession().save(function);
		return (Integer) result;
	}

	@Override
	public void delete(Auth_Function function) {
		this.getSession().delete(function);
	}

	@Override
	public void update(Auth_Function function) {
		this.getSession().update(function);
	}

	@Override
	/**
	 * 根据父节点分页查询
	 */
	public List<Auth_Function> getFunctions(int pageNo, int pageSize,int parentId) {
		String hql="from Auth_Function where parentId=?0 order by id";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0",parentId);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<Auth_Function> getAllFunctions() {
		String hql="from Auth_Function";
		return this.getSession().createQuery(hql).list();
	}

	@Override
	public Auth_Function getFunctionByName(String name) {
		String hql="from Auth_Function where name=?0";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0",name);
		return (Auth_Function) query.uniqueResult();
	}

}
