package com.panhong.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.panhong.dao.ServiceTypeDao;
import com.panhong.model.MaintenanceType;
import com.panhong.model.ServiceType;

@Repository
public class ServiceTypeDaoImpl implements ServiceTypeDao {
	
	@Resource
	public SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addServiceType(ServiceType serviceType) {
		this.getSession().save(serviceType);
	}

	@Override
	public void updateServiceType(ServiceType serviceType) {
		this.getSession().update(serviceType);
	}

	@Override
	public void deleteServiceType(ServiceType serviceType) {
		this.getSession().delete(serviceType);
	}

	@Override
	public ServiceType getServiceTypeByName(String name) {
		String hql="from ServiceType where serviceName=?0";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", name);
		return (ServiceType) query.uniqueResult();
	}

	@Override
	public List<ServiceType> getAllServiceType(int pageNo, int pageSize) {
		String hql="from ServiceType order by serviceName";
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
