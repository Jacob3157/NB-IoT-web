package com.panhong.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.panhong.dao.MaintenanceRecordDao;
import com.panhong.model.MaintenanceRecord;
import com.panhong.model.User;
import com.panhong.service.MaintenanceRecordService;
import com.panhong.util.PageBean;

@Service
public class MaintenanceRecordServiceImpl implements MaintenanceRecordService {
	
	@Resource
	private MaintenanceRecordDao maintenanceRecordDao;

	public MaintenanceRecordDao getMaintenanceRecordDao() {
		return maintenanceRecordDao;
	}

	public void setMaintenanceRecordDao(MaintenanceRecordDao maintenanceRecordDao) {
		this.maintenanceRecordDao = maintenanceRecordDao;
	}

	@Override
	public void addMaintenanceRecord(MaintenanceRecord maintenanceReord) {
		maintenanceRecordDao.addMaintenanceRecord(maintenanceReord);
	}

	@Override
	public void updateMaintenanceRecord(MaintenanceRecord maintenanceRecord) {
		maintenanceRecordDao.updateMaintenanceRecord(maintenanceRecord);
	}

	@Override
	public void deleteMaintenanceRecord(MaintenanceRecord maintenanceRecord) {
		maintenanceRecordDao.deleteMaintenanceRecord(maintenanceRecord);
	}

	@Override
	public PageBean getMaintenanceRecordByMachine(String machineID,int pageNo, int pageSize) {
		String hql = "select count(*) from MaintenanceRecord where machineID.machineName='"+machineID+"'";
        int count = maintenanceRecordDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        int currentPage = PageBean.countCurrentPage(pageNo);
        List<MaintenanceRecord> list = maintenanceRecordDao.getMaintenanceRecordByMachine(machineID, pageNo, pageSize);
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
	public PageBean getMaintenanceRecordByOperator(String wechatID,int pageNo, int pageSize) {
		String hql = "select count(*) from MaintenanceRecord where maintenanceOperator.wechatID='"+wechatID+"'";
        int count = maintenanceRecordDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        int currentPage = PageBean.countCurrentPage(pageNo);
        List<MaintenanceRecord> list = maintenanceRecordDao.getMaintenanceRecordByOperator(wechatID, pageNo, pageSize);
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
	public PageBean getMaintenanceRecordByDate(Date date,int pageNo, int pageSize) {
		String hql = "select count(*) from MaintenanceRecord where maintenanceDate > '"+date+"'";
        int count = maintenanceRecordDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        int currentPage = PageBean.countCurrentPage(pageNo);
        List<MaintenanceRecord> list = maintenanceRecordDao.getMaintenanceRecordByDate(date, pageNo, pageSize);
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
	public PageBean getAllMaintenanceRecord(int pageNo, int pageSize) {
		String hql = "select count(*) from MaintenanceRecord";
        int count = maintenanceRecordDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        int currentPage = PageBean.countCurrentPage(pageNo);
        List<MaintenanceRecord> list = maintenanceRecordDao.getAllMaintenanceRecord(pageNo, pageSize);
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

}
