package com.panhong.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.panhong.dao.MaintenanceTypeDao;
import com.panhong.model.MaintenanceType;

@Repository
public class MaintenanceTypeDaoImpl implements MaintenanceTypeDao {
	
	@Resource(name = "sessionFactory")
	public SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addMaintenanceType(MaintenanceType maintenanceType) {
		this.getSession().save(maintenanceType);
	}

	@Override
	public void updateMaintenanceType(MaintenanceType maintenanceType) {
		this.getSession().update(maintenanceType);
	}

	@Override
	public void deleteMaintenanceType(MaintenanceType maintenanceType) {
		this.getSession().delete(maintenanceType);
	}

	@Override
	public MaintenanceType getMaintenanceTypeByName(String name) {
		String hql="from MaintenanceType where maintenanceName=?";
		return (MaintenanceType) this.getSession().createQuery(hql).setParameter(0, name).uniqueResult();
	}

	@Override
	public List<MaintenanceType> getAllMaintenanceType(int pageNo, int pageSize) {
		String hql="from MaintenanceType order by maintenanceName";
		Query query=this.getSession().createQuery(hql);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public int getCount(String hql) {
		Query query=this.getSession().createQuery(hql);
		return Integer.parseInt(query.uniqueResult().toString());
	}

}
