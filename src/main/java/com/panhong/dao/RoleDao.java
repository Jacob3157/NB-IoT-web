package com.panhong.dao;

import java.util.Collection;
import java.util.List;

import com.panhong.model.Auth_Role;

public interface RoleDao {
	
	public Auth_Role getRoleById (int Id);
	
	public Auth_Role getRoleByName(String name);
	
	public void add(Auth_Role role);
	
	public void delete(Auth_Role role);
	
	public void update(Auth_Role role);
	
	public List<Auth_Role> getRoles(Collection<String> names);
	
	 public List<Auth_Role> getRoles(int pageNo,int paeSize);

}
