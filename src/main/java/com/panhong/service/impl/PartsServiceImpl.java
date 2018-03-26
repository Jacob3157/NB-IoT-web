package com.panhong.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.panhong.dao.PartsDao;
import com.panhong.model.Machine;
import com.panhong.model.Parts;
import com.panhong.service.PartsService;
import com.panhong.util.PageBean;

@Service
public class PartsServiceImpl implements PartsService {
	
	@Resource
	private PartsDao partsDao;

	public PartsDao getPartsDao() {
		return partsDao;
	}

	public void setPartsDao(PartsDao partsDao) {
		this.partsDao = partsDao;
	}
	
	@Override
	public void addParts(Parts parts) {
		partsDao.addParts(parts);
	}

	@Override
	public void updateParts(Parts parts) {
		partsDao.updateParts(parts);
	}

	@Override
	public PageBean getPartsById(String Id) {
		String hql = "select count(*)from Parts where partsId='"+Id+"'";
        int count = partsDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(10, count); // 总页数
        int currentPage = PageBean.countCurrentPage(1);
        List<Parts> list = new ArrayList<Parts>();
        list.add(partsDao.getPartsById(Id));
        // 把分页信息保存到Bean中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(10);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotal(count);
        pageBean.setTotalPage(totalPage);
        pageBean.setRows(list);
        pageBean.init();
        return pageBean;
	}

	@Override
	public PageBean getAllParts(int pageNo, int pageSize) {
		String hql = "select count(*)from Parts";
        int count = partsDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(10, count); // 总页数
        int currentPage = PageBean.countCurrentPage(pageNo);
        List<Parts> list = partsDao.getAllParts(pageNo, pageSize);
        // 把分页信息保存到Bean中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(10);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotal(count);
        pageBean.setTotalPage(totalPage);
        pageBean.setRows(list);
        pageBean.init();
        return pageBean;
	}

	@Override
	public void deleteParts(Parts parts) {
		partsDao.deleteParts(parts);

	}

	@Override
	public Parts getById(String Id) {
		return partsDao.getPartsById(Id);
	}

	@Override
	public List<Parts> getAllParts() {
		return partsDao.getAllParts();
	}

	@Override
	public Parts getByName(String name) {
		return partsDao.getPartsByName(name);
	}

}
