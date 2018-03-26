package com.panhong.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.panhong.dao.TicketInfoDao;
import com.panhong.model.TicketInfo;

@Repository
public class TicketInfoDaoImpl implements TicketInfoDao{
	
	@Resource
	public SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addTicket(TicketInfo ticketInfo) {
		this.getSession().save(ticketInfo);
	}

	@Override
	public void deleteTicket(TicketInfo ticketInfo) {
		this.getSession().delete(ticketInfo);
	}

	@Override
	public void updateTicket(TicketInfo ticketInfo) {
		this.getSession().update(ticketInfo);
	}

	@Override
	public TicketInfo getLatestTicket() {
		String hql="from TicketInfo order by Id desc";
		return (TicketInfo) this.getSession().createQuery(hql).setFirstResult(0).setMaxResults(1).uniqueResult();
	}

	@Override
	public TicketInfo getTicketByticket(String ticket) {
		String hql="from TicketInfo where token=?";
		return (TicketInfo) this.getSession().createQuery(hql).setParameter(0, ticket).uniqueResult();
	}

	@Override
	public List<TicketInfo> getAllTicket() {
		String hql="from TicketInfo";
		return this.getSession().createQuery(hql).list();
	}

}
