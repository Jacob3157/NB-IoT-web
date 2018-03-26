package com.panhong.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="userproperty")
public class UserProperty {
	
	@Id
	@Column(name="UserProperty",nullable=false,unique=true)
	@GeneratedValue
	private int userProperty;
	
	@Column(name="GradeName",nullable=false,unique=true)
	private String gradeName;
	
	@Column(name="Discount",nullable=false)
	private double discount;
	
	@Column(name="isVIP",nullable=false)
	private String isVIP;
	

	public String getIsVIP() {
		return isVIP;
	}

	public void setIsVIP(String isVIP) {
		this.isVIP = isVIP;
	}

	/*
	 * default constructor
	 */
	public UserProperty(){
		super();
	}
	
	//full constructor
	public UserProperty(String gradeName,Double discount,String isVIP){
		this.discount=discount;
		this.gradeName=gradeName;
		this.isVIP=isVIP;
	}

	public int getUserProperty() {
		return userProperty;
	}

	public void setUserProperty(int userProperty) {
		this.userProperty = userProperty;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

}
