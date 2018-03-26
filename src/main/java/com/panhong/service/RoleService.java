package com.panhong.service;

import java.util.Collection;
import java.util.List;

import com.panhong.model.Auth_Role;
import com.panhong.model.Auth_Role_Function;

public interface RoleService {
	
	public void addRole(Auth_Role role,Collection<Auth_Role_Function> roleFunctions);
	
	public void updateRole(Auth_Role role,Collection<Auth_Role_Function> roleFunctions);
	
	public void deleteRole(int roleId);
	
	public List<Auth_Role> getRoles(int pageNo,int pageSize);
	
	public List<Auth_Role_Function> getRole_Functions(int roleId);

}
