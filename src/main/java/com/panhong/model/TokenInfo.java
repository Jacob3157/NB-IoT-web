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
@Table(name="tokenInfo")
public class TokenInfo {
	
	@Id
	@Column(name="ID",nullable=false,unique=true)
	@GeneratedValue
	private int Id;
	
	@Column(name="ExpiresIn")
	private int expiresIn;
	
	@Column(name="Token",nullable=false)
	private String accessToken;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CreateTime",nullable=false)
	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String token) {
		this.accessToken = token;
	}
	
	
	//默认构造函数
	public TokenInfo(){
		super();
	}
	
	

}
