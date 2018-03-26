package com.panhong.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "auth_user_role")
public class Auth_User_Role {
	
	@Id
	@Column(name="id",nullable=false,unique=true)
	@GeneratedValue
	private int id;

	@Column(name="role_id",nullable=false)
	private int roleId;
	
	@Column(name="user_id",nullable=false)
	private int userId;

	public Auth_User_Role() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	

}
