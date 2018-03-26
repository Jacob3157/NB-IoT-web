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


@Entity
@Table(name = "bindinfo")
public class BindInfo {
	
	@Id
	@Column(name="Id",nullable=false,unique=true)
	@GeneratedValue
	private int id;//主键 自增长
	
	@Transient
	private String openid;
	
	@Transient
	private String deviceinfo;
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="MachineID",referencedColumnName="machineID")
	private Machine machineID;
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="WechatID",referencedColumnName="openid")
	private User wechatID;
	
	@Column(name="State",nullable=false)
	private String state;

	//默认构造器
	public BindInfo() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getDeviceinfo() {
		return deviceinfo;
	}

	public void setDeviceinfo(String deviceinfo) {
		this.deviceinfo = deviceinfo;
	}

	public Machine getMachineID() {
		return machineID;
	}

	public void setMachineID(Machine machineID) {
		this.machineID = machineID;
	}

	public User getWechatID() {
		return wechatID;
	}

	public void setWechatID(User wechatID) {
		this.wechatID = wechatID;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
	
	
	

}
