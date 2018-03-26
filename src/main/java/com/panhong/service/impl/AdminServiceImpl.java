package com.panhong.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.panhong.dao.AdminDao;
import com.panhong.dao.UserRoleDao;
import com.panhong.model.Admin;
import com.panhong.model.Auth_User_Role;
import com.panhong.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Resource
	private AdminDao adminDao;
	
	@Resource
	private UserRoleDao userRoleDao;

	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public Admin getByUsername(String username) {
		return adminDao.getByUsername(username);
	}

	@Override
	public Admin getByPhone(String phone) {
		return adminDao.getByPhone(phone);
	}

	@Override
	public Admin getByMail(String mail) {
		return adminDao.getByMail(mail);
	}

	@Override
	public List<Admin> getAll() {
		return adminDao.getAll();
	}

	@Override
	public void add(Admin admin) {
		adminDao.add(admin);
	}

	@Override
	public void delete(Admin admin) {
		adminDao.delete(admin);
	}

	@Override
	public void update(Admin admin) {
		adminDao.update(admin);
	}

	@Override
	public List<Auth_User_Role> getUserRoles(int pageNo, int pageSize) {
		return userRoleDao.getUserRole(pageNo, pageSize);
	}

	public UserRoleDao getUserRoleDao() {
		return userRoleDao;
	}

	public void setUserRoleDao(UserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

	@Override
	public List<Auth_User_Role> getUserRolesByUserId(int userId) {
		return userRoleDao.getByUserId(userId);
	}

	@Override
	public void addUserRoles(int userId, int[] roleIds) {
		for(int i=0;i<roleIds.length;i++){
			Auth_User_Role userRole=new Auth_User_Role();
			userRole.setRoleId(roleIds[i]);
			userRole.setUserId(userId);
			userRoleDao.add(userRole);
		}
		
	}

}
