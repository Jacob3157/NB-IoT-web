package com.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.panhong.model.OperatingInfo;
import com.panhong.service.OperatingInfoService;


@ContextConfiguration(locations="classpath:spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class OperatingServiceTest {
	
	@Resource
	private OperatingInfoService operatingInfoService;
	
	@Test
	public void testSave(){
		OperatingInfo operatingInfo=new OperatingInfo("上海大学", "宝山校区", 3.0);
		operatingInfoService.addOperatingInfo(operatingInfo);
		
		operatingInfo=new OperatingInfo("上海大学", "延长校区", 3.0);
		operatingInfoService.addOperatingInfo(operatingInfo);
		
		operatingInfo=new OperatingInfo("北京大学", "宝山校区", 3.0);
		operatingInfoService.addOperatingInfo(operatingInfo);
		
		operatingInfo=new OperatingInfo("北京大学", "延长校区", 3.0);
		operatingInfoService.addOperatingInfo(operatingInfo);
		
		
		
		
	}
	

}
