package com.panhong.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.panhong.dao.MachineDao;
import com.panhong.model.Machine;


@Repository
public class MachineDaoImpl implements MachineDao {
	
	@Resource(name = "sessionFactory")
	public SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addMachine(Machine machine) {
		this.getSession().save(machine);
	}

	@Override
	public void updateMachine(Machine machine) {
		this.getSession().update(machine);
	}

	@Override
	public void deleteMachine(Machine machine) {
		this.getSession().delete(machine);
	}

	@Override
	public Machine getMachineById(String machineID) {
		String hql="from Machine where machineID =?";
		return (Machine) this.getSession().createQuery(hql).setParameter(0, machineID).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Machine> getAllMachine(int pageNo,int pageSize) {
		String hql="from Machine order by machineName asc";
		Query query=this.getSession().createQuery(hql);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<Machine> getMachineBySchool(String school,int pageNo,int pageSize) {
		String hql="from Machine where operatinginfo.school=?0 order by machineName asc";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", school);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<Machine> getMachineByCompus(String compus) {
		String hql="from Machine where operatinginfo.compus=?";
		return this.getSession().createQuery(hql).setParameter(0, compus).list();
	}

	@Override
	public List<Machine> getMachineBySchAndCom(String school, String compus) {
		String hql="from Machine where operatinginfo.school=?0 and operatinginfo.compus=?1 order by machineName asc";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", school);
		query.setParameter("1", compus);
		return query.list();
	}

	@Override
	public Machine getMachineByMachineName(String machineName) {
		String hql="from Machine where machineName=?0";
		return (Machine) this.getSession().createQuery(hql).setParameter("0", machineName).uniqueResult();
	}

	@Override
	public int getCount(String hql) {
		Query query=this.getSession().createQuery(hql);
		return Integer.parseInt(query.uniqueResult().toString());
	}

	@Override
	public List<Machine> getMachinesBySchAndCom(String school, String compus,
			int pageNo, int pageSize) {
		String hql="from Machine where operatinginfo.school=?0 and operatinginfo.compus=?1 order by machineName asc";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", school);
		query.setParameter("1", compus);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<Machine> getMachineByStatus(String status) {
		String hql="from Machine where status=?0";
		return this.getSession().createQuery(hql).setParameter("0", status).list();
	}

	@Override
	public List<Machine> getMachineByStatus(String status, int pageNo,
			int pageSize) {
		String hql="from Machine where status=?0";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", status);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<Machine> getMachineByBuilding(String school, String compus,
			String building, int pageNo, int pageSize) {
		String hql="from Machine where operatinginfo.school=?0 and operatinginfo.compus=?1 and building=?2 order by machineName asc";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("0", school);
		query.setParameter("1", compus);
		query.setParameter("2", building);
		query.setFirstResult((pageNo - 1) * pageSize);  
        query.setMaxResults(pageSize);
		return query.list();
	}

}
