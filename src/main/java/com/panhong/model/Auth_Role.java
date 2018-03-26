package com.panhong.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "auth_Role")
public class Auth_Role {
	
	@Id
	@Column(name="id",nullable=false,unique=true)
	@GeneratedValue
	private int id;
	
	@Column(name="name",nullable=false)
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Auth_Role() {
		super();
	}
	
	

}
