package com.panhong.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "auth_Function")
public class Auth_Function {
	
	@Id
	@Column(name="id",nullable=false,unique=true)
	@GeneratedValue
	private int id;
	
	@Column(name="name",nullable=false,unique=true)
	private String name;
	
	@Column(name="parent_id",nullable=false)
	private int ParentId;
	
	@Column(name="url")
	private String url;
	
	@Column(name="serial_num",nullable=false)
	private int SerialNum;
	
	@Column(name="accordion",nullable=false)
	private int Accordion;
	
	public Auth_Function(){}

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

	public int getParentId() {
		return ParentId;
	}

	public void setParentId(int parentId) {
		ParentId = parentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getSerialNum() {
		return SerialNum;
	}

	public void setSerialNum(int serialNum) {
		SerialNum = serialNum;
	}

	public int getAccordion() {
		return Accordion;
	}

	public void setAccordion(int accordion) {
		Accordion = accordion;
	}
	
	
	
}
