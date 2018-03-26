package com.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.panhong.model.UserProperty;
import com.panhong.service.UserPropertyService;


@ContextConfiguration(locations="classpath:spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserPropertyServiceTest {
	
	@Resource
	private UserPropertyService userPropertyService;
	
	
	@Test
	public void testSave(){
		UserProperty userProperty=new UserProperty("免费用户",0.0,"1");
		userPropertyService.addUserProperty(userProperty);
		
		
		
		
	}
	
	@Test
	public void testGet(){
		List<UserProperty> list=userPropertyService.getAllUserProperty();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getGradeName());
		}
		UserProperty userProperty=userPropertyService.getUserPropertyById(1);
		System.out.println(userProperty.getGradeName());
	}
	
	@Test
	public void testDelete(){
		UserProperty userProperty=userPropertyService.getUserPropertyById(1);
		userPropertyService.deleteProperty(userProperty);
	}

}
