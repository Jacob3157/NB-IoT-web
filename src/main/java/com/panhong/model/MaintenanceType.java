package com.panhong.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="maintenancetype")
public class MaintenanceType {
	
	/**
	 * 用于标识对应的维护
	 * 维护内容表*/
	
	@Id
	@Column(name="MaintenanceType",nullable=false,unique=true)
	@GeneratedValue
	private int maintenanceType;
	
	@Column(name="MaintenanceName",nullable=false)
	private String maintenanceName;
	
	
	//default constructor
	public MaintenanceType(){
		super();
	}

	//full constructor
	public MaintenanceType( String maintenanceName) {
		super();
		this.maintenanceName = maintenanceName;
	}

	public int getMaintenanceType() {
		return maintenanceType;
	}

	public void setMaintenanceType(int maintenanceType) {
		this.maintenanceType = maintenanceType;
	}

	public String getMaintenanceName() {
		return maintenanceName;
	}

	public void setMaintenanceName(String maintenanceName) {
		this.maintenanceName = maintenanceName;
	}
	
	
	
	
	

}
