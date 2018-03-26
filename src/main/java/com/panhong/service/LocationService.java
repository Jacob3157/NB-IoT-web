package com.panhong.service;

import java.util.List;

import com.panhong.model.Location;

public interface LocationService {
	
	public void add(Location location);
	
	public void delete(Location location);
	
	public void update(Location location);
	
	public List<Location> getBuildings(String school,String compus);

}
