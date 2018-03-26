package com.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.panhong.model.Machine;
import com.panhong.model.OperatingInfo;
import com.panhong.service.MachineService;
import com.panhong.service.OperatingInfoService;



@ContextConfiguration(locations="classpath:spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MachineServiceTest {
	
	@Resource
	private MachineService machineService;
	
	@Resource
	private OperatingInfoService operatingInfoService;
	
	@Test
	public void testSave(){
		
		/*List<OperatingInfo> list =operatingInfoService.getOperatingInfoBySchool("上海大学");
		
		Machine machine=new Machine("sd0000001", list.get(0),"自动型号");
		machineService.addMachine(machine);
		
		machine=new Machine("sd0000002", list.get(1),"自动型号");
		machineService.addMachine(machine);*/
		
//		List<Machine> maList=machineService.getMachineBySchool("上海大学");
//		System.out.println(maList.size()+"aaaa");
//		
//		for(int i=0;i<maList.size();i++){
//			System.out.println(maList.get(i).getMachineID());
//		}
//		
	}

}
