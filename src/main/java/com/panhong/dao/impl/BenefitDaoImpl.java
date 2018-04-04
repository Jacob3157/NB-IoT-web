package com.panhong.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.panhong.dao.BenefitDao;
import com.panhong.model.Benefit;
import com.panhong.model.User;

@Repository
public class BenefitDaoImpl implements BenefitDao {
	
	@Resource(name = "sessionFactory")
	public SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addBenefit(Benefit benefit) {
		this.getSession().save(benefit);
	}

	@Override
	public void update(Benefit benefit) {
		this.getSession().update(benefit);
	}

	@Override
	public void deleteBenefit(Benefit benefit) {
		this.getSession().delete(benefit);
	}

	@Override
	public List<Benefit> getAllBenefit(int pageNo, int pageSize) {
		String hql="from Benefit order by creatTime";
		Query query=this.getSession().createQuery(hql);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	//分页展示用户优惠券
	public List<Benefit> getBenefitsByUser(String openid,int pageNo, int pageSize) {
		String hql="from Benefit where user.openid=?1 order by creatTime";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("1", openid);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<Benefit> getBenefitsByUserAndStatus(String openid, String status) {
		String hql="from Benefit where user.openid=?1 and status=?2 order by creatTime";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("1", openid);
		query.setParameter("2", status);
		return query.list();
	}

	@Override
	public int getCount(String hql) {
		Query query=this.getSession().createQuery(hql);
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<Benefit> getBenefitsByUserAndStatus(String openid, String status,
			int pageNo, int pageSize) {
		String hql="from Benefit where user.openid=?1 and status=?2 order by creatTime";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("1", openid);
		query.setParameter("2", status);
		query.setParameter("1", openid);
		query.setParameter("2", status);
		return query.list();
	}

	@Override
	public Benefit getBenefitById(int id) {
		String hql="from Benefit where id=?1";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("1", id);
		return (Benefit) query.uniqueResult();
	}

}
