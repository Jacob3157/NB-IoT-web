package com.panhong.service;

import java.util.Date;
import java.util.List;

import com.panhong.model.MaintenanceRecord;
import com.panhong.util.PageBean;

public interface MaintenanceRecordService {
//	增加维护记录
	public void addMaintenanceRecord(MaintenanceRecord maintenanceReord);
//	更新维护记录
	public void updateMaintenanceRecord(MaintenanceRecord maintenanceRecord);
//	删除维护记录
	public void deleteMaintenanceRecord(MaintenanceRecord maintenanceRecord);
//	获取特定机器的维护记录
	public PageBean getMaintenanceRecordByMachine(String machineID,int pageNo, int pageSize);
//	获取特定维护人员的维护记录
	public PageBean getMaintenanceRecordByOperator(String wechatID,int pageNo, int pageSize);
//	获取指定日期后的维护记录
	public PageBean getMaintenanceRecordByDate(Date date,int pageNo, int pageSize);
//	获取所有维护记录
	public PageBean getAllMaintenanceRecord(int pageNo, int pageSize);
	
	

}
