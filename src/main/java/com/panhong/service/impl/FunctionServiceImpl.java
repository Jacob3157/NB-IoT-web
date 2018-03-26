package com.panhong.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.panhong.dao.FunctionDao;
import com.panhong.model.Auth_Function;
import com.panhong.service.FunctionService;

@Service
public class FunctionServiceImpl implements FunctionService {
	
	@Resource
	private FunctionDao functionDao;

	@Override
	public int add(Auth_Function function) {
		return functionDao.add(function);
	}

	@Override
	public void delete(Auth_Function function) {
		functionDao.delete(function);
	}

	@Override
	public void update(Auth_Function function) {
		functionDao.update(function);
	}

	@Override
	public List<Auth_Function> getFunctions(int pageNo, int pageSize,
			int parentId) {
		return functionDao.getFunctions(pageNo, pageSize, parentId);
	}

	@Override
	public List<Auth_Function> getAllFunctions() {
		return functionDao.getAllFunctions();
	}

	@Override
	public Auth_Function getFunctionByName(String name) {
		return functionDao.getFunctionByName(name);
	}

}
