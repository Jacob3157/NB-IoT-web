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

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;

@Entity
@Table(name="systemrecord")
public class SystemRecord {
	
	@Id
	@Column(name="SystemRecordID",nullable=false,unique=true)
	@GeneratedValue
	private int systemRecordID;
	
	@Column(name="ChangingObjects",nullable=false)
	private String changingObjects;
	
	@Column(name="VisionOld",nullable=false)
	private String visionOld;
	
	@Column(name="VisionNew",nullable=false)
	private String visionNew;
	
	@Column(name="ChangingContent",nullable=false)
	private String changingContent;
	
	@Column(name="ResponsibleWorker",nullable=false)
	private String responsibleWorker;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumnsOrFormulas(value={
			@JoinColumnOrFormula(column=@JoinColumn(name="School",referencedColumnName="school")),
			@JoinColumnOrFormula(column=@JoinColumn(name="Compus",referencedColumnName="compus"))
	})
	private OperatingInfo operatinginfo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ChangingDate",nullable=false)
	private Date changingDate;
	
	@Transient
	private String school;
	
	@Transient
	private String compus;
	
	
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
	
	//default constructor
	public SystemRecord(){
		super();
	}

	
	public int getSystemRecordID() {
		return systemRecordID;
	}


	public void setSystemRecordID(int systemRecordID) {
		this.systemRecordID = systemRecordID;
	}


	public String getChangingObjects() {
		return changingObjects;
	}


	public void setChangingObjects(String changingObjects) {
		this.changingObjects = changingObjects;
	}


	public String getVisionOld() {
		return visionOld;
	}


	public void setVisionOld(String visionOld) {
		this.visionOld = visionOld;
	}


	public String getVisionNew() {
		return visionNew;
	}


	public void setVisionNew(String visionNew) {
		this.visionNew = visionNew;
	}


	public String getChangingContent() {
		return changingContent;
	}


	public void setChangingContent(String changingContent) {
		this.changingContent = changingContent;
	}


	public String getResponsibleWorker() {
		return responsibleWorker;
	}


	public void setResponsibleWorker(String responsibleWorker) {
		this.responsibleWorker = responsibleWorker;
	}

	


	public OperatingInfo getOperatinginfo() {
		return operatinginfo;
	}


	public void setOperatinginfo(OperatingInfo operatinginfo) {
		this.operatinginfo = operatinginfo;
	}


	public Date getChangingDate() {
		return changingDate;
	}


	public void setChangingDate(Date changingDate) {
		this.changingDate = changingDate;
	}


	//full constructor
	public SystemRecord(String changingObjects, String visionOld, String visionNew,
			String changingContent, String responsibleWorker, OperatingInfo operatinginfo, Date changingDate) {
		super();
		this.changingObjects = changingObjects;
		this.visionOld = visionOld;
		this.visionNew = visionNew;
		this.changingContent = changingContent;
		this.responsibleWorker = responsibleWorker;
		this.changingDate = changingDate;
		this.operatinginfo=operatinginfo;
	}
	
}
