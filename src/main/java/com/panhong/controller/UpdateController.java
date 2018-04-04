package com.panhong.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panhong.model.Admin;
import com.panhong.model.Benefit;
import com.panhong.model.BindInfo;
import com.panhong.model.FormNum;
import com.panhong.model.Machine;
import com.panhong.model.OperatingInfo;
import com.panhong.model.OrderRecord;
import com.panhong.model.Parts;
import com.panhong.model.User;
import com.panhong.model.UserProperty;
import com.panhong.service.BenefitService;
import com.panhong.service.BindInfoService;
import com.panhong.service.InfoReleaseService;
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
import com.panhong.thread.InformTask;
import com.panhong.util.CommonUtil;
import com.panhong.util.HttpUtil;

@Controller
@RequestMapping(value="/update")
public class UpdateController {
	
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
	public BindInfoService bindInfoService;
	
	@Resource
	public PartsService partsService;
	
	@Resource
	public WelllidService welllidService;
	//日志显示
	private static final Log logger = LogFactory
            .getLog(UpdateController.class);
	
	
	//更新设备绑定信息
	@RequestMapping(value="/bindInfo")
	public @ResponseBody void updateBindInfo(HttpServletRequest request){
		BindInfo bindInfo=HttpUtil.getBindInfoBean(request, BindInfo.class);
		String state=bindInfo.getState();
		bindInfo=bindInfoService.getBindInfoByOpenidAndDeviceID(bindInfo.getOpenid(), bindInfo.getDeviceinfo());
		bindInfo.setState(state);
		bindInfoService.updateBindInfo(bindInfo);
	}
	//更新单价
	@RequestMapping(value="/opratingInfo")
	public @ResponseBody void updateOpratingInfo(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		
		String school=request.getParameter("school");
		String compus=request.getParameter("compus");
		Double washingPrice=Double.parseDouble(request.getParameter("washingPrice"));
		try{
			OperatingInfo operatingInfo=operatingInfoService.getOperatingInfoBySchoolAndCompus(school, compus);
			operatingInfo.setWashingPrice(washingPrice);
			operatingInfo.setLatestModifyTime(new Date());
			operatingInfo.setLatestModifyWorker(admin);
			
			operatingInfoService.updateOperatingInfo(operatingInfo);
			
			HttpUtil.sendInfoBack(response, "success");
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "error");
			e.printStackTrace();
			return;
		}
	}
	
	//用户升级信息
	@RequestMapping(value="/user")
	public @ResponseBody void updateUser(HttpServletRequest request,HttpServletResponse response){
		
		String openid=request.getParameter("openid");
		String telephone=request.getParameter("telephone");
		String studentID=request.getParameter("studentID");
		String name=request.getParameter("name");
		String organization=request.getParameter("organization");  // 1 上海大学 ；2 上海纽约大学  3上海财经 4华东师范  5 移动互联创新大赛
		//System.out.println(openid+telephone+studentID);
		int org=Integer.parseInt(organization);
		String[] schools={"上海大学","上海纽约大学","上海财经","华东师范","PIOT"};
		User user=userService.getUserById(openid);
		if(user.getCode()==null || user.getCode().equals("")){
			if(userService.getByPhone(telephone)==null){
				if(userService.getByStudentId(studentID)==null){
					user.setTelephone(telephone);
					user.setStudentID(studentID);
					user.setName(name);
					user.setSchool(schools[--org]);
					user.setCode("upgraded");
					user.setCodeTime(new Date());
					if(user.getSchool().equals("PIOT")){
						user.setUserProperty(userPropertyService.getUserPropertyById(20));
					}else{
						user.setUserProperty(userPropertyService.getUserPropertyById(2));
					}
					user.setVipProperty(1);//1表示为VIP
					userService.updateUser(user);
					HttpUtil.sendInfoBack(response, "升级成功，您将享受学生优惠！");
				}else{
					user.setTelephone(telephone);
					user.setStudentID(studentID);
					user.setName(name);
					user.setSchool(schools[--org]);
					user.setCode("upgrading");
					user.setCodeTime(new Date());
					userService.updateUser(user);
//					HttpUtil.sendInfoBack(response, "学号已被注册，请检查后输入！");
				}
				
			}else{//说明电话已经存在
				user.setTelephone(telephone);
				user.setStudentID(studentID);
				user.setName(name);
				user.setSchool(schools[--org]);
				user.setCode("upgrading");
				user.setCodeTime(new Date());
				userService.updateUser(user);
//				HttpUtil.sendInfoBack(response, "手机号码已被注册，请检查后输入！");
			}
		}else if(user.getCode().equals("upgrading")){
			HttpUtil.sendInfoBack(response, "请勿重复提交，并请耐心等待！");
		}else if(user.getCode().equals("upgraded")){
			HttpUtil.sendInfoBack(response, "您已完成认证！");
		}
	}
	
	//更新用户属性信息
	@RequestMapping(value="/userProperty")
	public @ResponseBody void updateUserProperty(HttpServletRequest request,HttpServletResponse response){
		String openid=request.getParameter("openid");
		int userProperty=Integer.parseInt(request.getParameter("userProperty"));
		if(openid==null || userProperty==0){
			HttpUtil.sendInfoBack(response, "error");
			return;
		}
		try{
			User user=userService.getUserById(openid);
			UserProperty userProperty2=userPropertyService.getUserPropertyById(userProperty);
			user.setUserProperty(userProperty2);
			userService.updateUser(user);
			HttpUtil.sendInfoBack(response, "success");
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "error");
			e.printStackTrace();
			return;
		}
	}
	
	//更新系统维护记录信息
	@RequestMapping(value="/saveChargebackFile")
	public @ResponseBody void saveChargebackFile(@RequestParam List<String> reasons,@RequestParam List<String> ids,HttpServletResponse response){
		OrderRecord orderRecord=null;
		for(int i=0;i<ids.size();i++){
			orderRecord=orderRecordService.getOrderRecordById(Integer.parseInt(ids.get(i)));
			orderRecord.setChargeback(reasons.get(i));
			orderRecord.setIsChecked("Yes");
			orderRecordService.updateOrderRecord(orderRecord);
		}
		HttpUtil.sendInfoBack(response, "success");
	}
	
	/*//更新用户优惠信息
	@RequestMapping(value="/userOff")
	public @ResponseBody void userOff(HttpServletResponse response,HttpServletRequest request){
		String openid=request.getParameter("openid");
		String startTime=request.getParameter("start");
		String endTime=request.getParameter("end");
		String times=request.getParameter("times");
		//判断参数是否为空
		if(openid.equals("") || startTime.equals("") || endTime.equals("") || times.equals("") || openid==null ||startTime==null || endTime==null || times==null){
			HttpUtil.sendInfoBack(response, "操作失败，部分参数为空！");
			return;
		}
		
		String totalTimes="";
		if(times.equals("always")){
			totalTimes="always";
		}else{
			try{
				totalTimes=Integer.parseInt(times)+"";
			}catch(NumberFormatException e){
				logger.info("洗衣总次数包含非数字字符！");
				HttpUtil.sendInfoBack(response, "失败，洗衣次数包含非法字符！");
				return;
			}
		}
		
		Date start=CommonUtil.format2Time(startTime);
		Date end=CommonUtil.format2Time(endTime);
		
		try{
			User user=userService.getUserById(openid);
			user.setEndTime(end);
			user.setStartTime(start);
			user.setTotalTimes(totalTimes);
			user.setTimes(0+"");
			
			//判断用户属性是否为普通注册用户，如果是 改为优惠用户 佛则保持不变
			if(user.getUserProperty().getUserProperty()==2){
				UserProperty userProperty8=userPropertyService.getUserPropertyById(8);
				user.setUserProperty(userProperty8);
			}
			userService.updateUser(user);
			HttpUtil.sendInfoBack(response, "成功！");
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "数据库操作错误");
			return;
		}
		
		
	}*/
	
	//同意用户升级
	@RequestMapping(value="/upgradeuser")
	public @ResponseBody void upgradeuser(HttpServletRequest request,HttpServletResponse response){
		String openid=request.getParameter("openid");
		try{
			User user=userService.getUserById(openid);
			UserProperty userPropertyNow=user.getUserProperty();
			List<User> userList=userService.getByPhone(user.getTelephone(),1,10).getRows();
			if(userList.size()>1){
				HttpUtil.sendInfoBack(response, "用户电话重复！");
				return;
			}
			
			userList=userService.getByStudentId(user.getStudentID(),1,10).getRows();
			if(userList.size()>1){
				HttpUtil.sendInfoBack(response, "用户学号重复！");
				return;
			}
			
			if(userPropertyNow.getUserProperty()==1){
				user.setCode("upgraded");
				UserProperty userProperty=userPropertyService.getUserPropertyById(2);
				user.setUserProperty(userProperty);
				user.setVipProperty(1);//1表示为VIP
				//不在给用户首次优惠
				//给予用户首次优惠
				/*Date date=new Date();
				Calendar cd=Calendar.getInstance();
				cd.setTime(date);
				cd.add(Calendar.MONTH, 1);
				//设置优惠结束日期为当前日期加上一个月
				
				Benefit benefit=new Benefit();
				
				benefit.setEndTime(cd.getTime());
				benefit.setStartTime(user.getFollowDate());
				benefit.setTotalTimes(1+"");
				benefit.setTimes(0+"");
				benefit.setCreatTime(date);
				benefit.setStatus("using");
				benefit.setUser(user);
				*/
//				benefitService.addBenefit(benefit);
				
				userService.updateUser(user);
				HttpUtil.sendInfoBack(response, "升级成功！");
				
				//向用户发送注册成功信息
				String url="http://pioteks.com/WM/Pioteks/Tools/Register_template.php";
				String result=HttpUtil.postOpenid(openid, url);
				logger.info("发送注册成功通知结果:"+result);
			}else{
				HttpUtil.sendInfoBack(response, "请勿重复升级用户！");
			}
			
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "升级失败，请联系管理员！");
			e.printStackTrace();
		}
	}
	
	//否决用户升级
	@RequestMapping(value="/disupgradeuser")
	public @ResponseBody void disupgradeuser(HttpServletRequest request,HttpServletResponse response){
		String openid=request.getParameter("openid");
		try{
			User user=userService.getUserById(openid);
			user.setCode(null);
			user.setCodeTime(null);
			user.setName(null);
			user.setTelephone(null);
			user.setStudentID(null);
			userService.updateUser(user);
			HttpUtil.sendInfoBack(response, "处理成功！");
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "处理失败，请联系管理员！");
			e.printStackTrace();
		}
	}

