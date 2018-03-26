package com.panhong.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Timer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.panhong.model.Admin;
import com.panhong.model.Auth_Function;
import com.panhong.model.Benefit;
import com.panhong.model.BindInfo;
import com.panhong.model.InfoRelease;
import com.panhong.model.Location;
import com.panhong.model.Machine;
import com.panhong.model.MaintenanceRecord;
import com.panhong.model.MaintenanceType;
import com.panhong.model.Mcode;
import com.panhong.model.OpenRecord;
import com.panhong.model.OperatingInfo;
import com.panhong.model.OrderRecord;
import com.panhong.model.Parts;
import com.panhong.model.ServiceType;
import com.panhong.model.SystemRecord;
import com.panhong.model.User;
import com.panhong.model.UserProperty;
import com.panhong.service.AdminService;
import com.panhong.service.BenefitService;
import com.panhong.service.BindInfoService;
import com.panhong.service.FunctionService;
import com.panhong.service.InfoReleaseService;
import com.panhong.service.LocationService;
import com.panhong.service.MachineService;
import com.panhong.service.MaintenanceRecordService;
import com.panhong.service.MaintenanceTypeService;
import com.panhong.service.McodeService;
import com.panhong.service.OpenRecordService;
import com.panhong.service.OperatingInfoService;
import com.panhong.service.OrderRecordService;
import com.panhong.service.PartsService;
import com.panhong.service.ServiceTypeService;
import com.panhong.service.SystemRecordService;
import com.panhong.service.UserPropertyService;
import com.panhong.service.UserService;
import com.panhong.thread.RebootTask;
import com.panhong.util.CommonUtil;
import com.panhong.util.HttpUtil;


@Controller
@RequestMapping(value="/add")
public class AddController {
	
	private static int USER_VALID=1;
	private static int USER_UNVALID=0;
	
	@Resource
	public UserService userService;
	
	@Resource
	public BenefitService benefitService;
	
	@Resource
	public AdminService adminService;
	
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
	public LocationService locationService;
	
	@Resource
	public SystemRecordService systemRecordService;
	
	@Resource
	public MaintenanceTypeService maintenanceTypeService;
	
	@Resource
	public BindInfoService bindInfoService;
	
	@Resource
	public McodeService mcodeService;
	
	@Resource
	public PartsService partsService;
	
	@Resource
	public FunctionService functionService;
	
	@Resource
	public OpenRecordService openRecordService;
	
	
	@Resource
	public ServiceTypeService serviceTypeService;
	//日志显示
	private static final Log logger = LogFactory
            .getLog(AddController.class);
	
	//添加新用户
	@RequestMapping(value="/user")
	public @ResponseBody void addUser(HttpServletRequest request){
		UserProperty userProperty;
		User user=HttpUtil.getUserBean(request, User.class);
		System.out.println(user.getOpenid()+" aaaaa");
		User user_check=userService.getUserById(user.getOpenid());
		//先判断是否为过去已关注用户，否则执行更新操作
		if(user_check==null){
			if(user.getProperty()==null){
				userProperty=userPropertyService.getUserPropertyByGradeName("common");
			}else{
				userProperty=userPropertyService.getUserPropertyByGradeName(user.getProperty());
			}
			user.setUserProperty(userProperty);
			user.setValid(USER_VALID);//标识为关注用户
			user.setVipProperty(0);//设置用户的vip属性为普通用户
			user.setFollowDate(new Date());//设置关注时间为当前时间
			userService.addUser(user);
			logger.info("添加用户"+user.getOpenid()+"成功！");
		}else{
			//若是曾经关注用户，则进行更新操作
			user_check.setUnfollowDate(null);
			user_check.setFollowDate(new Date());
			user_check.setValid(USER_VALID);
			user_check.setCity(user.getCity());
			user_check.setCountry(user.getCountry());
			user_check.setNickname(user.getNickname());
			user_check.setProvince(user.getProvince());
			user_check.setSex(user.getSex());
			userService.updateUser(user_check);
		}
		
	}
	
