package com.panhong.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.panhong.dao.OpenRecordDao;
import com.panhong.model.OpenRecord;


@Repository
public class OpenRecordDaoImpl implements OpenRecordDao{
	
	//日志显示
		private static final Log logger = LogFactory
		            .getLog(OpenRecordDaoImpl.class);
	
	
	
	@Resource(name = "sessionFactory")
	public SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addOpenRecord(OpenRecord openRecord) {
		this.getSession().save(openRecord);		
	}

	@Override
	public OpenRecord getLastOpenRecordBydeviceId(String deviceId) {
		String hql="from OpenRecord where deviceId=?0 order by date DESC";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", deviceId);
		query.setMaxResults(1);
		return (OpenRecord) query.uniqueResult();
	}

	@Override
	public int getCount(String hql) {
		Query query=this.getSession().createQuery(hql);
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	

}
