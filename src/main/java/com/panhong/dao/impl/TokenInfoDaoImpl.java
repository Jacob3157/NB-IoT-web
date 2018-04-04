package com.panhong.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.management.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.panhong.dao.TokenInfoDao;
import com.panhong.model.TokenInfo;

@Repository
public class TokenInfoDaoImpl implements TokenInfoDao {
	
	
	@Resource(name = "sessionFactory")
	public SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addToken(TokenInfo tokenInfo) {
		this.getSession().save(tokenInfo);
	}

	@Override
	public void deleteToken(TokenInfo tokenInfo) {
		this.getSession().delete(tokenInfo);
	}

	@Override
	public void updateToken(TokenInfo tokenInfo) {
		this.getSession().update(tokenInfo);
	}

	@Override
	public TokenInfo getLatestToken() {
		String hql="from TokenInfo order by Id desc";
		return (TokenInfo) this.getSession().createQuery(hql).setFirstResult(0).setMaxResults(1).uniqueResult();
	}

	@Override
	public List<TokenInfo> getAllToken() {
		String hql="from TokenInfo";
		return this.getSession().createQuery(hql).list();
	}

	@Override
	public TokenInfo getTokenBytoken(String token) {
		String hql="from TokenInfo where token=?";
		return (TokenInfo) this.getSession().createQuery(hql).setParameter(0, token).uniqueResult();
	}

}
