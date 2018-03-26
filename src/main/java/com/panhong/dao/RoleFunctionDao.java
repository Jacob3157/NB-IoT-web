package com.panhong.dao;

import java.util.List;

import com.panhong.model.Auth_Role_Function;


public interface RoleFunctionDao {
	
	public void add(Auth_Role_Function roleFunction);
	
	public void delete(Auth_Role_Function roleFunction);
	
	public void update(Auth_Role_Function roleFunction);
	
	public Auth_Role_Function getById(int id);
	
	public List<Auth_Role_Function> getByRoleId(int roleId);
	
	public void deleteByRoleId(int roleId);
	
	

}
