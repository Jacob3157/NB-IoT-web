package com.panhong.service;

import java.util.List;

import com.panhong.model.Auth_Function;

public interface FunctionService {
	
	public int add(Auth_Function function);
	
	public void delete(Auth_Function function);
	
	public void update(Auth_Function function);
	
	public List<Auth_Function> getFunctions(int pageNo,int pageSize,int parentId);
	
	public List<Auth_Function> getAllFunctions();
	
	public Auth_Function getFunctionByName(String name);

}
