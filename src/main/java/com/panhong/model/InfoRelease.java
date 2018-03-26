package com.panhong.model;

import java.sql.Clob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="inforelease")
public class InfoRelease {
	
	@Id
	@Column(name="InfoReleaseID",nullable=false,unique=true)
	@GeneratedValue
	private int infoReleaseID;
	
	@Column(name="Promulgator",nullable=false)
	private String promulgator;
	
	@Column(name="InfoContent",nullable=false)
	private Clob infoContent;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="InfoReleaseDate",nullable=false)
	private Date infoReleaseDate;
	
	public int getInfoReleaseID() {
		return infoReleaseID;
	}

	public void setInfoReleaseID(int infoReleaseID) {
		this.infoReleaseID = infoReleaseID;
	}

	public String getPromulgator() {
		return promulgator;
	}

	public void setPromulgator(String promulgator) {
		this.promulgator = promulgator;
	}

	public Clob getInfoContent() {
		return infoContent;
	}

	public void setInfoContent(Clob infoContent) {
		this.infoContent = infoContent;
	}

	public Date getInfoReleaseDate() {
		return infoReleaseDate;
	}

	public void setInfoReleaseDate(Date infoReleaseDate) {
		this.infoReleaseDate = infoReleaseDate;
	}

	//default constructor
	public InfoRelease(){
		super();
	}
	
	//full constructor
	public InfoRelease( String promulgator, Clob infoContent, Date infoReleaseDate) {
//		super();
		this.promulgator = promulgator;
		this.infoContent = infoContent;
		this.infoReleaseDate = infoReleaseDate;
	}
	

}
