package com.panhong.dao.impl;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.panhong.dao.InfoReleaseDao;
import com.panhong.model.InfoRelease;


@Repository
public class InfoReleaseDaoImpl implements InfoReleaseDao {
	
	
	@Resource(name = "sessionFactory")
	public SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}


	@Override
	public void addInfoRelease(InfoRelease info) {
		this.getSession().save(info);
	}

	@Override
	public void updateInfoRelease(InfoRelease info) {
		this.getSession().update(info);
	}

	@Override
	public void deleteInfoRelease(InfoRelease info) {
		this.getSession().delete(info);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InfoRelease> getAllinfoRelease() {
		String hql="from InfoRelease";
		return this.getSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InfoRelease> getInfoReleaseByDate(Date date) {
		String hql="from InfoRelease where InfoReleaseDate > ?";
		return this.getSession().createQuery(hql).setParameter(0, date).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InfoRelease> getInfoReleaseByPromulgator(String promulgator) {
		String hql="from InfoRelease where promulgator=?";
		return this.getSession().createQuery(hql).setParameter(0, promulgator).list();
	}

}
