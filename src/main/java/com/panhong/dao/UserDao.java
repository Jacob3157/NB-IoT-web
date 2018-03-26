/**
 * 
 */
package com.panhong.dao;

import java.util.Date;
import java.util.List;

import com.panhong.model.User;

/**
 * @author alaric
 * 用户表DAO对象，完成对用户表的底层操作
 *
 */
public interface UserDao {
	//addUser
	public void addUser(User user);
	//updateUser
	public void updateUser(User user);
	//getUser
	public User getUserById(String Id);
	//getAllUsers
	public List<User> getAllUser(int pageNo, int pageSize);
	//getAvailableUser获取关注用户集
	public List<User> gerAvailableUser(int pageNo, int pageSize);
	//delete user
	public void deleteUser(User user);
	//根据用户注册状态获取用户
	public List<User> getByRegisterStatue(String statue,int pageNo, int pageSize);
	//根据注册状态和注册时间获取用户
	public List<User> getByStatueAndRegTime(String statue,Date date,int pageNo, int pageSize);
	//根据注册时间获取注册用户
	public List<User> getByRegTime(Date date,int pageNo, int pageSize);
	//根据用户姓名获取注册用户
	public List<User> getByName(String name,int pageNo, int pageSize);
	//根据用户昵称获取注册用户
	public List<User> getByNickName(String nickname,int pageNo, int pageSize);
	//根据用户学号获取注册用户
	public List<User> getByStudentId(String StudentId,int pageNo, int pageSize);
	//根据用户学号获取注册用户
	public User getByStudentId(String StudentId);
	//根据用户电话获取注册用户
	public List<User> getByPhone(String phone,int pageNo, int pageSize);
	
	public User getByPhone(String phone);
	//通过关注时间获取用户
	public List<User> getByFollowTime(Date startTime,Date endTime,int pageNo, int pageSize);
	//通过关注时间获取用户
	public List<User> getByFollowTimeAnduserProperty(int userProperty,Date startTime,Date endTime,int pageNo, int pageSize);
	//获取总记录数
	public int getCount(String hql);
	//通过用户属性获取用户
	public List<User> getByUserProperty(int userProperty,int pageNo, int pageSize);
	
	//通过用户属性获取用户名称或者唯一ID值
	public List<User> getByUserProperty(int userProperty);
}
