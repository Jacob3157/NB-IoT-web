package com.panhong.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.panhong.model.Admin;

public class LoginInterceptor implements HandlerInterceptor {
	
	//日志显示
	private static final Log logger = LogFactory
	            .getLog(LoginInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception e)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
		
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		if(admin==null){
			//重定向  
			 String ip = request.getHeader("x-forwarded-for");  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("Proxy-Client-IP");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("WL-Proxy-Client-IP");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_CLIENT_IP");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getRemoteAddr();  
	        }
	        logger.info("此IP地址试图访问系统："+ip+"  "+ip);
			response.sendRedirect(request.getContextPath()+"/check/index");
			return false;
		}
		
		String url=request.getRequestURL().toString();
		if(url.indexOf("?")!=-1){
			url=url.split("?")[0];
		}
//		logger.info(url+new Date());
		if(url.contains("_app")){
			return true;
		}
		
		return true;
	}

}
