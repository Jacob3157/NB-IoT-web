package com.panhong.thread;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.panhong.model.Machine;
import com.panhong.service.MachineService;
import com.panhong.util.APPContextUtil;

public class MachineStatusThread implements Runnable{

	@Override
	public void run() {
		ApplicationContext context=APPContextUtil.getContext();
		MachineService machineService=context.getBean(MachineService.class);
		System.out.println(new Date()+"MachineService");
		while(true){
			List<Machine> machines=machineService.getMachineByStatus("工作中");
			if(machines!=null && !machines.isEmpty() ){
				for(Machine machine:machines){
					Date date=machine.getLatestWorkingTime();
					if(date!=null && new Date().getTime()>=(date.getTime()+35*60*1000)){
						machine.setStatus("待机 ");
						machineService.updateMachine(machine);
					};
				}
			}
			
			try {
				Thread.sleep(2*60*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}
