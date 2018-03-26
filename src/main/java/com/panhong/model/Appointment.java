package com.panhong.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "appointment")
public class Appointment {
	
	@Id
	@Column(name="appointmentId",nullable=false,unique=true)
	@GeneratedValue
	private int appointmentId;
	
	
	

}
