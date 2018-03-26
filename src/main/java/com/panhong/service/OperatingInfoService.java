package com.panhong.service;

import java.util.List;

import com.panhong.model.OperatingInfo;
import com.panhong.util.DataBean;

public interface OperatingInfoService {
//	新增运营信息
	public void addOperatingInfo(OperatingInfo opratingInfo);
//	更新运营信息
	public void updateOperatingInfo(OperatingInfo opratingInfo);
//	删除运营信息
	public void deleteOperatingInfo(OperatingInfo opratingInfo);
//	通过学校获取运营信息
	public DataBean getOperatingInfoBySchool(String school);
//	通过校区获取运营信息
	public DataBean getOperatingInfoByCompus(String compus);
//	通过学校和校区同时获取运营信息
	public OperatingInfo getOperatingInfoBySchoolAndCompus(String school,String compus);
//	获取所有运营信息
	public DataBean getAllOperatingInfo();

}
