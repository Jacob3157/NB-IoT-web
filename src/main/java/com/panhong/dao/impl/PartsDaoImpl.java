package com.panhong.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.panhong.dao.PartsDao;
import com.panhong.model.Parts;

@Repository
public class PartsDaoImpl implements PartsDao {
	
	@Resource
	public SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addParts(Parts parts) {
		this.getSession().save(parts);
	}

	@Override
	public void updateParts(Parts parts) {
		this.getSession().update(parts);
	}

	@Override
	public Parts getPartsById(String Id) {
		String hql="from Parts where partsId=?0";
		return (Parts) this.getSession().createQuery(hql).setParameter("0", Id).uniqueResult();
	}
	
	@Override
	public Parts getPartsByName(String name) {
		String hql="from Parts where name=?0";
		return (Parts) this.getSession().createQuery(hql).setParameter("0", name).uniqueResult();
	}

	@Override
	public List<Parts> getAllParts(int pageNo, int pageSize) {
		String hql="from Parts order by partsId asc";
		Query query=this.getSession().createQuery(hql);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public void deleteParts(Parts parts) {
		this.getSession().delete(parts);
	}
	
	@Override
	public int getCount(String hql) {
		Query query=this.getSession().createQuery(hql);
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<Parts> getAllParts() {
		String hql="from Parts order by partsId asc";
		Query query=this.getSession().createQuery(hql);
		return query.list();
	}

}
