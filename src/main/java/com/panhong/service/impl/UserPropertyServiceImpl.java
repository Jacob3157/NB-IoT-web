package com.panhong.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.panhong.dao.UserPropertyDao;
import com.panhong.model.UserProperty;
import com.panhong.service.UserPropertyService;


@Service("userPropertyService")
public class UserPropertyServiceImpl implements UserPropertyService {
	
	@Resource
	private UserPropertyDao userPropertyDao;

	public UserPropertyDao getUserPropertyDao() {
		return userPropertyDao;
	}

	public void setUserPropertyDao(UserPropertyDao userPropertyDao) {
		this.userPropertyDao = userPropertyDao;
	}

	@Override
	public UserProperty getUserPropertyById(int property) {

		return userPropertyDao.getUserPropertyById(property);
	}

	@Override
	public void addUserProperty(UserProperty up) {
		userPropertyDao.addUserProperty(up);
	}

	@Override
	public void updateProperty(UserProperty up) {
		userPropertyDao.updateProperty(up);
	}

	@Override
	public void deleteProperty(UserProperty up) {
		userPropertyDao.deleteProperty(up);
	}

	@Override
	public List<UserProperty> getAllUserProperty() {
		return userPropertyDao.getAllUserProperty();
	}

	@Override
	public List<UserProperty> getVIPUserProperty(String isVip) {
		return userPropertyDao.getVIPUserProperty(isVip);
	}

	@Override
	public UserProperty getUserPropertyByGradeName(String gradeName) {
		return userPropertyDao.getUserPropertyByGradeName(gradeName);
	}

}
