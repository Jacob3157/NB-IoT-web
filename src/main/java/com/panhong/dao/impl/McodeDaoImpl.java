package com.panhong.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.panhong.dao.McodeDao;
import com.panhong.model.Mcode;
import com.panhong.model.TicketInfo;

@Repository
public class McodeDaoImpl implements McodeDao{
	
	@Resource
	public SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public String addMcode(Mcode mcode) {
		Serializable result= this.getSession().save(mcode);
		if(result!=null) return "success";
		else return "failure";
	}

	@Override
	public void deleteMcode(Mcode mcode) {
		this.getSession().delete(mcode);
	}

	@Override
	public void updateMcode(Mcode mcode) {
		this.getSession().update(mcode);
	}

	@Override
	public Mcode getMcodeBycode(String mcode) {
		String hql="from Mcode where mcode=?";
		return (Mcode) this.getSession().createQuery(hql).setParameter(0, mcode).uniqueResult();
	}

	@Override
	public List<Mcode> getAllMcode() {
		String hql="from Mcode";
		return this.getSession().createQuery(hql).list();
	}

	@Override
	public Mcode getLatestMcode() {
		String hql="from Mcode order by Id desc";
		return (Mcode) this.getSession().createQuery(hql).setFirstResult(0).setMaxResults(1).uniqueResult();
	}

}
