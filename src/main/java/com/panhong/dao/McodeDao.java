package com.panhong.dao;

import java.util.List;

import com.panhong.model.Mcode;

public interface McodeDao {
	
	public String addMcode(Mcode mcode);
	
	public void deleteMcode(Mcode mcode);
	
	public void updateMcode(Mcode mcode);
	
	public Mcode getMcodeBycode(String Mcode);
	
	public List<Mcode> getAllMcode();
	
	public Mcode getLatestMcode();

}
