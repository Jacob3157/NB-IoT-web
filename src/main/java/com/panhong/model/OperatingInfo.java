package com.panhong.model;


import java.io.Serializable;
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

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="operatinginfo")
public class OperatingInfo implements Serializable{
	/**
	 * 主键类必须继承Serializable接口
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="School",nullable=false,length=20)
	@GeneratedValue(generator="School")
	@GenericGenerator(name="School",strategy="assigned")
	private String school;
	
	@Id
	@Column(name="Compus",nullable=false,length=20)
	@GeneratedValue(generator="Compus")
	@GenericGenerator(name="Compus",strategy="assigned")
	private String compus;
	
	//default constructor
	public OperatingInfo(){
		super();
	}
	
	//full constructor
	public OperatingInfo(String school,String compus,double washingPrice){
		this.school=school;
		this.compus=compus;
		this.washingPrice=washingPrice;
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

	public double getWashingPrice() {
		return washingPrice;
	}

	
	public Date getLatestModifyTime() {
		return latestModifyTime;
	}

	public void setLatestModifyTime(Date latestModifyTime) {
		this.latestModifyTime = latestModifyTime;
	}

	public Admin getLatestModifyWorker() {
		return latestModifyWorker;
	}

	public void setLatestModifyWorker(Admin latestModifyWorker) {
		this.latestModifyWorker = latestModifyWorker;
	}

	public void setWashingPrice(double washingPrice) {
		this.washingPrice = washingPrice;
	}

	@Column(name="WashingPrice")
	private double washingPrice;
	
	
	@Column(name="latestModifyTime")
	private Date latestModifyTime;
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="latestModifyWorker",referencedColumnName="id")
	private Admin latestModifyWorker;
	
	

}
