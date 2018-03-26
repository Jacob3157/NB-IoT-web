package com.panhong.service.impl;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.panhong.dao.InfoReleaseDao;
import com.panhong.model.InfoRelease;
import com.panhong.service.InfoReleaseService;


@Service
public class InfoReleaseServiceImpl implements InfoReleaseService {
	
	@Resource
	public InfoReleaseDao infoReleaseDao;

	public InfoReleaseDao getInfoReleaseDao() {
		return infoReleaseDao;
	}

	public void setInfoReleaseDao(InfoReleaseDao infoReleaseDao) {
		this.infoReleaseDao = infoReleaseDao;
	}

	@Override
	public void addInfoRelease(InfoRelease info) {
		infoReleaseDao.addInfoRelease(info);
	}

	@Override
	public void updateInfoRelease(InfoRelease info) {
		infoReleaseDao.updateInfoRelease(info);
	}

	@Override
	public void deleteInfoRelease(InfoRelease info) {
		infoReleaseDao.deleteInfoRelease(info);
	}

	@Override
	public List<InfoRelease> getAllinfoRelease() {
		return infoReleaseDao.getAllinfoRelease();
	}

	@Override
	public List<InfoRelease> getInfoReleaseByDate(Date date) {
		return infoReleaseDao.getInfoReleaseByDate(date);
	}

	@Override
	public List<InfoRelease> getInfoReleaseByPromulgator(String promulgator) {
		return infoReleaseDao.getInfoReleaseByPromulgator(promulgator);
	}

}
