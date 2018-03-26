package com.panhong.service;

import com.panhong.model.ServiceType;
import com.panhong.util.PageBean;

public interface ServiceTypeService {
//	增加维护类型
	public void addServiceType(ServiceType serviceType);
//	更新维护类型
	public void updateServiceType(ServiceType serviceType);
//	删除维护类型
	public void deleteServiceType(ServiceType serviceType);
//	通过维护名获取维护类型
	public ServiceType getServiceTypeByName(String name);
//	获取所有维护类型
	public PageBean getAllserviceType(int pageNo, int pageSize);

}
