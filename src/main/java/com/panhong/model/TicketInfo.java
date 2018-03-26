package com.panhong.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "ticketinfo")
public class TicketInfo {

	
	@Id
	@Column(name="ID",nullable=false,unique=true)
	@GeneratedValue
	private int Id;
	
	@Column(name="ExpiresIn")
	private int expires_in;
	
	@Column(name="ticket",nullable=false)
	private String ticket;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CreateTime",nullable=false)
	private Date createTime;
}
