package com.panhong.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.panhong.dao.WelllidDao;
import com.panhong.model.NB.Command;

@Repository
public class WelllidDaoImpl implements WelllidDao {

	@Resource(name = "NBsessionFactory")
	public SessionFactory NBsessionFactory;


	public SessionFactory getNBsessionFactory() {
		return NBsessionFactory;
	}

	public void setNBsessionFactory(SessionFactory nBsessionFactory) {
		NBsessionFactory = nBsessionFactory;
	}

	@Override
	public void updateCommand(int type, int num, String s, String sHex) {
		Command command = getCommandById(num);
		if(type == 1) {
			command.setRequest(s);
        	command.setRequestHex(sHex);
		}else if(type == 2) {
			command.setResponse(s);
        	command.setResponseHex(sHex);
		}
		Session session = NBsessionFactory.getCurrentSession();
		session.update(command);
	}

	@Override
	public void updateCommandName(int type, int num, String name) {
		Command command = getCommandById(num);
		if(type == 1) {
			command.setRequestName(name);
		}else if(type == 2) {
			command.setResponseName(name);
		}
	}

	@Override
	public List<Command> getCommandInfo() {
		Session session = NBsessionFactory.getCurrentSession();
		String hql = "SELECT * FROM command ";
		Query query;
		query = session.createSQLQuery(hql).addEntity(Command.class);
		return query.list();
	}

	@Override
	public Command getCommandById(int id) {
		Session session = NBsessionFactory.getCurrentSession();
		String hql = "SELECT * FROM command where id = ?";
		Query query;
		query = session.createSQLQuery(hql).addEntity(Command.class).setParameter(0, id);
		return (Command) query.uniqueResult();
	}

	@Override
	public void updateDelayTime(int delayTime) {
		Session session = NBsessionFactory.getCurrentSession();
		String hql = "UPDATE config SET delayTime = ? WHERE id = 1";
		Query query;
		query = session.createSQLQuery(hql).setParameter(0,delayTime);
		query.executeUpdate();
	}

	@Override
	public int getDelayTime() {
		Session session = NBsessionFactory.getCurrentSession();
		String hql = "SELECT delayTime FROM config where id = 1";
		Query query;
		query = session.createSQLQuery(hql);
		return (int) query.uniqueResult();
	}

	
	
}
