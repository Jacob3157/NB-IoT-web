package com.panhong.controller;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.panhong.model.Client;
import com.panhong.model.InfoRelease;
import com.panhong.model.Location;
import com.panhong.model.Machine;
import com.panhong.model.MaintenanceType;
import com.panhong.model.OperatingInfo;
import com.panhong.model.Parts;
import com.panhong.model.SystemRecord;
import com.panhong.model.User;
import com.panhong.model.UserProperty;
import com.panhong.model.NB.Command;
import com.panhong.service.BenefitService;
import com.panhong.service.InfoReleaseService;
import com.panhong.service.LocationService;
import com.panhong.service.MachineService;
import com.panhong.service.MaintenanceRecordService;
import com.panhong.service.MaintenanceTypeService;
import com.panhong.service.OperatingInfoService;
import com.panhong.service.OrderRecordService;
import com.panhong.service.PartsService;
import com.panhong.service.SystemRecordService;
import com.panhong.service.UserPropertyService;
import com.panhong.service.UserService;
import com.panhong.service.WelllidService;
import com.panhong.util.CommonUtil;
import com.panhong.util.DataBean;
import com.panhong.util.HttpUtil;
import com.panhong.util.PageBean;
import com.panhong.util.SingleUdpList;

@Controller
@RequestMapping(value="/select")
public class SelectController {
	
	@Resource
	public UserService userService;
	
	@Resource
	public BenefitService benefitService;
	
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
	
	@Resource
	public LocationService locationService;
	
	@Resource
	public PartsService partsService;
	
	@Resource
	public WelllidService welllidService;
	//日志显示
	private static final Log logger = LogFactory
            .getLog(SelectController.class);
	
	
	//获取单用户的全部订单
	@RequestMapping(value="/userOrder")
	public @ResponseBody PageBean getUserOrders(HttpServletRequest request,HttpServletResponse response){
		String openid=request.getParameter("openid");
		int pageNo=Integer.parseInt(request.getParameter("page"));
		int pageSize=Integer.parseInt(request.getParameter("rows"));
		PageBean pageBean= orderRecordService.getOrderRecordsByUser(openid, pageNo, pageSize);
		return pageBean;
	}
	
	//获取单用户的全部订单
	@RequestMapping(value="/timeOrder")
	public @ResponseBody PageBean getTimeOrders(HttpServletRequest request,HttpServletResponse response){
		String startTime=CommonUtil.formatTime(request.getParameter("startTime"));
		String endTime=CommonUtil.formatTime(request.getParameter("endTime"));
		int pageNo=Integer.parseInt(request.getParameter("page"));
		int pageSize=Integer.parseInt(request.getParameter("rows"));
		try{
			PageBean pageBean= orderRecordService.getOrderRecordByTime(pageNo, pageSize, startTime, endTime);
			return pageBean;
		}catch(Exception e){
			logger.info("异常"+new Date());
			e.printStackTrace();
			HttpUtil.sendInfoBack(response, "failure");
			return null;
		}
		
	}
	
	//获取单用户的全部订单
		@RequestMapping(value="/chargeback")
		public @ResponseBody JSONObject chargeback(HttpServletRequest request,HttpServletResponse response){
			String startTime=CommonUtil.formatTime(request.getParameter("startTime"));
			String endTime=CommonUtil.formatTime(request.getParameter("endTime"));
			try{
				JSONObject data= orderRecordService.getOrderRecordByTime(startTime, endTime);
				return data;
			}catch(Exception e){
				logger.info("异常"+new Date());
				e.printStackTrace();
				HttpUtil.sendInfoBack(response, "failure");
				return null;
			}
			
		}
		
	//获取全部用户的全部订单
	@RequestMapping(value="/allOrder")
	public @ResponseBody PageBean getAllOrders(HttpServletRequest request,HttpServletResponse response){
		int pageNo=Integer.parseInt(request.getParameter("page"));
		int pageSize=Integer.parseInt(request.getParameter("rows"));
		PageBean pageBean= orderRecordService.getAllOrders(pageNo, pageSize);
		return pageBean;
	}
	
