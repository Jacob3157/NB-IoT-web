package com.panhong.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.panhong.dao.SystemRecordDao;
import com.panhong.model.SystemRecord;

@Repository
public class SystemRecordDaoImpl implements SystemRecordDao {
	
	@Resource(name = "sessionFactory")
	public SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addSystemRecord(SystemRecord systemRecord) {
		this.getSession().save(systemRecord);
	}

	@Override
	public void updateSystemRecord(SystemRecord systemRecord) {
		this.getSession().update(systemRecord);
		
	}

	@Override
	public void deleteSystemRecord(SystemRecord systemRecord) {
		this.getSession().delete(systemRecord);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SystemRecord> getSystemRecordByResponsibleWorker(String name) {
		String hql="from SystemRecord where responsibleWorker=?";
		return this.getSession().createQuery(hql).setParameter(0, name).list();
	}

}
