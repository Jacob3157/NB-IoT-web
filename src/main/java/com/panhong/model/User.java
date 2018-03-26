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

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author AlaricZhang
 *�û���Ϣ��
 */

@Entity
@Table(name = "userinfo")
public class User {
	
	@Id
	@Column(name="openid", unique=true, nullable=false,length=48)
	@GeneratedValue(generator="openid")
	@GenericGenerator(name="openid",strategy="assigned")
	private String openid;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="NickName")
	private String nickname;
	
	@Column(name="Area")
	private String area;
	
	@Column(name="Province")
	private String province;
	
	@Column(name="City")
	private String city;
	
	@Column(name="Country")
	private String country;
	
	@Column(name="Gender")
	private String sex;
	
	@Column(name="School")
	private String school;
	
	@Column(name="StudentID")
	private String studentID;
	
	@Column(name="Telephone",unique=true)
	private String telephone;
	
	/*
	 * 
	 */
	@Column(name="VipProperty", nullable=false)
	private int vipProperty;
	
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="UserProperty",referencedColumnName="userProperty")
	private UserProperty userProperty;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FollowDate", nullable=false)
	private Date followDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UnfollowDate")
	private Date unfollowDate;
	
	//0 表示有效 1表示无效
	@Column(name="Valid",nullable=false)
	private int valid;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCodeTime() {
		return codeTime;
	}

	public void setCodeTime(Date codeTime) {
		this.codeTime = codeTime;
	}

	//
	@Column(name="Code")
	private String code;
	
/*	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="startTime")
	private Date startTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="endTime")
	private Date endTime;*/
	
	@Column(name="totalTimes")
	private String totalTimes;
	
	@Column(name="times")
	private String times;

	public String getTotalTimes() {
		return totalTimes;
	}

	public void setTotalTimes(String totalTimes) {
		this.totalTimes = totalTimes;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	/*public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}*/


	//洗衣机有效码时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CodeTime")
	private Date codeTime;
	//不映射到数据库中的字段
	@Transient
	private String property;
	
	@Transient
	private String remainingTimes;
	
	public String getRemainingTimes() {
		return remainingTimes;
	}

	public void setRemainingTimes(String remainingTimes) {
		this.remainingTimes = remainingTimes;
	}

	@Transient
	private String subscribe;
	
	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	//default constructor
	public User(){
		super();
	}
	
	//minimal constructor
	public User(String wechatID,int vipProperty,UserProperty userProperty,Date followDate,int valid,String province,String city,String country){
		this.openid=wechatID;
		this.vipProperty=vipProperty;
		this.userProperty=userProperty;
		this.followDate=followDate;
		this.valid=valid;
		this.province=province;
		this.city=city;
		this.country=country;
	}
	
	//full constructor
	public User(String wechatID,String name, String nickName,String area,String gender,String school,
			String studentID,String telephone,int vipProperty,UserProperty userProperty,Date followDate,Date unfollowDate,int valid,String province,String city,String country){
		this.openid=wechatID;
		this.name=name;
		this.nickname=nickName;
		this.area=area;
		this.sex=gender;
		this.school=school;
		this.studentID=studentID;
		this.telephone=telephone;
		this.vipProperty=vipProperty;
		this.userProperty=userProperty;
		this.followDate=followDate;
		this.unfollowDate=unfollowDate;
		this.valid=valid;
		this.province=province;
		this.city=city;
		this.country=country;
	}

	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}


	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getVipProperty() {
		return vipProperty;
	}

	public void setVipProperty(int vipProperty) {
		this.vipProperty = vipProperty;
	}

	public UserProperty getUserProperty() {
		return userProperty;
	}

	public void setUserProperty(UserProperty userProperty) {
		this.userProperty = userProperty;
	}

	public Date getFollowDate() {
		return followDate;
	}

	public void setFollowDate(Date followDate) {
		this.followDate = followDate;
	}

	public Date getUnfollowDate() {
		return unfollowDate;
	}

	public void setUnfollowDate(Date unfollowDate) {
		this.unfollowDate = unfollowDate;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}


}
