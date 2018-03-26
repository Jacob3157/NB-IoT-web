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

@Entity
@Table(name="serviceRecord")
public class ServiceRecord {
	
	@Id
	@Column(name="ServiceRecordID",nullable=false,unique=true)
	@GeneratedValue
	private int serviceRecordID;
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="machineID",referencedColumnName="machineID")
	private Machine machineID;
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="serviceName",referencedColumnName="serviceName")
	private ServiceType serviceType;
	
	@Column(name="Symptom")
	private String symptom;
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="WechatID",referencedColumnName="openid")
	private User serviceOperator;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ServiceDate",nullable=false)
	private Date serviceDate;
	
	@Column(name="Comment")
	private String comment;
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ServiceRecord(){
		super();
	}

	public Machine getMachineID() {
		return machineID;
	}

	public void setMachineID(Machine machineID) {
		this.machineID = machineID;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public int getServiceRecordID() {
		return serviceRecordID;
	}

	public void setServiceRecordID(int serviceRecordID) {
		this.serviceRecordID = serviceRecordID;
	}

	public User getServiceOperator() {
		return serviceOperator;
	}

	public void setServiceOperator(User serviceOperator) {
		this.serviceOperator = serviceOperator;
	}

	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	

}
