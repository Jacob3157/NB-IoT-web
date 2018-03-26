package com.panhong.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.panhong.dao.BindInfoDao;
import com.panhong.model.BindInfo;
import com.panhong.service.BindInfoService;

@Service("bindInfoService")
public class BindInfoServiceImpl implements BindInfoService {
	
	@Resource
	private BindInfoDao bindInfoDao;

	@Override
	public void addBindInfo(BindInfo bindInfo) {
		bindInfoDao.addBindInfo(bindInfo);
	}

	@Override
	public void updateBindInfo(BindInfo bindInfo) {
		bindInfoDao.updateBindInfo(bindInfo);
	}

	@Override
	public void deleteBindInfo(BindInfo bindInfo) {
		bindInfoDao.deleteBindInfo(bindInfo);
	}

	public BindInfoDao getBindInfoDao() {
		return bindInfoDao;
	}

	public void setBindInfoDao(BindInfoDao bindInfoDao) {
		this.bindInfoDao = bindInfoDao;
	}

	@Override
	public BindInfo getBindInfoByOpenidAndDeviceID(String openid,
			String deviceid) {
		return bindInfoDao.getBindInfoByOpenidAndDeviceid(openid, deviceid);
	}

	@Override
	public List<BindInfo> getBindInfoByOpenid(String openid) {
		return bindInfoDao.getBindInfoByOpenid(openid);
	}

	@Override
	public List<BindInfo> getBindInfoByDeviceID(String deviceid) {
		return bindInfoDao.getBindInfoByDeviceID(deviceid);
	}

	@Override
	public List<BindInfo> getAllBindInfos() {
		return bindInfoDao.getAllBindInfos();
	}

}
