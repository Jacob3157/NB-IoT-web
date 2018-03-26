package com.panhong.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.panhong.dao.MachineDao;
import com.panhong.model.Machine;
import com.panhong.model.User;
import com.panhong.service.MachineService;
import com.panhong.util.PageBean;

@Service("machineService")
public class MachineServiceImpl implements MachineService {
	
	@Resource
	private MachineDao machineDao;

	public MachineDao getMachineDao() {
		return machineDao;
	}

	public void setMachineDao(MachineDao machineDao) {
		this.machineDao = machineDao;
	}

	@Override
	public void addMachine(Machine machine) {
		machineDao.addMachine(machine);
	}

	@Override
	public void updateMachine(Machine machine) {
		machineDao.updateMachine(machine);
	};

	@Override
	public void deleteMachine(Machine machine) {
		machineDao.deleteMachine(machine);
	}

	@Override
	public Machine getMachineById(String machineID) {
		return machineDao.getMachineById(machineID);
	}

	@Override
	public PageBean getAllMachine(int pageNo,int pageSize) {
		String hql="select count(*) from Machine";
		int count = machineDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        int currentPage = PageBean.countCurrentPage(pageNo);
        List<Machine> list = machineDao.getAllMachine(pageNo, pageSize);
        // 把分页信息保存到Bean中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotal(count);
        pageBean.setTotalPage(totalPage);
        pageBean.setRows(list);
        pageBean.init();
        return pageBean;
	}

	@Override
	public PageBean getMachineBySchool(String school,int pageNo,int pageSize) {
		String hql="select count(*) from Machine where operatinginfo.school='"+school+"'";
		int count = machineDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        int currentPage = PageBean.countCurrentPage(pageNo);
        List<Machine> list = machineDao.getMachineBySchool(school, pageNo, pageSize);
        // 把分页信息保存到Bean中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotal(count);
        pageBean.setTotalPage(totalPage);
        pageBean.setRows(list);
        pageBean.init();
        return pageBean;
	}

	@Override
	public List<Machine> getMachineByCompus(String compus) {
		return machineDao.getMachineByCompus(compus);
	}

	@Override
	public List<Machine> getMachineBySchAndCom(String school, String compus) {
		return machineDao.getMachineBySchAndCom(school, compus);
	}

	@Override
	public PageBean getMachineByMachineName(String machineName) {
		String hql = "select count(*)from Machine where machineName='"+machineName+"'";
        int count = machineDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(10, count); // 总页数
        int currentPage = PageBean.countCurrentPage(1);
        List<Machine> list = new ArrayList<Machine>();
        list.add(machineDao.getMachineByMachineName(machineName));
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
	public PageBean getMachinesBySchAndCom(String school, String compus,int pageNo,int pageSize) {
		String hql = "select count(*) from Machine where operatinginfo.school='"+school+"' and operatinginfo.compus='"+compus+"'";
        int count = machineDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        int currentPage = PageBean.countCurrentPage(pageNo);
        List<Machine> list = machineDao.getMachinesBySchAndCom(school, compus, pageNo, pageSize);
        // 把分页信息保存到Bean中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotal(count);
        pageBean.setTotalPage(totalPage);
        pageBean.setRows(list);
        pageBean.init();
        return pageBean;
	}

	@Override
	public List<Machine> getMachineByStatus(String status) {
		return machineDao.getMachineByStatus(status);
	}

	@Override
	public PageBean getMachineByStatus(String status, int pageNo, int pageSize) {
		String hql = "select count(*) from Machine where status='"+status+"'";
        int count = machineDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        int currentPage = PageBean.countCurrentPage(pageNo);
        List<Machine> list = machineDao.getMachineByStatus(status, pageNo, pageSize);
        // 把分页信息保存到Bean中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotal(count);
        pageBean.setTotalPage(totalPage);
        pageBean.setRows(list);
        pageBean.init();
        return pageBean;
	}

	@Override
	public PageBean getMachineByBuilding(String school, String compus,
			String building, int pageNo, int pageSize) {
		String hql = "select count(*) from Machine where operatinginfo.school='"+school+"' and operatinginfo.compus='"+compus+"' and building='"+building+"'";
        int count = machineDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        int currentPage = PageBean.countCurrentPage(pageNo);
        List<Machine> list = machineDao.getMachineByBuilding(school,compus,building, pageNo, pageSize);
        // 把分页信息保存到Bean中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotal(count);
        pageBean.setTotalPage(totalPage);
        pageBean.setRows(list);
        pageBean.init();
        return pageBean;
	}

}
