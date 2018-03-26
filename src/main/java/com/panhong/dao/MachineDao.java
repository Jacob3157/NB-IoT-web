package com.panhong.dao;

import java.util.List;

import com.panhong.model.Machine;

public interface MachineDao {
	
//	添加机器信息
	public void addMachine(Machine machine);
//	更新机器信息
	public void updateMachine(Machine machine);
//	删除机器信息
	public void deleteMachine(Machine machine);
//	通过ID获取机器信息
	public Machine getMachineById(String machineID);
//	获取所有机器信息
	public List<Machine> getAllMachine(int pageNo,int pageSize);
//	通过学校获取机器信息
	public List<Machine> getMachineBySchool(String school,int pageNo,int pageSize);
//	通过校区获取机器信息
	public List<Machine> getMachineByCompus(String compus);
//	通过学校和校区获取机器信息
	public List<Machine> getMachineBySchAndCom(String school,String compus);
	
	public List<Machine> getMachinesBySchAndCom(String school,String compus,int pageNo,int pageSize);
//	通过机器编号获取机器
	public Machine getMachineByMachineName(String machineName);
//	获取机器总量
	public int getCount(String hql);
//	通过状态获取机器信息
	public List<Machine> getMachineByStatus(String status);
	
//	通过状态获取机器信息
	public List<Machine> getMachineByStatus(String status,int pageNo,int pageSize);
	
	public List<Machine> getMachineByBuilding(String school, String compus,
			String building, int pageNo, int pageSize);
	
	

}
