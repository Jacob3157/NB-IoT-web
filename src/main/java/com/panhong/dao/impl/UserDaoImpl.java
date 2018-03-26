package com.panhong.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.panhong.dao.UserDao;
import com.panhong.model.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Resource
	public SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addUser(User user) {
		this.getSession().save(user);
	}

	@Override
	public void updateUser(User user) {
		this.getSession().update(user);
	}

	@Override
	public User getUserById(String Id) {
		String hql="from User where openid like :openid";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("openid", "%"+Id);
		return (User) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser(int pageNo, int pageSize) {
		String hql="from User order by followDate";
		Query query=this.getSession().createQuery(hql);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> gerAvailableUser(int pageNo, int pageSize) {
		String hql="from User where valid=0 order by followDate";
		Query query=this.getSession().createQuery(hql);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public void deleteUser(User user) {
		this.getSession().delete(user);
	}

	public List<User> getByRegisterStatue(String statue,int pageNo, int pageSize) {
		String hql="from User where code=?1 order by codeTime desc";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("1", statue);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<User> getByStatueAndRegTime(String statue, Date date,int pageNo, int pageSize) {
		String hql="from User where code=?0 and codeTime<=?1 order by codeTime desc";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", statue);
		query.setParameter("1", date);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<User> getByRegTime(Date date,int pageNo, int pageSize) {
		String hql="from User where codeTime<=?0 order by codeTime desc";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", date);
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
	public List<User> getByUserProperty(int userProperty, int pageNo,
			int pageSize) {
		String hql="from User where userProperty.userProperty=?0";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", userProperty);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<User> getByUserProperty(int userProperty) {
		String hql="from User where userProperty.userProperty=?0";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", userProperty);
		return query.list();
		
	}

	@Override
	public List<User> getByFollowTime(Date startTime, Date endTime, int pageNo,
			int pageSize) {
		String hql="from User where followDate>=?0 and followDate<=?1";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", startTime);
		query.setParameter("1", endTime);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<User> getByFollowTimeAnduserProperty(int userProperty,
			Date startTime, Date endTime, int pageNo, int pageSize) {
		String hql="from User where followDate>=?0 and followDate<=?1 and userProperty.userproperty=?2";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", startTime);
		query.setParameter("1", endTime);
		query.setParameter("2", userProperty);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<User> getByName(String name, int pageNo, int pageSize) {
		String hql="from User where name=?0";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", name);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<User> getByNickName(String nickname, int pageNo, int pageSize) {
		String hql="from User where nickname=?0";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", nickname);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<User> getByStudentId(String StudentId, int pageNo, int pageSize) {
		String hql="from User where studentID=?0";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", StudentId);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<User> getByPhone(String phone, int pageNo, int pageSize) {
		String hql="from User where telephone=?0";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", phone);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public User getByStudentId(String StudentId) {
		String hql="from User where studentID=?0";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", StudentId);
		return (User) query.uniqueResult();
	}

	@Override
	public User getByPhone(String phone) {
		String hql="from User where telephone=?0";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", phone);
		return (User) query.uniqueResult();
	}
	
	
	

}