	//获取所有机器信息
	@RequestMapping(value="/allMachines")
	public @ResponseBody PageBean getAllMachines(HttpServletRequest request){
		int pageNo=Integer.parseInt(request.getParameter("pageNO"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		PageBean pageBean =machineService.getAllMachine(pageNo, pageSize);
		System.out.println("aaa");
		return pageBean;
	}
	
	//通过学校获取洗衣机
	@RequestMapping(value="/schMachines")
	public @ResponseBody PageBean getschMachines(HttpServletRequest request,HttpServletResponse response){
		String school=request.getParameter("school");
		int pageNo=Integer.parseInt(request.getParameter("pageNO"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		if(school==null){
			HttpUtil.sendInfoBack(response, "参数错误");
			return null;
		}
		PageBean pageBean =machineService.getMachineBySchool(school, pageNo, pageSize);
		return pageBean;
	}
	
	//获取单一洗衣机信息并返回页面
	@RequestMapping(value="/singleMachine")
	public String getsingleMachine(HttpServletRequest request,Model model){
		String machineName=request.getParameter("machineName");
		if(machineName==null){
			return "erro";
		}
		Machine machine=(Machine) machineService.getMachineByMachineName(machineName).getRows().get(0);
		model.addAttribute("machine", machine);
		return "updateMachine";
	}
	//通过学校获取洗衣机
	@RequestMapping(value="/sinMachine")
	public @ResponseBody PageBean getsinMachine(HttpServletRequest request,HttpServletResponse response){
		String machineName=request.getParameter("machineName");
		int pageNo=Integer.parseInt(request.getParameter("pageNO"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		if(machineName==null){
			HttpUtil.sendInfoBack(response, "参数错误");
			return null;
		}
		PageBean pageBean =machineService.getMachineByMachineName(machineName);
		return pageBean;
	}
	
	
	@RequestMapping(value="/machineByBuilding")
	public @ResponseBody PageBean getMachinesByBuilding(HttpServletRequest request,HttpServletResponse response){
		String school=request.getParameter("school");
		String compus=request.getParameter("compus");
		String building=request.getParameter("building");
		int pageNo=Integer.parseInt(request.getParameter("pageNo"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		if(school==null || compus==null || building==null ){
			HttpUtil.sendInfoBack(response, "failure");
			return null;
		}
		try{
			
			PageBean pageBean =machineService.getMachineByBuilding(school,compus,building, pageNo, pageSize);
			return pageBean;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		}
	
	//通过学校获取洗衣机
	@RequestMapping(value="/machineByStatus")
	public @ResponseBody PageBean getMachinesByStatus(HttpServletRequest request,HttpServletResponse response){
		String status=request.getParameter("status");
		int pageNo=Integer.parseInt(request.getParameter("pageNo"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		if(status==null){
			HttpUtil.sendInfoBack(response, "参数错误");
			return null;
		}
		PageBean pageBean =machineService.getMachineByStatus(status, pageNo, pageSize);
		return pageBean;
		}
	//通过学校和校区获取洗衣机
	@RequestMapping(value="/comMachines")
	public @ResponseBody PageBean getcomMachines(HttpServletRequest request,HttpServletResponse response){
		String compus=request.getParameter("compus");
		String school=request.getParameter("school");
		int pageNo=Integer.parseInt(request.getParameter("pageNO"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		try{
			PageBean pageBean=machineService.getMachinesBySchAndCom(school, compus, pageNo, pageSize);
			return pageBean;
		}catch(Exception e){
			logger.info(new Date()+"获取洗衣机错误");
			HttpUtil.sendInfoBack(response, "请联系管理员！");
			e.printStackTrace();
			return null;
		}
		
	}
	
	//获取洗衣机名称
	@RequestMapping(value="/getMachineName")
	public @ResponseBody JSONObject getMachineName(HttpServletRequest request,HttpServletResponse response){
		String school=request.getParameter("school");
		String compus=request.getParameter("compus");
		if(school==null || compus==null){
			HttpUtil.sendInfoBack(response, "参数错误！");
			return null;
		}
		JSONObject json=new JSONObject();
		try{
			List<Machine> machine=machineService.getMachineBySchAndCom(school, compus);
			List<String> machineName=new ArrayList<String>();
			for(int i=0;i<machine.size();i++){
				machineName.add(machine.get(i).getMachineName());
			}
			json.put("machineName", machineName);
			return json;
			
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "数据库操作错误，请联系管理员！");
			return null;
		}
		
	}
	
	
	//获取洗衣机名称
	@RequestMapping(value="/getBuildings")
	public @ResponseBody JSONObject getBuildings(HttpServletRequest request,HttpServletResponse response){
		String school=request.getParameter("school");
		String compus=request.getParameter("compus");
		if(school==null || compus==null){
			
			HttpUtil.sendInfoBack(response, "failure");
			return null;
		}
		JSONObject json=new JSONObject();
		try{
			List<Location> location=locationService.getBuildings(school, compus);
			List<String> buildings=new ArrayList<String>();
			for(int i=0;i<location.size();i++){
				buildings.add(location.get(i).getBuilding());
			}
			json.put("buildings", buildings);
			return json;
			
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "failure");
			e.printStackTrace();
			return null;
		}
		
	}
	
	//获取所有运营信息
	@RequestMapping(value="/allOperating")
	public @ResponseBody DataBean getallOperatingInfo(HttpServletRequest request,HttpServletResponse response){
		try{
			DataBean dataBean=operatingInfoService.getAllOperatingInfo();
			return dataBean;
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "error");
			e.printStackTrace();
			return null;
		}
	}
	
	//通过学校获取运营信息
	@RequestMapping(value="/schOperating")
	public @ResponseBody DataBean getschOperatingInfo(HttpServletRequest request,HttpServletResponse response){
		String school=request.getParameter("school");
		try{
			DataBean dataBean=operatingInfoService.getOperatingInfoBySchool(school);
			return dataBean;
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "error");
			e.printStackTrace();
			return null;
		}
	}
		
	//通过校区获取运营信息
	@RequestMapping(value="/comOperating")
	public @ResponseBody DataBean getcomOperatingInfo(HttpServletRequest request,HttpServletResponse response){
		String compus=request.getParameter("compus");
		try{
			DataBean dataBean=operatingInfoService.getOperatingInfoByCompus(compus);
			return dataBean;
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "error");
			e.printStackTrace();
			return null;
		}
	}
	
	//通过学校和校区同时获取运营信息
	@RequestMapping(value="/cschcomOperating")
	public ModelAndView getsinOperatingInfo(HttpServletRequest request){
		String school=request.getParameter("school");
		String compus=request.getParameter("compus");
		OperatingInfo sinOperaringInfo=operatingInfoService.getOperatingInfoBySchoolAndCompus(school, compus);
		ModelAndView mv=new ModelAndView("operatingInfo", "operatingInfo", sinOperaringInfo);
		return mv;
	}
	
	//获取信息发布记录
	//获取所有信息发布记录
	@RequestMapping(value="/allInfo")
	public ModelAndView getAllinfoRelease(HttpServletRequest request){
		List<InfoRelease> allInfoRelease=infoReleaseService.getAllinfoRelease();
		ModelAndView mv=new ModelAndView("infoRelease", "infoRelease", allInfoRelease);
		return mv;
	}
	
	//获取某日期之前的发布信息
	@RequestMapping(value="/dateInfo")
	public ModelAndView getinfoReleaseByDate(HttpServletRequest request){
		String dateStr=request.getParameter("dateStr");
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date=formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<InfoRelease> dateInfoRelease=infoReleaseService.getInfoReleaseByDate((java.sql.Date) date);
		ModelAndView mv=new ModelAndView("infoRelease", "infoRelease", dateInfoRelease);
		return mv;
	}	
	
	//通过发布人获取发布信息
	@RequestMapping(value="/proInfo")
	public ModelAndView getInfoReleaseByPromulgator(HttpServletRequest request){
		String promulgator=request.getParameter("promulgator");
		List<InfoRelease> proInfoRelease=infoReleaseService.getInfoReleaseByPromulgator(promulgator);
		ModelAndView mv=new ModelAndView("infoRelease", "infoRelease", proInfoRelease);
		return mv;
	}
	
	//获取所有维护记录
	//通过机器获取维护记录
	@RequestMapping(value="/macMaintenanceRecord")
	public @ResponseBody PageBean getMaintenanceRecordByMachine(HttpServletRequest request,HttpServletResponse response){
		String machineName=request.getParameter("machineName");
		if(machineName==null){
			logger.info("参数为空");
			System.out.println("参数为空");
			return null;
		}
		int pageNo=Integer.parseInt(request.getParameter("pageNo"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		try{
			PageBean pageBean=maintenanceRecordService.getMaintenanceRecordByMachine(machineName, pageNo, pageSize);
			return pageBean;
		}catch(Exception e){
			e.printStackTrace();
			HttpUtil.sendInfoBack(response, "erro");
			return null;
		}
		
	}	
	
	//通过维护人获取维护记录
	@RequestMapping(value="/opeMaintenanceRecord")
	public @ResponseBody PageBean getMaintenanceRecordByOperator(HttpServletRequest request,HttpServletResponse response){
		String wechatID=request.getParameter("wechatID");
		if(wechatID==null){
			logger.info("参数为空");
			System.out.println("参数为空");
			HttpUtil.sendInfoBack(response, "erro");
			return null;
		}
		int pageNo=Integer.parseInt(request.getParameter("pageNo"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		try{
			PageBean pageBean=maintenanceRecordService.getMaintenanceRecordByOperator(wechatID, pageNo, pageSize);
			return pageBean;
		}catch(Exception e){
			e.printStackTrace();
			HttpUtil.sendInfoBack(response, "erro");
			return null;
		}
	}
	
	//通过日期筛选维护记录
	@RequestMapping(value="/dateMaintenanceRecord")
	public @ResponseBody PageBean getMaintenanceRecordByDate(HttpServletRequest request,HttpServletResponse response){
		String dateStr=request.getParameter("dateStr");
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date=formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		int pageNo=Integer.parseInt(request.getParameter("pageNo"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		try{
			PageBean pageBean=maintenanceRecordService.getMaintenanceRecordByDate(date, pageNo, pageSize);
			return pageBean;
		}catch(Exception e){
			e.printStackTrace();
			HttpUtil.sendInfoBack(response, "erro");
			return null;
		}		
	}
	
	//获取所有维护记录
	@RequestMapping(value="/allMaintenanceRecord")
	public @ResponseBody PageBean getAllMaintenanceRecord(HttpServletRequest request,HttpServletResponse response){
		int pageNo=Integer.parseInt(request.getParameter("pageNo"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		try{
			PageBean pageBean=maintenanceRecordService.getAllMaintenanceRecord(pageNo, pageSize);
			return pageBean;
		}catch(Exception e){
			e.printStackTrace();
			HttpUtil.sendInfoBack(response, "erro");
			return null;
		}				
	}
	
	//获取维护类型
	//获取所有维护类型
	@RequestMapping(value="/allMaintenanceType")
	public @ResponseBody PageBean getAllMaintenanceType(HttpServletRequest request){
		int pageNo=Integer.parseInt(request.getParameter("pageNo"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		PageBean pageBean=maintenanceTypeService.getAllMaintenanceType(pageNo,pageSize);
		return pageBean;		
	}
	
	//通过维护名获取维护类型
	@RequestMapping(value="/nameMaintenanceType")
	public @ResponseBody PageBean getMaintenanceTypeByName(HttpServletRequest request,HttpServletResponse response){
		String name=request.getParameter("name");
		try{
			MaintenanceType maintenanceTypes=maintenanceTypeService.getMaintenanceTypeByName(name);
			PageBean pageBean = new PageBean();
			pageBean.setPageSize(10);
			pageBean.setCurrentPage(1);
			pageBean.setTotal(1);
			pageBean.setTotalPage(1);
			List<MaintenanceType> list=new ArrayList<MaintenanceType>();
			list.add(maintenanceTypes);
			pageBean.setRows(list);
			pageBean.init();
			return pageBean;		
		}catch(Exception e){
			e.printStackTrace();
			HttpUtil.sendInfoBack(response, "服务器异常");
			return null;
		}
		
	}
	
	//获取系统升级记录信息
	//通过责任人获取记录
	@RequestMapping(value="/worSystemtRecord")
	public ModelAndView getSystemRecordByResponsibleWorker(HttpServletRequest request){
		String worker=request.getParameter("worker");
		List<SystemRecord> worSystemtRecord=systemRecordService.getSystemRecordByResponsibleWorker(worker);
		ModelAndView mv=new ModelAndView("systemRecord", "systemRecord", worSystemtRecord);
		return mv;
	}
	
	//获取用户等级信息
	//获取所有用户等级信息
	@RequestMapping(value="/allUserPro")
	public ModelAndView getAllUserProperty(HttpServletRequest request){
		List<UserProperty> allUserPro=userPropertyService.getAllUserProperty();
		ModelAndView mv=new ModelAndView("userProperty","userProperty",allUserPro);
		return mv;
	}
	
	//获取VIP等级信息
	@RequestMapping(value="/vipUserProperties")
	public ModelAndView getVIPUserProperty(HttpServletRequest request){
		String isVip=request.getParameter("isVIP");
		List<UserProperty> vipUserProperties=userPropertyService.getVIPUserProperty(isVip);
		ModelAndView mv=new ModelAndView("userProperty","userProperty",vipUserProperties);
		return mv;		
	}
	
	//通过等级名获取等级
	@RequestMapping(value="/sinUserProperty")
	public ModelAndView getUserPropertyByGradeName(HttpServletRequest request){
		String name=request.getParameter("gradeName");
		UserProperty sUserProperty=userPropertyService.getUserPropertyByGradeName(name);
		List<UserProperty> sinUserProperty=new ArrayList<UserProperty>();
		sinUserProperty.add(sUserProperty);
		ModelAndView mv=new ModelAndView("userProperty","userProperty",sinUserProperty);
		return mv;
	}
	
	//获取用户信息
	//获取所有用户信息
	@RequestMapping(value="/allUser")
	public @ResponseBody PageBean getAllUser(HttpServletRequest request,HttpServletResponse response){
		int pageNo=Integer.parseInt(request.getParameter("page"));
		int pageSize=Integer.parseInt(request.getParameter("rows"));
		try{
			PageBean pageBean=userService.getAllUser(pageNo, pageSize);
			return pageBean;
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "error");
			e.printStackTrace();
			return null;
		}
	}
	
	//获取所有用户信息
	@RequestMapping(value="/userinfo")
	public @ResponseBody DataBean userinfo(HttpServletRequest request,HttpServletResponse response){
		int pageNo=Integer.parseInt(request.getParameter("page"));
		int pageSize=Integer.parseInt(request.getParameter("rows"));
		String queryContent=request.getParameter("queryContent");
		int type=Integer.parseInt(request.getParameter("type"));
		DataBean dataBean=new DataBean();
		List<User> list=null;
		switch(type){
		case 0://通过openid查询用户信息
			User user=null;
			try{
				user=userService.getUserById(queryContent);
				if(user==null){
                //如果为空什么也不做					
				}else{
					user.setRemainingTimes(benefitService.getRemainingTimesByUser(queryContent));
				}
			}catch(Exception e){
				HttpUtil.sendInfoBack(response, "erro");
				e.printStackTrace();
				break;
			}
			list=new ArrayList<>();
			list.add(user);
			dataBean.setRows(list);
			dataBean.setTotal(1);
			break;
		case 1://姓名
			dataBean=userService.getByName(queryContent, pageNo, pageSize);
			list=dataBean.getRows();
			if(list.isEmpty()){
				//如果为空什么也不做
			}else{
				for(User u:list){
					u.setRemainingTimes(benefitService.getRemainingTimesByUser(u.getOpenid()));
				}
			}
			break;
		case 2://昵称
			dataBean=userService.getByNickName(queryContent, pageNo, pageSize);
			list=dataBean.getRows();
			if(list.isEmpty()){
				
			}else{
				for(User u:list){
					u.setRemainingTimes(benefitService.getRemainingTimesByUser(u.getOpenid()));
				}
			}
			break;
		case 3://学号
			dataBean=userService.getByStudentId(queryContent, pageNo, pageSize);
			list=dataBean.getRows();
			if(list.isEmpty()){
			
			}else{
				for(User u:list){
					u.setRemainingTimes(benefitService.getRemainingTimesByUser(u.getOpenid()));
				}
			}
			break;
		case 4://电话
			dataBean=userService.getByPhone(queryContent, pageNo, pageSize);
			list=dataBean.getRows();
			if(list.isEmpty()){
				
			}else{
				for(User u:list){
					u.setRemainingTimes(benefitService.getRemainingTimesByUser(u.getOpenid()));
				}
			}
			break;
		}
		return dataBean;
		
	}
	
	@RequestMapping(value="/UserByTime")
	public @ResponseBody PageBean getUserByTime(HttpServletRequest request,HttpServletResponse response){
		Date startTime=CommonUtil.format2Time(request.getParameter("startTime"));
		Date endTime=CommonUtil.format2Time(request.getParameter("endTime"));
		int pageNo=Integer.parseInt(request.getParameter("page"));
		int pageSize=Integer.parseInt(request.getParameter("rows"));
		try{
			PageBean pageBean=userService.getByFollowTime(startTime, endTime, pageNo, pageSize);
			return pageBean;
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "error");
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/UserByTimeAndProperty")
	public @ResponseBody PageBean getUserByTimeAndProperty(HttpServletRequest request,HttpServletResponse response){
		int userProperty=Integer.parseInt(request.getParameter("userProperty"));
		Date startTime=CommonUtil.format2Time(request.getParameter("startTime"));
		Date endTime=CommonUtil.format2Time(request.getParameter("endTime"));
		int pageNo=Integer.parseInt(request.getParameter("page"));
		int pageSize=Integer.parseInt(request.getParameter("rows"));
		try{
			PageBean pageBean=userService.getByFollowTimeAnduserProperty(userProperty, startTime, endTime, pageNo, pageSize);
			return pageBean;
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "error");
			e.printStackTrace();
			return null;
		}
	}
	//get user by name
	@RequestMapping(value="/userByName")
	public @ResponseBody DataBean getUserByName(HttpServletRequest request,HttpServletResponse response){
		String name=request.getParameter("name");
		int pageNo=Integer.parseInt(request.getParameter("page"));
		int pageSize=Integer.parseInt(request.getParameter("rows"));
		try{
			DataBean dataBean=userService.getByName(name, pageNo, pageSize);
			return dataBean;
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "error");
			e.printStackTrace();
			return null;
		}
	}
	
	
	//获取用户信息
	//获取所有用户信息
	@RequestMapping(value="/groupUser")
	public @ResponseBody PageBean getGroupUser(HttpServletRequest request,HttpServletResponse response){
		int userProperty=Integer.parseInt(request.getParameter("userProperty"));
		int pageNo=Integer.parseInt(request.getParameter("page"));
		int pageSize=Integer.parseInt(request.getParameter("rows"));
		try{
			PageBean pageBean=userService.getByUserProperty(userProperty, pageNo, pageSize);
			return pageBean;
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "erro");
			e.printStackTrace();
			return null;
		}
	}
	
	//获取组用户信息
	@RequestMapping(value="/groupUserName")
	public @ResponseBody JSONObject groupUserName(HttpServletRequest request,HttpServletResponse response){
		int userProperty=Integer.parseInt(request.getParameter("userProperty"));
		JSONObject json=new JSONObject();
		try{
			List<User> list=userService.getByUserProperty(userProperty);
			List<String> userName=new ArrayList<String>();
			List<String> userID=new ArrayList<String>();
			for(int i=0;i<list.size();i++){
				userName.add(list.get(i).getName());
				userID.add(list.get(i).getOpenid());
			}
			json.put("userName", userName);
			json.put("userId", userID);
			return json;
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "erro");
			e.printStackTrace();
			return null;
		}
	}
	//获取组用户信息
	@RequestMapping(value="/partsName")
	public @ResponseBody JSONObject partsName(HttpServletRequest request,HttpServletResponse response){
		JSONObject json=new JSONObject();
		try{
			List<Parts> list=partsService.getAllParts();
			List<String> name=new ArrayList<String>();
			for(int i=0;i<list.size();i++){
				name.add(list.get(i).getName());
			}
			json.put("name", name);
			return json;
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "erro");
			e.printStackTrace();
			return null;
		}
	}
	
	//获取所有可用用户
	@RequestMapping(value="/avaUser")
	public @ResponseBody PageBean getAvaUser(HttpServletRequest request,HttpServletResponse response){
		int pageNo=Integer.parseInt(request.getParameter("pageNo"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		try{
			PageBean pageBean=userService.getAllUser(pageNo, pageSize);
			return pageBean;
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "erro");
			e.printStackTrace();
			return null;
		}
	}		
	
	//通过用户名获取用户信息
	@RequestMapping(value="/sinUser")
	public ModelAndView getUserById(HttpServletRequest request){
		String openid=request.getParameter("openid");
		User user=userService.getUserById(openid);
		List<User> sinUser=new ArrayList<>();
		sinUser.add(user);
		ModelAndView mv=new ModelAndView("user","user",sinUser);
		return mv;		
	}
	
	@RequestMapping(value="/getSinUser")
	public @ResponseBody User getSinUser(HttpServletRequest request){
		String openid=request.getParameter("openid");
		User user=userService.getUserById(openid);
		return user;
	}
	
	//获取洗衣单价
	@RequestMapping(value="/price")
	public @ResponseBody JSONObject getPrice(@RequestBody JSONObject jsonObject){
		JSONObject jsonObject_re=new JSONObject();
		double price=0;
		User user=userService.getUserById(jsonObject.getString("openid"));
//		System.out.println(user.toString());
		double discount=user.getUserProperty().getDiscount();
//		System.out.println(discount);
//		System.out.println(jsonObject.getString("deviceid"));
		Machine machine=machineService.getMachineById(jsonObject.getString("deviceid"));
		if(machine.getStatus().equals("维修中")){
			jsonObject_re.put("price","failure");
			return jsonObject_re;
		}
		price=machine.getOperatinginfo().getWashingPrice();
		price=price*discount*100;
		jsonObject_re.put("price",Math.round(price));
		return jsonObject_re;
	}
	
	@RequestMapping(value="/getByStatue")
	public @ResponseBody PageBean getByStatue(HttpServletRequest request,HttpServletResponse response){
		String statue=request.getParameter("statue");
		int pageNO=Integer.parseInt(request.getParameter("pageNO"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		PageBean pageBean=userService.getByRegisterStatue(statue, pageNO, pageSize);
		return pageBean;
	}
	
	@RequestMapping(value="/getAllParts")
	public @ResponseBody PageBean getAllParts(HttpServletRequest request,HttpServletResponse response){
		int pageNo=Integer.parseInt(request.getParameter("pageNo"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		PageBean pageBean=partsService.getAllParts(pageNo, pageSize);
		return pageBean;
	}
	
	@RequestMapping(value="/getAllPartsName")
	public @ResponseBody JSONObject getAllPartsName(HttpServletRequest request,HttpServletResponse response){
		JSONObject jsonObject=new JSONObject();
		List<Parts> list=partsService.getAllParts();
		List<String> name=new ArrayList<>();
		for(int i=0;i<list.size();i++){
			name.add(list.get(i).getName());
		}
		jsonObject.put("name", name);
		return jsonObject;
	}
	
	@RequestMapping(value="/sinParts")
	public @ResponseBody PageBean getSinParts(HttpServletRequest request,HttpServletResponse response){
		String partsId=request.getParameter("partsId");
		PageBean pageBean=partsService.getPartsById(partsId);
		return pageBean;
	}
	
	//返回用于编辑配件信息的页面
	@RequestMapping(value="/singleParts")
	public  String getSingleParts(HttpServletRequest request,HttpServletResponse response,Model model){
		String partsId=request.getParameter("partsId");
		Parts parts=(Parts) partsService.getPartsById(partsId).getRows().get(0);
		System.out.println(parts.getImageurl());
		model.addAttribute("parts", parts);
		return "updateParts";
	}
	
	@RequestMapping(value="/getPartsImg")
	public @ResponseBody void getPartsImg(HttpServletRequest request,HttpServletResponse response){
		String partsId=request.getParameter("imgurl");
		Parts parts=partsService.getById(partsId);
		String imgurl=parts.getImageurl();
		if(imgurl==null){
			return;
		}
		response.setHeader("Pragma","No-cache");    
 	    response.setHeader("Cache-Control","no-cache");    
	    response.setDateHeader("Expires", 0);    
	    BufferedInputStream bis = null;  

	    OutputStream os = null;  

	    FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(new File(imgurl));
			bis = new BufferedInputStream(fileInputStream);  
		    byte[] buffer = new byte[1024*10];  
		    response.reset();  
		    response.setCharacterEncoding("UTF-8");  
		   //不同类型的文件对应不同的MIME类型  
		    response.setContentType("image/png");  

		    response.setContentLength(bis.available());  
		    os = response.getOutputStream();  
		    int n;  
		    while ((n = bis.read(buffer)) != -1) {  
		     os.write(buffer, 0, n);  
		    }
		    bis.close();  
		    os.flush();  
		    os.close();
		} catch (IOException e) {
			logger.info("文件不存在");
			e.printStackTrace();
		}  
	}
	//返回实时的udp数据
	@RequestMapping(value="/getUdpInfo")
	public  @ResponseBody JSONObject getUdpInfo(HttpServletRequest request,HttpServletResponse response,Model model){

		List<Client> list = SingleUdpList.getSingleUdpList().getList();

		List total=new ArrayList();	
		
		for(Client c:list){
			JSONObject result=new JSONObject();
			result.put("IP", c.getIP());
			result.put("Port", c.getPort());
			result.put("content", c.getContentHex() + "(" + c.getContent() + ")");
			if(c.getResponseHex() == null) {
				result.put("response", c.getResponse());
			}else {
				result.put("response", c.getResponseHex() + "(" + c.getResponse() + ")");
			}
		
			total.add(result);
		}
		
		JSONObject data=new JSONObject();
		data.put("total", 1);
		data.put("rows", total);
		return data;
	}
	
	//返回NB设置信息
	@RequestMapping(value="/getNBconfig")
	public  @ResponseBody JSONObject getNBconfig(HttpServletRequest request,HttpServletResponse response,Model model){

		SingleUdpList list = SingleUdpList.getSingleUdpList();

//		int delayTime = list.getDelayTime();
		int delayTime = welllidService.getDelayTime();
				
		List total=new ArrayList();	
		
		JSONObject result=new JSONObject();
		result.put("id", 1);
		result.put("delayTime", delayTime);
		total.add(result);
	
		JSONObject data=new JSONObject();
		data.put("total", 1);
		data.put("rows", total);
		return data;
	}
	
	//返回当前的命令表
		@RequestMapping(value="/getCommandInfo")
		public  @ResponseBody JSONObject getCommandInfo(HttpServletRequest request,HttpServletResponse response,Model model){

			List<Command> list = SingleUdpList.getSingleUdpList().getCommandList();

			list = welllidService.getCommandInfo();
					
			List total=new ArrayList();	
			
			for(Command c:list){
				JSONObject result=new JSONObject();
				result.put("id", c.getId());
				if(c.getRequest() != null) {
					result.put("request", c.getRequestHex() + "(" + c.getRequest() + ")");
				}else {
					result.put("request", c.getRequest());
				}
				if(c.getResponse() != null){
					result.put("response", c.getResponseHex()+ "(" + c.getResponse() + ")");
				}else {
					result.put("response", c.getResponse());
				}
				result.put("requestName", c.getRequestName());
				result.put("responseName", c.getResponseName());
				total.add(result);
			}
			
			JSONObject data=new JSONObject();
			data.put("total", 1);
			data.put("rows", total);
			return data;
		}
	
	
}
