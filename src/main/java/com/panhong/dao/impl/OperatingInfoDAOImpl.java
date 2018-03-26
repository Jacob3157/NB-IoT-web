package com.panhong.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import com.panhong.dao.OperatingInfoDAO;
import com.panhong.model.OperatingInfo;

@Repository
public class OperatingInfoDAOImpl implements OperatingInfoDAO {
	
	@Resource
	public SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addOperatingInfo(OperatingInfo opratingInfo) {
		this.getSession().save(opratingInfo);
	}

	@Override
	public void updateOperatingInfo(OperatingInfo opratingInfo) {
		this.getSession().merge(opratingInfo);

	}

	@Override
	public void deleteOperatingInfo(OperatingInfo opratingInfo) {
		this.getSession().delete(opratingInfo);

	}

	@Override
	public List<OperatingInfo> getOperatingInfoBySchool(String school) {
		String hql="from OperatingInfo where school = ?";
		return this.getSession().createQuery(hql).setParameter(0, school).list();
	}

	@Override
	public List<OperatingInfo> getOperatingInfoByCompus(String compus) {
		String hql="from OperatingInfo where compus=?";
		return this.getSession().createQuery(hql).setParameter(0, compus).list();
	}

	@Override
	public OperatingInfo getOperatingInfoBySchoolAndCompus(String school,
			String compus) {
		String hql="from OperatingInfo where school=?0 and compus=?1";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", school);
		query.setParameter("1", compus);
		return (OperatingInfo) query.uniqueResult();
	}

	@Override
	public List<OperatingInfo> getAllOperatingInfo() {
		String hql="from OperatingInfo";
		return this.getSession().createQuery(hql).list();
	}

	@Override
	public int getCount(String hql) {
		Query query=this.getSession().createQuery(hql);
		return Integer.parseInt(query.uniqueResult().toString());
	}

}
