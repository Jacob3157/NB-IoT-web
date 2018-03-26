package com.panhong.dao;

import java.util.List;

import com.panhong.model.Parts;

public interface PartsDao {
	//addParts
	public void addParts(Parts parts);
	//updateParts
	public void updateParts(Parts parts);
	//getParts
	public Parts getPartsById(String Id);
	//getAllParts
	public List<Parts> getAllParts(int pageNo, int pageSize);
	//getAllParts
	public List<Parts> getAllParts();
	//delete parts
	public void deleteParts(Parts parts);
	
	public Parts getPartsByName(String name);
	
	public int getCount(String hql);

}
