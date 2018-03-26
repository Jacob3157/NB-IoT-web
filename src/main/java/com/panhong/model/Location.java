package com.panhong.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "location")
public class Location {
	
	@Id
	@Column(name="id",nullable=false,unique=true)
	@GeneratedValue
	private int id;
	
	@Column(name="school",nullable=false)
	private String school;
	
	@Column(name="compus",nullable=false)
	private String compus;
	
	@Column(name="building",nullable=false,unique=true)
	private String building;

	public int getId() {
		return id;
	}

	public Location() {
		super();
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getCompus() {
		return compus;
	}

	public void setCompus(String compus) {
		this.compus = compus;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

}
