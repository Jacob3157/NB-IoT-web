package com.panhong.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.panhong.dao.OpenRecordDao;
import com.panhong.model.OpenRecord;
import com.panhong.service.OpenRecordService;

@Service
public class OpenRecordServiceImpl implements OpenRecordService {
	
	private static final Log logger = LogFactory
            .getLog(OpenRecordServiceImpl.class);
	
	@Resource
	private OpenRecordDao openRecordDao;
	
	
	public OpenRecordDao getOpenRecordDao() {
		return openRecordDao;
	}

	public void setOpenRecordDao(OpenRecordDao openRecordDao) {
		this.openRecordDao = openRecordDao;
	}

	@Override
	public void addOpenRecord(OpenRecord openRecord) {
		this.openRecordDao.addOpenRecord(openRecord);

	}

	@Override
	public OpenRecord getLastOpenRecordBydeviceId(String deviceId) {
		
		return openRecordDao.getLastOpenRecordBydeviceId(deviceId);
		
	}

	@Override
	public int getCount(String hql) {
		return openRecordDao.getCount(hql);
	}

}
