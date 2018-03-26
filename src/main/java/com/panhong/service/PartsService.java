package com.panhong.service;

import java.util.List;

import com.panhong.model.Parts;
import com.panhong.util.PageBean;

public interface PartsService {
	//addParts
	public void addParts(Parts parts);
	//updateParts
	public void updateParts(Parts parts);
	//getParts
	public PageBean getPartsById(String Id);
	public Parts getById(String Id);
	
	public Parts getByName(String name);
	//getAllParts
	public PageBean getAllParts(int pageNo, int pageSize);
	
	//getAllParts
	public List<Parts> getAllParts();
	//delete parts
	public void deleteParts(Parts parts);

}