	//添加用户位置信息
	@RequestMapping(value="/openRecord")
	public @ResponseBody void addOpenRecord(HttpServletRequest request){
		String openid=request.getParameter("openid");
		String deviceId=request.getParameter("deviceId");
		String status=request.getParameter("status");
		Date date=new Date();
		
		OpenRecord openRecord=new OpenRecord();
		openRecord.setDate(date);
		openRecord.setDeviceId(deviceId);
		openRecord.setOpenid(openid);
		openRecord.setStatus(status);
		
		openRecordService.addOpenRecord(openRecord);
		
		logger.info("添加开启记录成功");
	}
	
	//添加用户属性
	@RequestMapping(value="/userProperty")
	public @ResponseBody void addUserProperty(HttpServletRequest request){
		UserProperty userProperty=HttpUtil.getUserPropertyBean(request, UserProperty.class);
		userPropertyService.addUserProperty(userProperty);
		logger.info("添加等级"+userProperty.getGradeName()+"成功！");
	}
	//新增用户优惠信息
	@RequestMapping(value="/userOff")
	public @ResponseBody void userOff(HttpServletResponse response,HttpServletRequest request){
		String openid=request.getParameter("openid");
		String startTime=request.getParameter("start");
		String endTime=request.getParameter("end");
		String times=request.getParameter("times");
		
//		String username=request.getParameter("username");//给予优惠的管理员
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
			Benefit benefit=new Benefit();
			benefit.setEndTime(end);
			benefit.setStartTime(start);
			benefit.setTotalTimes(totalTimes);
			benefit.setTimes(0+"");//设置初始为0次
			
			HttpSession session=request.getSession();
			Admin admin=(Admin) session.getAttribute("admin");//从session中获取Admin
			benefit.setAdmin(admin);
			
			benefit.setCreatTime(new Date());
			benefit.setStatus("unused");
			benefit.setUser(user);
			benefitService.addBenefit(benefit);
			
			//判断用户属性是否为普通注册用户，如果是 改为优惠用户 佛则保持不变
			Date now=new Date();
			if(start.before(now) && end.after(now)){
				user=userService.getUserById(openid);
				if(user.getUserProperty().getUserProperty()==2){
					UserProperty userProperty8=userPropertyService.getUserPropertyById(8);
					user.setUserProperty(userProperty8);
					userService.updateUser(user);
				}
			}
			HttpUtil.sendInfoBack(response, "成功！");
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "数据库操作错误");
			return;
		}
		
		
	}
	
		
	//添加订单记录
	@RequestMapping(value="/order")
	public @ResponseBody void addOrder(HttpServletRequest request){
		OrderRecord orderRecord=HttpUtil.getOrderBean(request, OrderRecord.class);
		logger.info(orderRecord.getAttach()+"  "+new Date());
		User user=userService.getUserById(orderRecord.getOpenid());
		Machine machine=machineService.getMachineById(orderRecord.getAttach());
		orderRecord.setMachineID(machine);
		orderRecord.setWechatID(user);
		
		String hql="select count(*) from OrderRecord where out_trade_no='"+orderRecord.getOut_trade_no()+"'";
		int count=orderRecordService.getCount(hql);
		//如果已有订单 则不再插入
		if(count>0){
			return;
		}
		orderRecordService.addOrderRecord(orderRecord);
		logger.info("add order"+orderRecord.getOrderID()+"success");
		
		//如果用户没注册通知用户进行注册
		if(user.getCode()==null && orderRecord.getTotal_fee().equals("400")){
			String url="http://pioteks.com/WM/Pioteks/Tools/Inform_Register__Template.php";
			String result=HttpUtil.postOpenid(user.getOpenid(), url);
			logger.info("inform user to register :"+user.getOpenid()+"  "+result);
		}
		//在用户总洗衣次数上进行加1操作
		if(user.getTotalTimes()==null){
			user.setTotalTimes("1");
			userService.updateUser(user);
		}else{
			int totalTimes=Integer.parseInt(user.getTotalTimes());
			user.setTotalTimes((++totalTimes)+"");
			userService.updateUser(user);
		}
		
		
		//开始计时3miao钟 重启
		RebootTask myTask=new RebootTask(orderRecord.getOpenid(),machine.getMachineID());
		new Timer(true).schedule(myTask, 3*1000);  //5秒
		
	}
	
	//添加维护记录
	@RequestMapping(value="/maintenanceRecord")
	public @ResponseBody void addMaintenance(HttpServletRequest request,HttpServletResponse response){
		try{
			MaintenanceRecord maintenanceRecord =HttpUtil.getMaintenanceBean(request, MaintenanceRecord.class);
			User maintenanceOperator=userService.getUserById(maintenanceRecord.getUserID());
			Machine machine=(Machine) machineService.getMachineByMachineName(maintenanceRecord.getMachine()).getRows().get(0);
			MaintenanceType maintenanceType=maintenanceTypeService.getMaintenanceTypeByName(maintenanceRecord.getMaintenance());
			Date date=HttpUtil.StringToDate(maintenanceRecord.getDate_main());
			
			maintenanceRecord.setMaintenanceDate(date);
			maintenanceRecord.setMaintenanceType(maintenanceType);
			maintenanceRecord.setMaintenanceOperator(maintenanceOperator);
			maintenanceRecord.setMachineID(machine);
			maintenanceRecordService.addMaintenanceRecord(maintenanceRecord);
			HttpUtil.sendInfoBack(response, "success");
			logger.info("添加维护记录"+maintenanceRecord.getMaintenanceRecordID()+"成功！");
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "erro");
			e.printStackTrace();
		}
	}
	
	//添加维护类型
	@RequestMapping(value="/maintenanceType")
	public @ResponseBody void addMaintenanceType(HttpServletRequest request,HttpServletResponse response){
		MaintenanceType maintenanceType=HttpUtil.getMaintenanceTypeBean(request, MaintenanceType.class);
		try{
			maintenanceTypeService.addMaintenanceType(maintenanceType);
			HttpUtil.sendInfoBack(response, "success");
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "erro");
			e.printStackTrace();
		}
		logger.info("添加维护类型"+maintenanceType.getMaintenanceName()+"成功！");
	}
	
	//添加维修类型
	@RequestMapping(value="/serviceType")
	public @ResponseBody void addServiceType(HttpServletRequest request,HttpServletResponse response){
		ServiceType serviceType=HttpUtil.getServiceTypeBean(request, ServiceType.class);
		try{
			if(!serviceType.getPartsOneName().equals("0")){
				serviceType.setPartsOne(partsService.getByName(serviceType.getPartsOneName()));
			}
			if(!serviceType.getPartsTwoName().equals("0")){
				serviceType.setPartsTwo(partsService.getByName(serviceType.getPartsTwoName()));
			}
			if(!serviceType.getPartsThreeName().equals("0")){
				serviceType.setPartsThree(partsService.getByName(serviceType.getPartsThreeName()));
			}
			if(!serviceType.getPartsFourName().equals("0")){
				serviceType.setPartsFour(partsService.getByName(serviceType.getPartsFourName()));
			}
			if(!serviceType.getPartsFiveName().equals("0")){
				serviceType.setPartsFive(partsService.getByName(serviceType.getPartsFiveName()));
			}
			if(!serviceType.getPartsSixName().equals("0")){
				serviceType.setPartsSix(partsService.getByName(serviceType.getPartsSixName()));
			}
			if(!serviceType.getPartsSevenName().equals("0")){
				serviceType.setPartsSeven(partsService.getByName(serviceType.getPartsSevenName()));
			}
			if(!serviceType.getPartsEightName().equals("0")){
				serviceType.setPartsEight(partsService.getByName(serviceType.getPartsEightName()));
			}
			if(!serviceType.getPartsNineName().equals("0")){
				serviceType.setPartsNine(partsService.getByName(serviceType.getPartsNineName()));
			}
			if(!serviceType.getPartsTenName().equals("0")){
				serviceType.setPartsTen(partsService.getByName(serviceType.getPartsTenName()));
			}
			serviceTypeService.addServiceType(serviceType);
			HttpUtil.sendInfoBack(response, "success");
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "erro");
			e.printStackTrace();
		}
		logger.info("添加维护类型"+serviceType.getServiceName()+"成功！");
	}
	
	//添加机器
	@RequestMapping(value="/machine")
	public @ResponseBody void addMachine(HttpServletRequest request,HttpServletResponse response){
		Machine machine=HttpUtil.getMachineBean(request, Machine.class);
		try{
			OperatingInfo operatingInfo =operatingInfoService.getOperatingInfoBySchoolAndCompus(machine.getSchool(), machine.getCompus());
			machine.setOperatinginfo(operatingInfo);
			machineService.addMachine(machine);
			HttpUtil.sendInfoBack(response, "success");
			logger.info("添加机器"+machine.getMachineID()+"成功！");
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "添加失败。");
			e.printStackTrace();
		}
	}
	
	//添加发布信息记录
	@RequestMapping(value="/infoRelease")
	public @ResponseBody void addInfoRelease(HttpServletRequest request){
		InfoRelease infoRelease=HttpUtil.getInfoReleaseBean(request, InfoRelease.class);
		infoReleaseService.addInfoRelease(infoRelease);
		logger.info("添加信息发布记录成功"+infoRelease.getInfoReleaseID());
	}
	
	//添加运营信息
	@RequestMapping(value="/operatingInfo")
	public @ResponseBody void addOperatingInfo(HttpServletRequest request){
		OperatingInfo operatingInfo=HttpUtil.getOperatingInfoBean(request, OperatingInfo.class);
		operatingInfoService.addOperatingInfo(operatingInfo);
		logger.info("添加运营信息成功"+operatingInfo.getSchool()+operatingInfo.getCompus()+operatingInfo.getWashingPrice());
	}
	
	//添加系统升级记录
	@RequestMapping(value="/systemRecord")
	public @ResponseBody void addSystemRecord(HttpServletRequest request){
		SystemRecord systemRecord=HttpUtil.getSystemRecordBean(request, SystemRecord.class);
		OperatingInfo operatingInfo=operatingInfoService.getOperatingInfoBySchoolAndCompus(systemRecord.getSchool(), systemRecord.getCompus());
		systemRecord.setOperatinginfo(operatingInfo);
		systemRecordService.addSystemRecord(systemRecord);
		logger.info("添加系统升级记录成功"+systemRecord.getSystemRecordID());
	}
	
	//添加用户设备绑定信息
	@RequestMapping(value="/bindInfo")
	public @ResponseBody void addBindInfo(HttpServletRequest request){
		BindInfo bindInfo=HttpUtil.getBindInfoBean(request, BindInfo.class);
		BindInfo bindInfo_old=bindInfoService.getBindInfoByOpenidAndDeviceID(bindInfo.getOpenid(), bindInfo.getDeviceinfo());
		if(bindInfo_old==null){
			User user=userService.getUserById(bindInfo.getOpenid());
			Machine machine=machineService.getMachineById(bindInfo.getDeviceinfo());
			bindInfo.setWechatID(user);
			bindInfo.setMachineID(machine);
			bindInfoService.addBindInfo(bindInfo);
		}else{
			bindInfo_old.setState(bindInfo.getState());
			bindInfoService.updateBindInfo(bindInfo_old);
		}
	}
	
	//添加mcode
	@RequestMapping(value="/mcode")
	public @ResponseBody JSONObject addMcode(HttpServletRequest request){
		JSONObject jsonObject=new JSONObject();
		String code =request.getParameter("mcode");
		Mcode mcode=new Mcode();
		mcode.setMcode(code);
		mcode.setCreateDate(new Date());
		String result=mcodeService.addMcode(mcode);
		jsonObject.put("result", result);
		return jsonObject;
	}
	
	@RequestMapping(value="/parts")
	public @ResponseBody void addParts(HttpServletRequest request,HttpServletResponse response){
		Parts parts=HttpUtil.getPartsBean(request, Parts.class);
		try{
			partsService.addParts(parts);
			HttpUtil.sendInfoBack(response, "success");
			logger.info("添加成功"+parts.getPartsId());
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "添加失败，请联系管理员！");
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/function")
	public @ResponseBody void addFunction(HttpServletRequest request,HttpServletResponse response){
		String name=request.getParameter("name");
		int parentId=Integer.parseInt(request.getParameter("parentId"));
		String url=request.getParameter("url");
		int serialNum=Integer.parseInt(request.getParameter("serialNum"));
		int accordion=Integer.parseInt(request.getParameter("accordion"));
		
		Auth_Function function=new Auth_Function();
		
		function.setAccordion(accordion);
		function.setName(name);
		function.setParentId(parentId);
		function.setSerialNum(serialNum);
		function.setUrl(url);
		
		try{
			functionService.add(function);
			HttpUtil.sendInfoBack(response, "success");
			logger.info("添加成功"+function.getId());
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "添加失败，请联系管理员！");
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/location")
	public @ResponseBody void addlocation(HttpServletRequest request,HttpServletResponse response){
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			logger.info("不支持的编码格式");
			e1.printStackTrace();
		}
		String school=request.getParameter("school");
		String compus=request.getParameter("compus");
		String building=request.getParameter("building");
		
		System.out.println(school+ compus+building);
		
		Location location=new Location();
		
		location.setBuilding(building);
		location.setCompus(compus);
		location.setSchool(school);
		
		try{
			locationService.add(location);
			HttpUtil.sendInfoBack(response, "success");
			logger.info("添加成功"+location.getId());
		}catch(Exception e){
			HttpUtil.sendInfoBack(response, "添加失败，请联系管理员！");
			e.printStackTrace();
		}
	}
	//上传图片
		@RequestMapping(value = "/photoUpload",method = RequestMethod.POST)
		public @ResponseBody void photoUpload(@RequestParam(value="upfile") MultipartFile file,HttpServletRequest request,HttpServletResponse response,HttpSession session){
			if (file!=null) {// 判断上传的文件是否为空
	            String path="/data/wxws/test/images/";// 文件路径
//	            String path="E:/test/";// 文件路径
	            String type=null;// 文件类型
	            String partsId=request.getParameter("partsId");
	            System.out.println(partsId);
	            String fileName=file.getOriginalFilename();// 文件名称更改
	            // 判断文件类型
	            type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
	            if (type!=null) {// 判断文件类型是否为空
	                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
	                    // 自定义的文件名称
	                    
	                    String trueFileName=partsId.toString()+"."+type;
	                    // 设置存放图片文件的路径
	                    path=path+trueFileName;
	                    System.out.println("存放图片文件的路径:"+path);
	                    // 转存文件到指定的路径
	                    try {
	                    	File file2=new File(path);
	                    	//如果文件存在则删除文件
	                    	if(file2.exists()){
	                    		file2.delete();
	                    	}
							file.transferTo(file2);
							Parts parts=partsService.getById(partsId);
							parts.setImageurl(path);
							partsService.updateParts(parts);
						} catch (Exception e) {
							System.out.println("aa");
							e.printStackTrace();
						}
	                    System.out.println("上传成功");
	                    HttpUtil.sendInfoBack(response, "success");
	                }else {
	                	HttpUtil.sendInfoBack(response, "不是我们想要的文件类型,请按要求重新上传");
	                }
	            }else {
	            	HttpUtil.sendInfoBack(response, "文件类型为空");
	            }
	        }else {
	        	HttpUtil.sendInfoBack(response, "没有找到相对应的文件");
	        }
		}
		
		@RequestMapping(value="/admin")
		public @ResponseBody void addAdmin(HttpServletRequest request,HttpServletResponse response){
			Admin admin=HttpUtil.getAdminBean(request, Admin.class);
			try{
				adminService.add(admin);
				HttpUtil.sendInfoBack(response, "success");
				logger.info("添加成功"+admin.getUsername());
			}catch(Exception e){
				HttpUtil.sendInfoBack(response, "添加失败，请联系管理员！");
				e.printStackTrace();
			}
		
		}
		
	


}
