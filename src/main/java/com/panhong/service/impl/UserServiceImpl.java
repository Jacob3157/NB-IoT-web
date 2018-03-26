package com.panhong.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding.Use;

import org.springframework.stereotype.Service;

import com.panhong.dao.UserDao;
import com.panhong.model.User;
import com.panhong.service.UserService;
import com.panhong.util.DataBean;
import com.panhong.util.PageBean;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void addUser(User u) {
		userDao.addUser(u);
	}

	@Override
	public void updateUser(User u) {
		userDao.updateUser(u);
	}

	@Override
	public void deleteUser(User u) {
		userDao.deleteUser(u);
	}

	@Override
	public User getUserById(String Id) {
		return userDao.getUserById(Id);
	}

	@Override
	public PageBean getAllUser(int pageNo,
			int pageSize) {
		String hql = "select count(*) from User";
        int count = userDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        int currentPage = PageBean.countCurrentPage(pageNo);
        List<User> list = userDao.getAllUser(pageNo, pageSize);
        // 把分页信息保存到Bean中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotal(count);
        pageBean.setTotalPage(totalPage);
        pageBean.setRows(list);
        pageBean.init();
        return pageBean;
	}

	@Override
	public PageBean gerAvailableUser(int pageNo,
			int pageSize) {
		String hql = "select count(*) from User where valid=0";
        int count = userDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        int currentPage = PageBean.countCurrentPage(pageNo);
        List<User> list = userDao.gerAvailableUser(pageNo, pageSize);
        // 把分页信息保存到Bean中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotal(count);
        pageBean.setTotalPage(totalPage);
        pageBean.setRows(list);
        pageBean.init();
        return pageBean;
	}

	@Override
	public PageBean getByRegisterStatue(String statue, int pageNo,
			int pageSize) {
		String hql = "select count(*) from User where code='"+statue+"'";
        int count = userDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        int currentPage = PageBean.countCurrentPage(pageNo);
        List<User> list = userDao.getByRegisterStatue(statue, pageNo, pageSize);
        // 把分页信息保存到Bean中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotal(count);
        pageBean.setTotalPage(totalPage);
        pageBean.setRows(list);
        pageBean.init();
        return pageBean;
	}

	@Override
	public PageBean getByStatueAndRegTime(String statue, Date date,
			int pageNo, int pageSize) {
		String hql = "select count(*) from User where code='"+statue+"'"+"and codeTime<='"+date+"'";
        int count = userDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        int currentPage = PageBean.countCurrentPage(pageNo);
        List<User> list = userDao.getByRegisterStatue(statue, pageNo, pageSize);
        // 把分页信息保存到Bean中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotal(count);
        pageBean.setTotalPage(totalPage);
        pageBean.setRows(list);
        pageBean.init();
        return pageBean;
	}

	@Override
	public PageBean getByRegTime(Date date, int pageNo, int pageSize) {
		String hql = "select count(*) from User where codeTime<='"+date+"'";
        int count = userDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        System.out.println(totalPage);
        int currentPage = PageBean.countCurrentPage(pageNo);
        System.out.println(currentPage);
        List<User> list = userDao.getByRegTime(date, pageNo, pageSize);
        System.out.println(list.size());
        // 把分页信息保存到Bean中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotal(count);
        pageBean.setTotalPage(totalPage);
        pageBean.setRows(list);
        pageBean.init();
        return pageBean;
	}

	@Override
	public PageBean getByUserProperty(int userProperty, int pageNo,
			int pageSize) {
		String hql = "select count(*) from User where userProperty.userProperty='"+userProperty+"'";
        int count = userDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        int currentPage = PageBean.countCurrentPage(pageNo);
        List<User> list = userDao.getByUserProperty(userProperty, pageNo, pageSize);
        // 把分页信息保存到Bean中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotal(count);
        pageBean.setTotalPage(totalPage);
        pageBean.setRows(list);
        pageBean.init();
        return pageBean;
	}

	@Override
	public List<User> getByUserProperty(int userProperty) {
		return userDao.getByUserProperty(userProperty);
	}

	@Override
	public PageBean getByFollowTime(Date startTime, Date endTime, int pageNo,
			int pageSize) {
		String hql = "select count(*) from User where followDate>='"+startTime+"' and followDate<='"+endTime+"'";
        int count = userDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        int currentPage = PageBean.countCurrentPage(pageNo);
        List<User> list = userDao.getByFollowTime(startTime, endTime, pageNo, pageSize);
        // 把分页信息保存到Bean中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotal(count);
        pageBean.setTotalPage(totalPage);
        pageBean.setRows(list);
        pageBean.init();
        return pageBean;
	}

	@Override
	public PageBean getByFollowTimeAnduserProperty(int userProperty,
			Date startTime, Date endTime, int pageNo, int pageSize) {
		String hql = "select count(*) from User where followDate>='"+startTime+"'and followDate<='"+endTime+"'and userProperty.userProperty='"+userProperty+"'";
        int count = userDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        int currentPage = PageBean.countCurrentPage(pageNo);
        List<User> list = userDao.getByFollowTimeAnduserProperty(userProperty, startTime, endTime, pageNo, pageSize);
        // 把分页信息保存到Bean中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotal(count);
        pageBean.setTotalPage(totalPage);
        pageBean.setRows(list);
        pageBean.init();
        return pageBean;
	}

	@Override
	public DataBean getByName(String name, int pageNo, int pageSize) {
		String hql="select count(*) from User where name='"+name+"'";
		int count = userDao.getCount(hql); // 总记录数
		List<User> list=userDao.getByName(name, pageNo, pageSize);
		//dataBean
		DataBean dataBean=new DataBean();
		dataBean.setRows(list);
		dataBean.setTotal(count);
		return dataBean;
	}

	@Override
	public DataBean getByNickName(String nickname, int pageNo, int pageSize) {
		String hql="select count(*) from User where nickname='"+nickname+"'";
		int count = userDao.getCount(hql); // 总记录数
		List<User> list=userDao.getByNickName(nickname, pageNo, pageSize);
		//dataBean
		DataBean dataBean=new DataBean();
		dataBean.setRows(list);
		dataBean.setTotal(count);
		return dataBean;
	}

	@Override
	public DataBean getByStudentId(String StudentId, int pageNo, int pageSize) {
		String hql="select count(*) from User where StudentId='"+StudentId+"'";
		int count = userDao.getCount(hql); // 总记录数
		List<User> list=userDao.getByStudentId(StudentId, pageNo, pageSize);
		//dataBean
		DataBean dataBean=new DataBean();
		dataBean.setRows(list);
		dataBean.setTotal(count);
		return dataBean;
	}

	@Override
	public DataBean getByPhone(String phone, int pageNo, int pageSize) {
		String hql="select count(*) from User where telephone='"+phone+"'";
		int count = userDao.getCount(hql); // 总记录数
		List<User> list=userDao.getByPhone(phone, pageNo, pageSize);
		//dataBean
		DataBean dataBean=new DataBean();
		dataBean.setRows(list);
		dataBean.setTotal(count);
		return dataBean;
	}

	@Override
	public User getByStudentId(String StudentId) {
		return userDao.getByStudentId(StudentId);
	}

	@Override
	public User getByPhone(String phone) {
		return userDao.getByPhone(phone);
	}

}
