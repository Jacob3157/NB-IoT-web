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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;

@Entity
@Table(name="machineinfo")
public class Machine {

	@Id
	@Column(name="MachineID",nullable=false,unique=true,length=190)
	@GeneratedValue(generator="MachineID")
	@GenericGenerator(name="MachineID",strategy="assigned")
	private String machineID;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumnsOrFormulas(value={
			@JoinColumnOrFormula(column=@JoinColumn(name="School",referencedColumnName="school")),
			@JoinColumnOrFormula(column=@JoinColumn(name="Compus",referencedColumnName="compus"))
	})
	private OperatingInfo operatinginfo;
	
	
	@Column(name="Building")
	private String building;
	
	@Column(name="Model",nullable=false)
	private String model;
	
	@Column(name="MachineType")
	private String machineType;
	
	@Column(name="status")
	private String status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LatestWorkingTime")
	private Date LatestWorkingTime;
	
	public Date getLatestWorkingTime() {
		return LatestWorkingTime;
	}

	public void setLatestWorkingTime(Date latestWorkingTime) {
		LatestWorkingTime = latestWorkingTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Transient
	private String school;
	
	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	@Transient
	private String compus;
	
	@Column(name="MachineName",nullable=false)
	private String machineName;
	
	
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

	//	default constructor
	public Machine(){
		super();
	}
	
//	minimal constructor
	public Machine(String machineID,OperatingInfo operatinginfo,String model){
		this.machineID=machineID;
		this.operatinginfo=operatinginfo;
		this.model=model;
	}
	
	//full constructor
	public Machine(String machineID,OperatingInfo operatinginfo,String model,String building,String machineType){
		this.machineID=machineID;
		this.operatinginfo=operatinginfo;
		this.model=model;
		this.building=building;
		this.machineType=machineType;
	}
	
	public String getMachineID() {
		return machineID;
	}
	public void setMachineID(String machineID) {
		this.machineID = machineID;
	}
	
	public OperatingInfo getOperatinginfo() {
		return operatinginfo;
	}

	public void setOperatinginfo(OperatingInfo operatinginfo) {
		this.operatinginfo = operatinginfo;
	}

	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMachineType() {
		return machineType;
	}
	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}
}
