package com.panhong.dao;

import java.util.List;

import com.panhong.model.OpenRecord;

public interface OpenRecordDao {
	
	public void addOpenRecord(OpenRecord openRecord);
	
	public OpenRecord getLastOpenRecordBydeviceId(String deviceId);  
	
	//获取总记录数
	public int getCount(String hql);

}
