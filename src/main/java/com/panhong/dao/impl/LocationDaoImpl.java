package com.panhong.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.panhong.dao.LocationDao;
import com.panhong.model.Location;

@Repository
public class LocationDaoImpl implements LocationDao {
	
	@Resource
	public SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void add(Location location) {
		this.getSession().save(location);
	}

	@Override
	public void delete(Location location) {
		this.getSession().delete(location);
	}

	@Override
	public void update(Location location) {
		this.getSession().update(location);
	}

	@Override
	public List<Location> getBuildings(String school, String compus) {
		String hql="from Location where school=?0 and compus=?1";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", school);
		query.setParameter("1", compus);
		return query.list();
	}

}
