package com.panhong.service;

import java.util.List;

import com.panhong.model.Machine;
import com.panhong.util.PageBean;

public interface MachineService {
	
//	添加机器信息
	public void addMachine(Machine machine);
//	更新机器信息
	public void updateMachine(Machine machine);
//	删除机器信息
	public void deleteMachine(Machine machine);
//	通过ID获取机器信息
	public Machine getMachineById(String machineID);
//	获取所有机器信息
	public PageBean getAllMachine(int pageNo,int pageSize);
//	通过学校获取机器信息
	public PageBean getMachineBySchool(String school,int pageNo,int pageSize);
//	通过校区获取机器信息
	public List<Machine> getMachineByCompus(String compus);
//	通过学校和校区获取机器信息
	public List<Machine> getMachineBySchAndCom(String school,String compus);
	
	public PageBean getMachinesBySchAndCom(String school,String compus,int pageNo,int pageSize);
//	通过机器编号获取机器
	public PageBean getMachineByMachineName(String machineName);
//	通过status获取机器信息
	public List<Machine> getMachineByStatus(String status);
	
	public PageBean getMachineByStatus(String status,int pageNo,int pageSize);
	
	public PageBean getMachineByBuilding(String school, String compus,String building, int pageNo, int pageSize);

}
