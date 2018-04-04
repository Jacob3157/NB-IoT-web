package com.panhong.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.panhong.dao.MaintenanceRecordDao;
import com.panhong.model.MaintenanceRecord;

@Repository
public class MaintenanceRecordDaoImpl implements MaintenanceRecordDao {
	
	@Resource(name = "sessionFactory")
	public SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addMaintenanceRecord(MaintenanceRecord maintenanceReord) {
		this.getSession().save(maintenanceReord);
	}

	@Override
	public void updateMaintenanceRecord(MaintenanceRecord maintenanceRecord) {
		this.getSession().update(maintenanceRecord);
	}

	@Override
	public void deleteMaintenanceRecord(MaintenanceRecord maintenanceRecord) {
		this.getSession().delete(maintenanceRecord);
	}

	@Override
	public List<MaintenanceRecord> getMaintenanceRecordByMachine(String machineID,int pageNo, int pageSize) {
		String hql="from MaintenanceRecord where machineID.machineName=?0 order by maintenanceDate";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", machineID);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<MaintenanceRecord> getMaintenanceRecordByOperator(String wechatID,int pageNo, int pageSize) {
		String hql="from MaintenanceRecord where maintenanceOperator.wechatID=?0 order by maintenanceDate";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", wechatID);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<MaintenanceRecord> getMaintenanceRecordByDate(Date date,int pageNo, int pageSize) {
		String hql="from MaintenanceRecord where maintenanceDate > ?0 order by maintenanceDate";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", date);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<MaintenanceRecord> getAllMaintenanceRecord(int pageNo, int pageSize) {
		String hql="from MaintenanceRecord order by maintenanceDate";
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
