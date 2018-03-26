package com.panhong.thread;

import java.util.TimerTask;

import org.springframework.context.ApplicationContext;

import com.panhong.model.Machine;
import com.panhong.service.MachineService;
import com.panhong.service.TokenInfoService;
import com.panhong.util.APPContextUtil;
import com.panhong.util.HttpUtil;

public class RebootTask extends TimerTask{
	
	private String openid;
	
	private String machineId;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	
	public RebootTask(String openid,String machineId){
		this.openid=openid;
		this.machineId=machineId;
	}

	@Override
	public void run() {
		ApplicationContext context=APPContextUtil.getContext();
		MachineService machineService=(MachineService) context.getBean("machineService");
		Machine machine=machineService.getMachineById(machineId);
		if(machine.getStatus().equals("待机")){
			HttpUtil.rebootMachine(openid, machineId);
		}
	}

}
