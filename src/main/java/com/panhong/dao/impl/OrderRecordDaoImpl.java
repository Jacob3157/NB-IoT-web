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

import com.panhong.controller.SelectController;
import com.panhong.dao.OrderRecordDao;
import com.panhong.model.OrderRecord;

@Repository
public class OrderRecordDaoImpl implements OrderRecordDao {
	
	//日志显示
	private static final Log logger = LogFactory
	            .getLog(OrderRecordDaoImpl.class);
	
	@Resource(name = "sessionFactory")
	public SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addOrderRecord(OrderRecord orderRecord) {
		this.getSession().save(orderRecord);
	}

	@Override
	public void updateOrderRecord(OrderRecord orderReord) {
		this.getSession().update(orderReord);
	}

	@Override
	public void deleteOrderRecord(OrderRecord orderRecord) {
		this.getSession().delete(orderRecord);
	}

	@Override
	public List<OrderRecord> getOrderRecordsByUser(String wechatID,int pageNo, int pageSize) {
		String hql="from OrderRecord where wechatID.wechatID=?0 order by time_end asc";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", wechatID);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<OrderRecord> getAllOrderRecord(int pageNo, int pageSize) {
		String hql="from OrderRecord order by time_end desc";
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

	@Override
	public List<OrderRecord> getOrderRecordByTime(int pageNo, int pageSize,
			String startTime, String endTime) {
		String hql="from OrderRecord where time_end>=?0 and time_end<=?1 order by time_end desc";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", startTime);
		query.setParameter("1", endTime);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<OrderRecord> getOrderRecordByTime(String price,String startTime,
			String endTime) {
		String hql="from OrderRecord where time_end>=?0 and time_end<=?1 and total_fee=?2 order by time_end asc";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", startTime);
		query.setParameter("1", endTime);
		query.setParameter("2", price);
		return query.list();
	}

	@Override
	public OrderRecord getOrderRecordById(int id) {
		String hql="from OrderRecord where orderID=?1";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("1", id);
		return (OrderRecord) query.uniqueResult();
	}

	@Override
	public List<OrderRecord> getOrderRecordByUserProperty(int userProperty,
			String startTime, String endTime) {
		String hql="from OrderRecord where time_end>=?0 and time_end<=?1 and wechatID.userProperty.userProperty=?2 order by time_end asc";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", startTime);
		query.setParameter("1", endTime);
		query.setParameter("2", userProperty);
		return query.list();
	}

}
