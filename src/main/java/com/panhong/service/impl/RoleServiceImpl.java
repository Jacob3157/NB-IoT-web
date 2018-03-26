package com.panhong.service.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.panhong.dao.RoleDao;
import com.panhong.dao.RoleFunctionDao;
import com.panhong.model.Auth_Role;
import com.panhong.model.Auth_Role_Function;
import com.panhong.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Resource
	private RoleDao roleDao;
	
	@Resource
	private RoleFunctionDao roleFunctionDao;
	
	

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public RoleFunctionDao getRoleFunctionDao() {
		return roleFunctionDao;
	}

	public void setRoleFunctionDao(RoleFunctionDao roleFunctionDao) {
		this.roleFunctionDao = roleFunctionDao;
	}

	@Override
	public void addRole(Auth_Role role,Collection<Auth_Role_Function> roleFunctions) {
		roleDao.add(role);
		for(Auth_Role_Function roleFunction : roleFunctions){
			roleFunction.setRoleId(role.getId());
			roleFunctionDao.add(roleFunction);
		}
	}

	@Override
	public void updateRole(Auth_Role role,
			Collection<Auth_Role_Function> roleFunctions) {
		roleDao.update(role);
		for(Auth_Role_Function roleFunction : roleFunctions){
			roleFunction.setRoleId(role.getId());
			roleFunctionDao.add(roleFunction);
		}
		
	}

	@Override
	public void deleteRole(int roleId) {
		Auth_Role role=roleDao.getRoleById(roleId);
		roleDao.delete(role);
		roleFunctionDao.deleteByRoleId(roleId);
	}

	@Override
	public List<Auth_Role> getRoles(int pageNo, int pageSize) {
		return roleDao.getRoles(pageNo, pageSize);
		
	}

	@Override
	public List<Auth_Role_Function> getRole_Functions(int roleId) {
		return roleFunctionDao.getByRoleId(roleId);
	}

}
