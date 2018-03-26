package com.test.service;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.panhong.model.User;
import com.panhong.model.UserProperty;
import com.panhong.service.UserPropertyService;
import com.panhong.service.UserService;


@ContextConfiguration(locations="classpath:spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
	
	@Resource
	public UserService userService;
	
	@Resource
	private UserPropertyService userPropertyService;
	
	@Test
	public void testSave(){
		
//		UserProperty userProperty=userPropertyService.getUserPropertyById(4);
		
//		User user=new User("alaricZhang", 0, userProperty, new Date(), 1);
//		User user=userService.getUserById("alariczhang");
//		
//		UserProperty userProperty=user.getUserProperty();
//		System.out.println(userProperty.getGradeName());
		
//		userService.addUser(user);
		System.out.println("aaa");
	}

}
