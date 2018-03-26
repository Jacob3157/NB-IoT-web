package com.panhong.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "parts")
public class Parts {
	
	@Id
	@Column(name="partsId", unique=true, nullable=false,length=48)
	@GeneratedValue(generator="partsId")
	@GenericGenerator(name="partsId",strategy="assigned")
	private String partsId;
	
	@Column(name="name",unique=true, nullable=false)
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="machineType")
	private String machineType;
	
	@Column(name="onStack",nullable=false)
	private int onStack;
	
	@Column(name="image")
	private String imageurl;
	
	

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public Parts(){
		super();
	}

	public String getPartsId() {
		return partsId;
	}

	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMachineType() {
		return machineType;
	}

	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}

	public int getOnStack() {
		return onStack;
	}

	public void setOnStack(int onStack) {
		this.onStack = onStack;
	}

}
