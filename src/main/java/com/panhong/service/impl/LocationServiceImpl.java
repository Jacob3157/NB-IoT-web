package com.panhong.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.panhong.dao.LocationDao;
import com.panhong.model.Location;
import com.panhong.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Resource
	private LocationDao locationDao;

	public LocationDao getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	@Override
	public void add(Location location) {
		locationDao.add(location);
	}

	@Override
	public void delete(Location location) {
		locationDao.delete(location);
	}

	@Override
	public void update(Location location) {
		locationDao.update(location);
	}

	@Override
	public List<Location> getBuildings(String school, String compus) {
		return locationDao.getBuildings(school, compus);
	}

}
