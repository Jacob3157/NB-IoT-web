package com.panhong.service;

import java.util.List;

import com.panhong.model.MaintenanceType;
import com.panhong.util.PageBean;

public interface MaintenanceTypeService {
//	增加维护类型
	public void addMaintenanceType(MaintenanceType maintenanceType);
//	更新维护类型
	public void updateMaintenanceType(MaintenanceType maintenanceType);
//	删除维护类型
	public void deleteMaintenanceType(MaintenanceType maintenanceType);
//	通过维护名获取维护类型
	public MaintenanceType getMaintenanceTypeByName(String name);
//	获取所有维护类型
	public PageBean getAllMaintenanceType(int pageNo, int pageSize);

}
