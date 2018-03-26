package com.panhong.service;

import java.util.List;

import com.panhong.model.OpenRecord;

public interface OpenRecordService {
	
	public void addOpenRecord(OpenRecord openRecord);
	
	public OpenRecord getLastOpenRecordBydeviceId(String deviceId);  
	//获取总记录数
	public int getCount(String hql);

}
