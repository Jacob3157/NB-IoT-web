package com.panhong.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.panhong.dao.ServiceTypeDao;
import com.panhong.model.MaintenanceType;
import com.panhong.model.ServiceType;
import com.panhong.service.ServiceTypeService;
import com.panhong.util.PageBean;


@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {
	
	@Resource
	private ServiceTypeDao serviceTypeDao;

	public ServiceTypeDao getServiceTypeDao() {
		return serviceTypeDao;
	}

	public void setServiceTypeDao(ServiceTypeDao serviceTypeDao) {
		this.serviceTypeDao = serviceTypeDao;
	}

	@Override
	public void addServiceType(ServiceType serviceType) {
		serviceTypeDao.addServiceType(serviceType);
	}

	@Override
	public void updateServiceType(ServiceType serviceType) {
		serviceTypeDao.updateServiceType(serviceType);
	}

	@Override
	public void deleteServiceType(ServiceType serviceType) {
		serviceTypeDao.deleteServiceType(serviceType);
	}

	@Override
	public ServiceType getServiceTypeByName(String name) {
		return serviceTypeDao.getServiceTypeByName(name);
	}

	@Override
	public PageBean getAllserviceType(int pageNo, int pageSize) {
		String hql = "select count(*) from ServiceType";
        int count = serviceTypeDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        int currentPage = PageBean.countCurrentPage(pageNo);
        List<ServiceType> list = serviceTypeDao.getAllServiceType(pageNo, pageSize);
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
