package com.panhong.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panhong.model.User;
import com.panhong.service.InfoReleaseService;
import com.panhong.service.MachineService;
import com.panhong.service.MaintenanceRecordService;
import com.panhong.service.MaintenanceTypeService;
import com.panhong.service.OperatingInfoService;
import com.panhong.service.OrderRecordService;
import com.panhong.service.SystemRecordService;
import com.panhong.service.UserPropertyService;
import com.panhong.service.UserService;
import com.panhong.util.HttpUtil;


@Controller
@RequestMapping(value="/delete")
public class DeleteController {
	
	@Resource
	public UserService userService;
	
	@Resource
	public UserPropertyService userPropertyService;
	
	@Resource
	public InfoReleaseService infoReleaseService;
	
	@Resource
	public MachineService machineService;
	
	@Resource
	public MaintenanceRecordService maintenanceRecordService;
	
	@Resource
	public OperatingInfoService operatingInfoService;
	
	@Resource
	public OrderRecordService orderRecordService;
	
	@Resource
	public SystemRecordService systemRecordService;
	
	@Resource
	public MaintenanceTypeService maintenanceTypeService;
	//日志显示
	private static final Log logger = LogFactory
            .getLog(DeleteController.class);
	
	//用户取消关注
	@RequestMapping(value="/user")
	public @ResponseBody void deleteUser(HttpServletRequest request){
		User user_get=HttpUtil.getUserBean(request, User.class);
		User user=userService.getUserById(user_get.getOpenid());
		user.setValid(0);//0表示用户取消关注
		user.setUnfollowDate(new Date());
		userService.updateUser(user);
	}

}
