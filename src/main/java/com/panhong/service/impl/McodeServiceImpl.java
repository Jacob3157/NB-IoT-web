package com.panhong.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.panhong.dao.McodeDao;
import com.panhong.model.Mcode;
import com.panhong.service.McodeService;

@Service("mcodeService")
public class McodeServiceImpl implements McodeService{
	
	@Resource
	private McodeDao mcodeDao;

	public McodeDao getMcodeDao() {
		return mcodeDao;
	}

	public void setMcodeDao(McodeDao mcodeDao) {
		this.mcodeDao = mcodeDao;
	}

	@Override
	public String addMcode(Mcode mcode) {
		String result=mcodeDao.addMcode(mcode);
		return result;
	}

	@Override
	public void deleteMcode(Mcode mcode) {
		mcodeDao.deleteMcode(mcode);
	}

	@Override
	public void updateMcode(Mcode mcode) {
		mcodeDao.updateMcode(mcode);
	}

	@Override
	public Mcode getMcodeBycode(String code) {
		Mcode mcode=mcodeDao.getMcodeBycode(code);
		return mcode;
	}

	@Override
	public List<Mcode> getAllMcode() {
		return mcodeDao.getAllMcode();
	}

	@Override
	public Mcode getLatestMcode() {
		return mcodeDao.getLatestMcode();
	}

}
