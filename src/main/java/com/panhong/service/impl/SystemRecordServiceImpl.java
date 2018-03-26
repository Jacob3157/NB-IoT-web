package com.panhong.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.panhong.dao.SystemRecordDao;
import com.panhong.model.SystemRecord;
import com.panhong.service.SystemRecordService;


@Service
public class SystemRecordServiceImpl implements SystemRecordService {
	
	
	@Resource
	private SystemRecordDao systemRecordDao;

	public SystemRecordDao getSystemRecordDao() {
		return systemRecordDao;
	}

	public void setSystemRecordDao(SystemRecordDao systemRecordDao) {
		this.systemRecordDao = systemRecordDao;
	}

	@Override
	public void addSystemRecord(SystemRecord systemRecord) {
		systemRecordDao.addSystemRecord(systemRecord);
	}

	@Override
	public void updateSystemRecord(SystemRecord systemRecord) {
		systemRecordDao.updateSystemRecord(systemRecord);
	}

	@Override
	public void deleteSystemRecord(SystemRecord systemRecord) {
		systemRecordDao.deleteSystemRecord(systemRecord);
	}

	@Override
	public List<SystemRecord> getSystemRecordByResponsibleWorker(String name) {
		return systemRecordDao.getSystemRecordByResponsibleWorker(name);
	}

}