/*	//否决用户升级
	@RequestMapping(value="/innerBenefit")
	public @ResponseBody void innerBenefit(Benefit benefit){
		try{
			benefitService.updateBenefit(benefit);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	*/
//	更新单一订单信息
	
//	更新运营信息
	
//	更新维护类型信息
	
//	更新维护记录信息
	
//	更新机器信息
	@RequestMapping(value="/machine")
	public @ResponseBody void updateMachine(HttpServletRequest request,HttpServletResponse response){
		Machine machine_new=HttpUtil.getMachineBean(request, Machine.class);
		try{
			Machine machine=(Machine) machineService.getMachineByMachineName(machine_new.getMachineName()).getRows().get(0);
			machine.setBuilding(machine_new.getBuilding());
			OperatingInfo operatingInfo=operatingInfoService.getOperatingInfoBySchoolAndCompus(machine_new.getSchool(), machine_new.getCompus());
			machine.setOperatinginfo(operatingInfo);
			machine.setMachineType(machine_new.getMachineType());
			machine.setModel(machine_new.getModel());
			machine.setStatus(machine_new.getStatus());
			machineService.updateMachine(machine);
			HttpUtil.sendInfoBack(response, "success");
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "failure");
			e.printStackTrace();
		}
	}
	
//	更新机器状态
	@RequestMapping(value="/machineStatus")
	public @ResponseBody void machineStatus(HttpServletRequest request,HttpServletResponse response){
		String machineId=request.getParameter("deviceid");
		String openid=request.getParameter("openid");
		logger.info(machineId+"   "+openid+"  "+new Date());
		if(machineId==null || machineId.equals("") || openid==null || openid.equals("")){
			logger.info("change MachineStauts failure");
			logger.info(machineId+"  "+openid);
			HttpUtil.sendInfoBack(response, "failure");
			return;
		}
		try{
			Machine machine=(Machine) machineService.getMachineById(machineId);
			if(machine.getStatus()!=null && machine.getStatus().equals("维修中")){
				HttpUtil.sendInfoBack(response, "failure");
				logger.info("In respairing");
				return;
			}
			machine.setStatus("工作中");
			machine.setLatestWorkingTime(new Date());
			machineService.updateMachine(machine);
			HttpUtil.sendInfoBack(response, "success");
			
			//开始计时35分钟 提醒用户取衣
			logger.info(openid+"  "+new Date());
			InformTask myTask=new InformTask(openid);
			new Timer(true).schedule(myTask, 35*60*1000);
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "failure");
			e.printStackTrace();
		}
	}
	
	
