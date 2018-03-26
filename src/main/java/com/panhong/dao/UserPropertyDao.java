package com.panhong.dao;

import java.util.List;

import com.panhong.model.UserProperty;

public interface UserPropertyDao {
	
//	通过用户属性获取属性
	public UserProperty getUserPropertyById(int property);
//	通过用户属性获取属性
	public UserProperty getUserPropertyByGradeName(String gradeName);
//	添加用户属性
	public void addUserProperty(UserProperty up);
//	修改用户属性
	public void updateProperty(UserProperty up);
//	删除用户属性
	public void deleteProperty(UserProperty up);
//	获取所有用户属性
	public List<UserProperty> getAllUserProperty();
//	获取所有VIP属性
	public List<UserProperty> getVIPUserProperty(String isVip);
	
}
