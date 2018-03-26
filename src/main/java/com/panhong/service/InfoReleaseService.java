package com.panhong.service;

import java.sql.Date;
import java.util.List;

import com.panhong.model.InfoRelease;

public interface InfoReleaseService {
	
//	增加内容发布信息
	public void addInfoRelease(InfoRelease info);
//	更新内容发布信息
	public void updateInfoRelease(InfoRelease info);
//	删除内容发布信息
	public void deleteInfoRelease(InfoRelease info);
//	获取所有内容发布历史
	public List<InfoRelease> getAllinfoRelease();
//	获取指定时间之后的发布的信息
	public List<InfoRelease> getInfoReleaseByDate(Date date);
//	通过发布人获取指定信息
	public List<InfoRelease> getInfoReleaseByPromulgator(String promulgator);

}
