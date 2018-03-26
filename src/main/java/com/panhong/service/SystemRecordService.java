package com.panhong.service;

import java.util.List;

import com.panhong.model.SystemRecord;

public interface SystemRecordService {
	
//	增加记录
	public void addSystemRecord(SystemRecord systemRecord);
//	更新记录
	public void updateSystemRecord(SystemRecord systemRecord);
//	删除记录
	public void deleteSystemRecord(SystemRecord systemRecord);
//	获取记录
	public List<SystemRecord> getSystemRecordByResponsibleWorker(String name);

}
