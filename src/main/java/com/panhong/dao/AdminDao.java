package com.panhong.dao;

import java.util.List;

import com.panhong.model.Admin;

public interface AdminDao {
	
	public Admin getByUsername(String username);
	
	public Admin getByPhone(String phone);
	
	public Admin getByMail(String mail);
	
	public List<Admin> getAll();
	
	public void add(Admin admin);
	
	public void delete(Admin admin);
	
	public void update(Admin admin);

}
