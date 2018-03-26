package com.panhong.service;

import java.util.List;

import com.panhong.model.Admin;
import com.panhong.model.Auth_User_Role;

public interface AdminService {
	
	public Admin getByUsername(String username);
	
	public Admin getByPhone(String phone);
	
	public Admin getByMail(String mail);
	
	public List<Admin> getAll();
	
	public void add(Admin admin);
	
	public void delete(Admin admin);
	
	public void update(Admin admin);
	
	public List<Auth_User_Role> getUserRoles(int pageNo,int pageSize);
	
	public List<Auth_User_Role> getUserRolesByUserId(int userId);
	
	public void addUserRoles(int userId,int[] roleIds);

}
