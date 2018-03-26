package com.panhong.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class OperatingInfoPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compus == null) ? 0 : compus.hashCode());
		result = prime * result + ((school == null) ? 0 : school.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OperatingInfoPK other = (OperatingInfoPK) obj;
		if (compus == null) {
			if (other.compus != null)
				return false;
		} else if (!compus.equals(other.compus))
			return false;
		if (school == null) {
			if (other.school != null)
				return false;
		} else if (!school.equals(other.school))
			return false;
		return true;
	}

	@Column(name="School",nullable=false,length=20)
	private String school;
	
	@Column(name="Compus",nullable=false,length=20)
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
	
	public OperatingInfoPK(String school, String compus) {
		super();
		this.school = school;
		this.compus = compus;
	}
	
	public OperatingInfoPK() {
		
	}
	
	
	

}
