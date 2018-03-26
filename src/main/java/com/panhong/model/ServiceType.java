package com.panhong.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 用于标识对应的维修内容需要的配件
 * 维修内容表*/
@Entity
@Table(name = "serviceType")
public class ServiceType {
	
	@Id
	@Column(name="Id",nullable=false,unique=true)
	@GeneratedValue
	private int Id;//PK
	
	@Column(name="serviceName",nullable=false,unique=true)
	private String serviceName;
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="partsOne",referencedColumnName="partsId")
	private Parts partsOne;
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="partsTwo",referencedColumnName="partsId")
	private Parts partsTwo;
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="partsThree",referencedColumnName="partsId")
	private Parts partsThree;
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="partsFour",referencedColumnName="partsId")
	private Parts partsFour;
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="partsFive",referencedColumnName="partsId")
	private Parts partsFive;
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="partsSix",referencedColumnName="partsId")
	private Parts partsSix;
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="partsSeven",referencedColumnName="partsId")
	private Parts partsSeven;
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="partsEight",referencedColumnName="partsId")
	private Parts partsEight;
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="partsNine",referencedColumnName="partsId")
	private Parts partsNine;
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="partsTen",referencedColumnName="partsId")
	private Parts partsTen;
	
	@Transient
	private String partsOneName;
	
	@Transient
	private String partsTwoName;
	
	@Transient
	private String partsThreeName;
	
	@Transient
	private String partsFourName;
	
	@Transient
	private String partsFiveName;
	
	@Transient
	private String partsSixName;
	
	@Transient
	private String partsSevenName;
	
	@Transient
	private String partsEightName;
	
	@Transient
	private String partsNineName;
	
	public String getPartsOneName() {
		return partsOneName;
	}

	public void setPartsOneName(String partsOneName) {
		this.partsOneName = partsOneName;
	}

	public String getPartsTwoName() {
		return partsTwoName;
	}

	public void setPartsTwoName(String partsTwoName) {
		this.partsTwoName = partsTwoName;
	}

	public String getPartsThreeName() {
		return partsThreeName;
	}

	public void setPartsThreeName(String partsThreeName) {
		this.partsThreeName = partsThreeName;
	}

	public String getPartsFourName() {
		return partsFourName;
	}

	public void setPartsFourName(String partsFourName) {
		this.partsFourName = partsFourName;
	}

	public String getPartsFiveName() {
		return partsFiveName;
	}

	public void setPartsFiveName(String partsFiveName) {
		this.partsFiveName = partsFiveName;
	}

	public String getPartsSixName() {
		return partsSixName;
	}

	public void setPartsSixName(String partsSixName) {
		this.partsSixName = partsSixName;
	}

	public String getPartsSevenName() {
		return partsSevenName;
	}

	public void setPartsSevenName(String partsSevenName) {
		this.partsSevenName = partsSevenName;
	}

	public String getPartsEightName() {
		return partsEightName;
	}

	public void setPartsEightName(String partsEightName) {
		this.partsEightName = partsEightName;
	}

	public String getPartsNineName() {
		return partsNineName;
	}

	public void setPartsNineName(String partsNineName) {
		this.partsNineName = partsNineName;
	}

	public String getPartsTenName() {
		return partsTenName;
	}

	public void setPartsTenName(String partsTenName) {
		this.partsTenName = partsTenName;
	}

	@Transient
	private String partsTenName;
	
	//default constructor
	public ServiceType(){
		
	}

	public Parts getPartsOne() {
		return partsOne;
	}

	public void setPartsOne(Parts partsOne) {
		this.partsOne = partsOne;
	}

	public Parts getPartsTwo() {
		return partsTwo;
	}

	public void setPartsTwo(Parts partsTwo) {
		this.partsTwo = partsTwo;
	}

	public Parts getPartsThree() {
		return partsThree;
	}

	public void setPartsThree(Parts partsThree) {
		this.partsThree = partsThree;
	}

	public Parts getPartsFour() {
		return partsFour;
	}

	public void setPartsFour(Parts partsFour) {
		this.partsFour = partsFour;
	}

	public Parts getPartsFive() {
		return partsFive;
	}

	public void setPartsFive(Parts partsFive) {
		this.partsFive = partsFive;
	}

	public Parts getPartsSix() {
		return partsSix;
	}

	public void setPartsSix(Parts partsSix) {
		this.partsSix = partsSix;
	}

	public Parts getPartsSeven() {
		return partsSeven;
	}

	public void setPartsSeven(Parts partsSeven) {
		this.partsSeven = partsSeven;
	}

	public Parts getPartsEight() {
		return partsEight;
	}

	public void setPartsEight(Parts partsEight) {
		this.partsEight = partsEight;
	}

	public Parts getPartsNine() {
		return partsNine;
	}

	public void setPartsNine(Parts partsNine) {
		this.partsNine = partsNine;
	}

	public Parts getPartsTen() {
		return partsTen;
	}

	public void setPartsTen(Parts partsTen) {
		this.partsTen = partsTen;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	
	
	

}
