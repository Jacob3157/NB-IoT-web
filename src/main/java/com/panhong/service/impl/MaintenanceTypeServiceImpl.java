package com.panhong.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.panhong.dao.MaintenanceTypeDao;
import com.panhong.model.MaintenanceType;
import com.panhong.model.User;
import com.panhong.service.MaintenanceTypeService;
import com.panhong.util.PageBean;


@Service
public class MaintenanceTypeServiceImpl implements MaintenanceTypeService {
	
	@Resource
	private MaintenanceTypeDao maintenanceTypeDao;

	public MaintenanceTypeDao getMaintenanceTypeDao() {
		return maintenanceTypeDao;
	}

	public void setMaintenanceTypeDao(MaintenanceTypeDao maintenanceTypeDao) {
		this.maintenanceTypeDao = maintenanceTypeDao;
	}

	@Override
	public void addMaintenanceType(MaintenanceType maintenanceType) {
		maintenanceTypeDao.addMaintenanceType(maintenanceType);
	}

	@Override
	public void updateMaintenanceType(MaintenanceType maintenanceType) {
		maintenanceTypeDao.updateMaintenanceType(maintenanceType);
	}

	@Override
	public void deleteMaintenanceType(MaintenanceType maintenanceType) {
		maintenanceTypeDao.deleteMaintenanceType(maintenanceType);
	}

	@Override
	public MaintenanceType getMaintenanceTypeByName(String name) {
		return maintenanceTypeDao.getMaintenanceTypeByName(name);
	}

	@Override
	public PageBean getAllMaintenanceType(int pageNo, int pageSize) {
		String hql = "select count(*) from MaintenanceType";
        int count = maintenanceTypeDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        int currentPage = PageBean.countCurrentPage(pageNo);
        List<MaintenanceType> list = maintenanceTypeDao.getAllMaintenanceType(pageNo, pageSize);
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
