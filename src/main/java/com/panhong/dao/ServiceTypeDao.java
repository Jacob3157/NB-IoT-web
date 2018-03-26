package com.panhong.dao;

import java.util.List;

import com.panhong.model.ServiceType;


public interface ServiceTypeDao {
//	增加维护类型
	public void addServiceType(ServiceType serviceType);
//	更新维护类型
	public void updateServiceType(ServiceType serviceType);
//	删除维护类型
	public void deleteServiceType(ServiceType serviceType);
//	通过维护名获取维护类型
	public ServiceType getServiceTypeByName(String name);
//	获取所有维护类型
	public List<ServiceType> getAllServiceType(int pageNo, int pageSize);

	public int getCount(String hql);

}
