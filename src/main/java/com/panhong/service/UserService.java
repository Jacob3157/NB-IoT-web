package com.panhong.service;

import java.util.Date;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import com.panhong.model.User;
import com.panhong.util.DataBean;
import com.panhong.util.PageBean;


public interface UserService {

	//addUser
	public void addUser(User u);
	//updateUser
	public void updateUser(User u);
	//delete User
	public void deleteUser(User u);
	public User getUserById(String Id);
	//getAllUsers
	public PageBean getAllUser(int pageNo, int pageSize);
	//getAvailableUser获取关注用户集
	public PageBean gerAvailableUser(int pageNo, int pageSize); 
	//根据用户注册状态获取用户
	public PageBean getByRegisterStatue(String statue,int pageNo, int pageSize);
	//根据注册状态和注册时间获取用户
	public PageBean getByStatueAndRegTime(String statue,Date date,int pageNo, int pageSize);
	//根据注册时间获取注册用户
	public PageBean getByRegTime(Date date,int pageNo, int pageSize);
	//通过用户属性获取用户
	public PageBean getByUserProperty(int userProperty,int pageNo, int pageSize);
	//通过关注时间获取用户
	public PageBean getByFollowTime(Date startTime,Date endTime,int pageNo, int pageSize);
	//通过关注时间获取用户
	public PageBean getByFollowTimeAnduserProperty(int userProperty,Date startTime,Date endTime,int pageNo, int pageSize);
	//通过用户属性获取用户名称或者唯一ID值
	public List<User> getByUserProperty(int userProperty);
	//根据用户姓名获取注册用户
	public DataBean getByName(String name,int pageNo, int pageSize);
	//根据用户昵称获取注册用户
	public DataBean getByNickName(String nickname,int pageNo, int pageSize);
	//根据用户昵称获取注册用户
	public DataBean getByStudentId(String StudentId,int pageNo, int pageSize);
	
	public User getByStudentId(String StudentId);
	//根据用户电话获取注册用户
	public DataBean getByPhone(String phone,int pageNo, int pageSize);
	
	public User getByPhone(String phone);
}
