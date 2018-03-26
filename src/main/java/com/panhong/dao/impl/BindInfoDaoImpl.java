package com.panhong.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import com.panhong.dao.BindInfoDao;
import com.panhong.model.BindInfo;
import com.panhong.model.Machine;
import com.panhong.model.User;

@Repository
public class BindInfoDaoImpl implements BindInfoDao {

	@Resource
	public SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void addBindInfo(BindInfo bindInfo) {
		this.getSession().save(bindInfo);
	}

	@Override
	public void updateBindInfo(BindInfo bindInfo) {
		BindInfo bindInfoOld=getBindInfoByOpenidAndDeviceid(bindInfo.getOpenid(), bindInfo.getDeviceinfo());
		bindInfoOld.setState(bindInfo.getState());
		this.getSession().update(bindInfoOld);
	}

	@Override
	public void deleteBindInfo(BindInfo bindInfo) {
		this.getSession().delete(bindInfo);
	}

	@Override
	public BindInfo getBindInfoByOpenidAndDeviceid(String openid,
			String deviceid) {
		String hql="from BindInfo bindInfo where bindInfo.wechatID.openid=? and bindInfo.machineID.machineID=?";
		Object[] values=new Object[]{openid,deviceid};
		Type[] types=new Type[]{StringType.INSTANCE,StringType.INSTANCE};
		return (BindInfo) this.getSession().createQuery(hql).setParameters(values, types).uniqueResult();
	}

	@Override
	public List<BindInfo> getBindInfoByOpenid(String openid) {
		String hql="from BindInfo where openid = ?";
		return this.getSession().createQuery(hql).setParameter(0, openid).list();
	}

	@Override
	public List<BindInfo> getBindInfoByDeviceID(String deviceid) {
		String hql="from BindInfo where deviceid = ?";
		return this.getSession().createQuery(hql).setParameter(0, deviceid).list();
	}

	@Override
	public List<BindInfo> getAllBindInfos() {
		String hql ="from BindInfo";
		return this.getSession().createQuery(hql).list();
	}

	/*@Override
	public BindInfo getBindInfoByUserAndMachine(User user, Machine machine) {
		String hql="from BindInfo where WechatID=? and machineID=?";
		Object[] values=new Object[]{user.getOpenid(),machine.getMachineID()};
		Type[] types=new Type[]{StringType.INSTANCE,StringType.INSTANCE};
		return (BindInfo) this.getSession().createQuery(hql).setParameters(values, types).uniqueResult();
	}*/

}
