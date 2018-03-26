package com.panhong.dao;

import java.util.List;

import com.panhong.model.Auth_User_Role;

public interface UserRoleDao {
	
	public int add(Auth_User_Role userRole);
	
	public void update(Auth_User_Role userRole);
	
	public void delete(Auth_User_Role userRole);
	
	public Auth_User_Role getById(int id);
	
	public List<Auth_User_Role> getUserRole(int pageNo,int pageSize);
	
	public List<Auth_User_Role> getByUserId(int userId);

}
