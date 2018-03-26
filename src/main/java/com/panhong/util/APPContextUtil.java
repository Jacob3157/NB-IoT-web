package com.panhong.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class APPContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext context;
	

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		APPContextUtil.context=context;
	}
	
	public static ApplicationContext getContext(){
		return context;
	}

}
