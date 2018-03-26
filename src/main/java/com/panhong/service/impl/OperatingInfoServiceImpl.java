package com.panhong.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.panhong.dao.OperatingInfoDAO;
import com.panhong.model.OperatingInfo;
import com.panhong.service.OperatingInfoService;
import com.panhong.util.DataBean;


@Service
public class OperatingInfoServiceImpl implements OperatingInfoService {
	
	@Resource
	private OperatingInfoDAO operatingInfoDAO;

	public OperatingInfoDAO getOperatingInfoDAO() {
		return operatingInfoDAO;
	}

	public void setOperatingInfoDAO(OperatingInfoDAO operatingInfoDAO) {
		this.operatingInfoDAO = operatingInfoDAO;
	}

	@Override
	public void addOperatingInfo(OperatingInfo opratingInfo) {
		operatingInfoDAO.addOperatingInfo(opratingInfo);
	}

	@Override
	public void updateOperatingInfo(OperatingInfo opratingInfo) {
		operatingInfoDAO.updateOperatingInfo(opratingInfo);
	}

	@Override
	public void deleteOperatingInfo(OperatingInfo opratingInfo) {
		operatingInfoDAO.deleteOperatingInfo(opratingInfo);
	}

	@Override
	public DataBean getOperatingInfoBySchool(String school) {
		String hql = "select count(*) from OperatingInfo where school='"+school+"'";
        int count = operatingInfoDAO.getCount(hql); // 总记录数 
        List rows=operatingInfoDAO.getOperatingInfoBySchool(school);
        DataBean dataBean=new DataBean();
        dataBean.setRows(rows);
        dataBean.setTotal(count);
        return dataBean;
        
	}

	@Override
	public DataBean getOperatingInfoByCompus(String compus) {
		String hql = "select count(*) from OperatingInfo where compus='"+compus+"'";
        int count = operatingInfoDAO.getCount(hql); // 总记录数 
        List rows=operatingInfoDAO.getOperatingInfoByCompus(compus);
        DataBean dataBean=new DataBean();
        dataBean.setRows(rows);
        dataBean.setTotal(count);
        return dataBean;
	}

	@Override
	public OperatingInfo getOperatingInfoBySchoolAndCompus(String school,
			String compus) {
		return operatingInfoDAO.getOperatingInfoBySchoolAndCompus(school, compus);
	}

	@Override
	public DataBean getAllOperatingInfo() {
		String hql = "select count(*) from OperatingInfo";
        int count = operatingInfoDAO.getCount(hql); // 总记录数 
        List rows=operatingInfoDAO.getAllOperatingInfo();
        DataBean dataBean=new DataBean();
        dataBean.setRows(rows);
        dataBean.setTotal(count);
        return dataBean;
	}

}
