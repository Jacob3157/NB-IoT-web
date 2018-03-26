package com.panhong.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="maintenancerecord")
public class MaintenanceRecord {
	
	@Id
	@Column(name="MaintenanceRecordID",nullable=false,unique=true)
	@GeneratedValue
	private int maintenanceRecordID;
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="MachineID",referencedColumnName="machineID")
	private Machine machineID;
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="MaintenanceType",referencedColumnName="maintenanceType")
	private MaintenanceType maintenanceType;
	
	@Column(name="Comment")
	private String comment;
	
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="WechatID",referencedColumnName="openid")
	private User maintenanceOperator;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MaintenanceDate",nullable=false)
	private Date maintenanceDate;
	
	//用于获取维护的类型名称
	@Transient
	private String maintenance;
	
	@Transient
	private String Date_main;
	
	public String getDate_main() {
		return Date_main;
	}

	public void setDate_main(String date_main) {
		Date_main = date_main;
	}

	public String getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(String maintenance) {
		this.maintenance = maintenance;
	}

	@Transient
	private String userID;
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	@Transient
	private String machine;
	
	public MaintenanceRecord(){
		
	}
	
	public int getMaintenanceRecordID() {
		return maintenanceRecordID;
	}

	public void setMaintenanceRecordID(int maintenanceRecordID) {
		this.maintenanceRecordID = maintenanceRecordID;
	}

	public Machine getMachineID() {
		return machineID;
	}

	public void setMachineID(Machine machineID) {
		this.machineID = machineID;
	}

	public MaintenanceType getMaintenanceType() {
		return maintenanceType;
	}

	public void setMaintenanceType(MaintenanceType maintenanceType) {
		this.maintenanceType = maintenanceType;
	}



	public User getMaintenanceOperator() {
		return maintenanceOperator;
	}

	public void setMaintenanceOperator(User maintenanceOperator) {
		this.maintenanceOperator = maintenanceOperator;
	}

	public Date getMaintenanceDate() {
		return maintenanceDate;
	}

	public void setMaintenanceDate(Date maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
	
	

}