//	更新信息发布信息

	//更新配件信息
	@RequestMapping(value="/updateParts")
	public @ResponseBody void updateParts(HttpServletRequest request,HttpServletResponse response){
		Parts parts_new=HttpUtil.getPartsBean(request, Parts.class);
		try{
			Parts parts=(Parts) partsService.getPartsById(parts_new.getPartsId()).getRows().get(0);
			parts.setDescription(parts_new.getDescription());
			parts.setMachineType(parts_new.getMachineType());
			parts.setOnStack(parts_new.getOnStack());
			partsService.updateParts(parts);
			HttpUtil.sendInfoBack(response, "success");
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "升级失败，请联系管理员！");
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/updateCommand")
	public String updateCommand(FormNum formNum,Model model){
		welllidService.updateCommand(formNum);
		return "welllidInfoWeb";
	}
	
	@RequestMapping(value="/updateCommandName")
	public String updateCommandName(FormNum formNum,Model model) throws UnsupportedEncodingException{
		welllidService.updateCommandName(formNum);
		return "welllidInfoWeb";
	}
	
	@RequestMapping(value="/updateDelayTime")
	public String updateDelayTime(int delayTime,Model model) throws UnsupportedEncodingException{
		welllidService.updateDelayTime(delayTime);
		return "welllidInfoWeb";
	}
}
