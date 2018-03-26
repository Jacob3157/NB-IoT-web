package com.panhong.controller;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.OrderComparator.OrderSourceProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.panhong.model.Admin;
import com.panhong.model.Benefit;
import com.panhong.model.FormNum;
import com.panhong.model.Mcode;
import com.panhong.model.OrderRecord;
import com.panhong.model.TicketInfo;
import com.panhong.model.TokenInfo;
import com.panhong.model.User;
import com.panhong.model.UserProperty;
import com.panhong.service.AdminService;
import com.panhong.service.BenefitService;
import com.panhong.service.InfoReleaseService;
import com.panhong.service.MachineService;
import com.panhong.service.MaintenanceRecordService;
import com.panhong.service.MaintenanceTypeService;
import com.panhong.service.McodeService;
import com.panhong.service.OpenRecordService;
import com.panhong.service.OperatingInfoService;
import com.panhong.service.OrderRecordService;
import com.panhong.service.PartsService;
import com.panhong.service.SystemRecordService;
import com.panhong.service.TicketInfoService;
import com.panhong.service.TokenInfoService;
import com.panhong.service.UserPropertyService;
import com.panhong.service.UserService;
import com.panhong.service.WelllidService;
import com.panhong.thread.InformTask;
import com.panhong.util.APPContextUtil;
import com.panhong.util.HttpUtil;

@Controller
@RequestMapping(value="/check")
public class CheckController {

	@Resource
	public UserService userService;
	
	@Resource
	public UserPropertyService userPropertyService;
	
	@Resource
	public InfoReleaseService infoReleaseService;
	
	@Resource
	public MachineService machineService;
	
	@Resource
	public BenefitService benefitService;
	
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
	public TokenInfoService tokenInfoService;
	
	@Resource
	public TicketInfoService ticketInfoService;
	
	@Resource
	public McodeService mcodeService;
	
	@Resource
	public AdminService adminService;
	
	@Resource
	public OpenRecordService openRecordService;
	
	@Resource
	public PartsService partsService;
	
	@Resource
	public WelllidService welllidService;
	//日志显示
	private static final Log logger = LogFactory
            .getLog(CheckController.class);
	
	
	//获取token
	@RequestMapping(value="/token")
	public @ResponseBody JSONObject getToken(){
		TokenInfo token=tokenInfoService.getLatestToken();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("accessToken", token.getAccessToken());
		return jsonObject;
	}
	
	//获取ticket
	@RequestMapping(value="/ticket")
	public @ResponseBody JSONObject getTicket(){
		TicketInfo ticket=ticketInfoService.getLatestTicket();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("ticket",ticket.getTicket());
		return jsonObject;
	}
	
	/*//get the welllid opentimes
	@RequestMapping(value="/openTimes")
	public @ResponseBody JSONObject getOpenTimes(HttpServletRequest request){
		String deviceId=request.getParameter("deviceId");
		String hql="select count(*) from OpenRecord where deviceId='"+deviceId+"'";
		int times=openRecordService.getCount(hql);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("times",times);
		return jsonObject;
	}*/
	
	
	
	
	//测试相关的的页面 
	@RequestMapping(value="/test")
	public String maintain_app(){
		return "maintain_app";
	}
	
	@RequestMapping(value="/washer_scan_app")
	public String washer_scan_app(){
		return "washer_scan_app";
	}
	
	@RequestMapping(value="/disinfect_record_app")
	public String disinfect_record_app(){
		return "disinfect_record_app";
	}
	
	@RequestMapping(value="/service_record_app")
	public String service_record(){
		return "service_record_app";
	}
	
	@RequestMapping(value="/parts_scan_app")
	public String parts_scan(){
		return "parts_scan_app";
	}
	
	
	@RequestMapping(value="/washer_detail_app")
	public String washer_detail_app(){
		return "washer_detail_app";
	}
	
	@RequestMapping(value="/service_detail_app")
	public String service_detail_app(){
		return "service_detail_app";
	}
	
	
	@RequestMapping(value="/disinfect_detail_app")
	public String disinfect_detail_app(){
		return "disinfect_detail_app";
	}
	
	//验证主页
	@RequestMapping(value="/index")
	public String index(){
		return "login";
	}
	
	@RequestMapping(value="/logout")
	public @ResponseBody void logout(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
		session.invalidate();
		HttpUtil.sendInfoBack(response, "success");
	}
	
	//用户登录
	@RequestMapping(value="/login",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody void login(HttpServletRequest request,HttpServletResponse response){
		String name=request.getParameter("username");
		String passwd=request.getParameter("password");
		Admin admin=adminService.getByUsername(name);
		if(admin==null){
			logger.info("用户不存在！");
			String message="用户不存在！";
			HttpUtil.sendInfoBack(response, message);
			return;
		}
		
		if(!admin.getPassword().equals(passwd)){
			logger.info("密码错误！");
			String message="密码错误！";
			HttpUtil.sendInfoBack(response, message);
			return;
		}
		HttpSession session=request.getSession();
		session.setAttribute("admin", admin);
		logger.info("用户："+admin.getUsername()+new Date()+"进入系统");
		String message="success";
		HttpUtil.sendInfoBack(response, message);
	}
	
	//获取mcode
	@RequestMapping(value="/mcode",method=RequestMethod.GET)
	public @ResponseBody JSONObject getMcode(){
		Mcode mcode =mcodeService.getLatestMcode();
		if(mcode!=null){
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("mcode", mcode.getMcode());
			return jsonObject;
		}
		return null;
	}
	/*//个人中心
	@RequestMapping(value="/info_app")
	public String info(HttpServletRequest request,Model model){
		String openid=request.getParameter("openid");
		model.addAttribute("openid", openid);
		return "userInfoapp";
	}*/
	
	//注册界面
	@RequestMapping(value="/upgradeuser_app")
	public String upgradeuser_app(HttpServletRequest request,Model model){
		String openid=request.getParameter("openid");
		model.addAttribute("openid", openid);
		return "upgradeuser_app";
	}
	
//	暂时页面
	@RequestMapping(value="/temp")
	public String temp(HttpServletRequest request,Model model){
		return null;
	}
	
	@RequestMapping(value="/addServiceType")
	public String addServiceType(HttpServletRequest request,Model model){
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		logger.info(admin.getUsername()+"  "+new Date()+"进入添加维修类型界面");
		return "addServiceType";
	}
	
	@RequestMapping(value="/addMaintenanceRecord")
	public String addMaintenanceRecord(HttpServletRequest request,Model model){
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		logger.info(admin.getUsername()+"  "+new Date()+"进入添加维护记录界面");
		return "addMaintenanceRecord";
	}
	
	@RequestMapping(value="/addMachine")
	public String addMachine(HttpServletRequest request,Model model){
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		logger.info(admin.getUsername()+"  "+new Date()+"进入添加机器界面");
		return "addMachine";
	}
	
	@RequestMapping(value="/addFunction")
	public String addFunction(HttpServletRequest request,Model model){
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		logger.info(admin.getUsername()+"  "+new Date()+"进入添加功能界面");
		return "addFunction";
	}
	
	@RequestMapping(value="/addLocation")
	public String addLocation(HttpServletRequest request,Model model){
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		logger.info(admin.getUsername()+"  "+new Date()+"进入添加放置地界面");
		return "addLocation";
	}
	
	@RequestMapping(value="/addMaintenanceType")
	public String addMaintenanceType(HttpServletRequest request,Model model){
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		logger.info(admin.getUsername()+"  "+new Date()+"进入添加维护类型界面");
		return "addMaintenanceType";
	}
	//用户升级 
	@RequestMapping(value="/upgrade")
	public String upgrade(HttpServletRequest request,Model model){
		String openid=request.getParameter("openid");
		model.addAttribute("openid", openid);
		return "upgradeuser_app";
	}
	//web端用户信息查询  用户管理-用户信息
	@RequestMapping(value="/userinfo")
	public String userinfo(HttpServletRequest request,Model model){
		String openid=request.getParameter("openid");
		model.addAttribute("openid", openid);
		return "userinfo_web";
	}
	
	
	
	@RequestMapping(value="/generateFile")
	public @ResponseBody void generateFile(@RequestParam List<String> reason,@RequestParam List<String> checkedItem,@RequestParam String fileName,HttpServletResponse response){
//		String path="D:/";
		String path="/data/wxws/web/chargebackFile/";// 文件路径
		File file=new File(path+fileName);
    	//如果文件存在则删除文件
    	if(file.exists()){
    		file.delete();
    	}
    	OrderRecord order=null;
		try {
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter w=new OutputStreamWriter(fos,"utf-8");//使用utf-8写入数据
	    	BufferedWriter bw=new BufferedWriter(w);
	    	String info=null;
	    	for(int i=0;i<reason.size();i++){
				order=orderRecordService.getOrderRecordById(Integer.parseInt(checkedItem.get(i)));
				if(reason.get(i).equals("0")){
					info="操作失误";
				}else if(reason.get(i).equals("1")){
					info="测试员";
				}else if(reason.get(i).equals("2")){
					info="首次优惠";
				}else if(reason.get(i).equals("3")){
					info="不建议退款";
				}else if(reason.get(i).equals("4")){
					info="管理员";
				}else if(reason.get(i).equals("5")){
					info="优惠用户";
				}
				
		    	//用户已经优惠次数加1
		    	User user=order.getWechatID();
		    	if(info.equals("优惠用户") || info.equals("管理员")){
		    		
		    		Benefit benefit=null;
			    	if(benefitService.getBenefitsByUserAndStatus(user.getOpenid(), "using").isEmpty()){
			    		if(benefitService.getBenefitsByUserAndStatus(user.getOpenid(), "unused").isEmpty()){
			    			//如果用户不满足继续优惠条件 不生成退款文件并将优惠订单设为不建议退款
			    			//TODO 如果管理员超出了优惠条件怎么办
			    			order.setChargeback("3");
			    			orderRecordService.updateOrderRecord(order);
			    			continue;
			    		}
			    		benefit=benefitService.getBenefitsByUserAndStatus(user.getOpenid(), "unused").get(0);
			    		benefit.setStatus("using");
			    		benefitService.update(benefit);
			    	}else{
			    		benefit=benefitService.getBenefitsByUserAndStatus(user.getOpenid(), "using").get(0);
			    	}
			    	
			    	//对优惠用户是否继续优惠进行判断
		    		if(user.getUserProperty().getUserProperty()==2 || benefit==null){
		    			
		    			
		    		}
			    	
		    		//判断是否是重复生成文件
		    		if(order.getIsGenerated()==null){
		    			
		    			//对优惠进行加1操作
		    			if(benefit.getTimes()!=null && !benefit.getTimes().equals("")){
		    				benefit.setTimes((Integer.parseInt(benefit.getTimes())+1)+"");
		    				benefitService.update(benefit);
		    			}else{
		    				benefit.setTimes("1");
		    				benefitService.update(benefit);
		    			}
		    			//在用户信息表中 对用户优惠总次数进行加1处理
		    			if(user.getTimes()!=null && !user.getTimes().equals("")){
		    				user.setTimes((Integer.parseInt(user.getTimes())+1)+"");
		    				userService.updateUser(user);
		    			}else{
		    				user.setTimes("1");
		    				userService.updateUser(user);
		    			}
		    			
		    			if(user.getUserProperty().getUserProperty()==8 && !benefit.getTotalTimes().equals("always") && Integer.parseInt(benefit.getTotalTimes())-Integer.parseInt(benefit.getTimes())<=0){
		    				//更新优惠信息
		    				benefit.setStatus("used");
		    				benefitService.update(benefit);
		    				//如果用户已经没有其他的未使用优惠则将用户属性修改为普通注册用户
		    				//否则开始使用新的优惠
		    				if(benefitService.getBenefitsByUserAndStatus(user.getOpenid(), "unused").isEmpty()){
		    					UserProperty userProperty2=userPropertyService.getUserPropertyById(2);//获取userProperty
			    				user.setUserProperty(userProperty2);//设置user的属性
			    				userService.updateUser(user);//更新用户
		    				}
		    				
		    			}
		    		}
		    		
		    	}
		    	
		    	//四舍五入保留2位小数
				Double price=Double.parseDouble((order.getTotal_fee()))/100;
				BigDecimal  b   =  new BigDecimal(price);  
				price = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
				
				String chargebackInfo=order.getOut_trade_no()+","+price+","+info+",,"+"交易未结算资金";
				bw.write(chargebackInfo);  
		    	bw.write("\r\n");
		    	
		    	
		    	//将订单标识为已生成退款文件
		    	order.setIsGenerated("Yes");
		    	orderRecordService.updateOrderRecord(order);
			}
	    	
	    	bw.close();
	    	w.close();
	    	fos.close();
	    	
		} catch (Exception e) {
			logger.info("文件写异常");
			HttpUtil.sendInfoBack(response, "failure");
			e.printStackTrace();
			return;
		}
		HttpUtil.sendInfoBack(response, "success");
	}
	
	@RequestMapping(value="/download")
	public @ResponseBody void download(@RequestParam String fileName,HttpServletResponse response){
//		String path="D:/";
		String path="/data/wxws/web/chargebackFile/";// 文件路径
		fileName=path+fileName;
		HttpUtil.download(response, fileName, null, "utf-8");
	}
	
	
	
	//用户升级
	@RequestMapping(value="/admin")
	public String verifyAdmin(HttpServletRequest request,Model model,HttpServletResponse response){
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		String name=admin.getUsername();
//		String message;
		if(name==null||name.equals("")){
			return "temp";
		}else{
			model.addAttribute("user", admin);
			return "main";
		}
		}
	
	@RequestMapping(value="/allOrder")
	public String allOrder(){
		return "allOrder_web";
	}
		
	@RequestMapping(value="/chargeback")
	public String chargeback(HttpServletRequest request,Model model){
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		model.addAttribute("admin", admin);
		return "layout_order";
	}
	
	@RequestMapping(value="/user")
	public String user(HttpServletRequest request,HttpServletResponse response){
		String url=null;
		String property=request.getParameter("userProperty");
		if(property==null || property.equals("0")){
			url="allUser";
		}else{
			url="groupUser";
		}
		return "forward:/select/"+url;
		/*if(startTime!=null && endTime!=null){
			if(property==null || property.equals("0")){
				url="UserByTime";
			}else{
				url="UserByTimeAndProperty";
			}
			return "forward:/select/"+url;
		}*/
		
	/*HttpUtil.sendInfoBack(response, "error");
	return null;*/
	}
	
	@RequestMapping(value="/userlist")
	public String userlist(HttpServletRequest request,Model model){
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		model.addAttribute("user", admin);
		return "layout_user";
	}
	//洗衣机信息第二界面
	@RequestMapping(value="/secondMachien_app")
	public String secondMachien_app(HttpServletRequest request,Model model){
		
		System.out.println( request.getParameter("school"));
		
		model.addAttribute("school1", request.getParameter("school"));
		model.addAttribute("compus1", request.getParameter("compus"));
		model.addAttribute("building1", request.getParameter("building"));
		return "washer_detail_app";
	}
	
	@RequestMapping(value="/queryOrder")
	public String queryOrder(HttpServletRequest request){
		String url=null;
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
		if(startTime==null || endTime==null || startTime.equals("") || endTime.equals("")){
			url="allOrder";
		}else{
			url="timeOrder";
		}
		return "forward:/select/"+url;
	}
	
	@RequestMapping(value="/sinmachineDetail_app")
	public String machienDetail(HttpServletRequest request,Model model){
		String machineName=request.getParameter("machineName");
		System.out.println(machineName);
		return "washer_detail_app";
	}
	
	@RequestMapping(value="/operating")
	public String operating(HttpServletRequest request,Model model){
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		model.addAttribute("admin", admin);
		return "layout_operating";
	}
	
	@RequestMapping(value="/disinfectDetail_app")
	public String disinfectDetail(HttpServletRequest request,Model model){
		String machineName=request.getParameter("machineName");
		System.out.println(machineName);
		return "disinfect_detail_app";
	}
	
	@RequestMapping(value="/serviceDetail_app")
	public String serviceDetail(HttpServletRequest request,Model model){
		String machineName=request.getParameter("machineName");
		System.out.println(machineName);
		return "service_detail_app";
	}
	
	@RequestMapping(value="/partsDetail_app")
	public String partsDetail(HttpServletRequest request,Model model){
		String machineName=request.getParameter("machineName");
		return "parts_detail_app";
	}
	
	@RequestMapping(value="/machine_app")
	public String machineApp(HttpServletRequest request,Model model){
		return "washer_scan_app";
	}
	
	//getWelllidInfo
	@RequestMapping(value="/getWelllidInfo")
	public String getWelllidInfo(HttpServletRequest request,Model model){
		
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		
//		User user=userService.getUserById(admin.getOpenid());
//		
//		if(user==null)//如果查不到用户显示陆老师相关信息
//			user=userService.getByPhone("31010700001");
//		
//		String hql = "select count(*) from OrderRecord where wechatID.openid='"+user.getOpenid()+"'";
//		int times=openRecordService.getCount(hql);
//		
//		JSONObject jsonObject=HttpUtil.sendGet("上海","121.405224,31.327224").getJSONObject("result");
//		
//		model.addAttribute("humidity",jsonObject.getString("humidity"));
//		model.addAttribute("temp",jsonObject.getString("temp"));
//		model.addAttribute("location",user.getName());
//		model.addAttribute("latLon",user.getStudentID());
//		model.addAttribute("id",user.getTelephone());
//		model.addAttribute("openTiems",times);
//		
//		
//		
//		model.addAttribute("admin",admin);
		
		
		return "welllidInfoWeb";
	}
	
	//微信端 我--个人中心
	@RequestMapping(value="/info_app")
	public String userinfoapp(HttpServletRequest request,Model model){
		String openid=request.getParameter("openid");
		User user=userService.getUserById(openid);
		if(user==null){
			return "erro";
		}
		//如果是井盖信息组，则显示井盖信息页
		if(user.getUserProperty().getUserProperty()==20){
			
//			String deviceId=request.getParameter("deviceId");
//			String hql="select count(*) from OpenRecord where deviceId='"+deviceId+"'";
			String hql = "select count(*) from OrderRecord where wechatID.openid='"+user.getOpenid()+"'";
			int times=openRecordService.getCount(hql);
			
			model.addAttribute("user", user);
//			
			JSONObject jsonObject=HttpUtil.sendGet("上海","121.405224,31.327224").getJSONObject("result");
			model.addAttribute("humidity",jsonObject.getString("humidity"));
			model.addAttribute("temp",jsonObject.getString("temp"));
			
			model.addAttribute("openTimes", times);
			return "welllid_app";
			
		}
		model.addAttribute("user", user);
//		System.out.println(user.getTotalTimes());
//		System.out.println(user.getTimes());
//		//获取剩余优惠次数
		int sum=0;
		List<Benefit> benefits=benefitService.getBenefitsByUserAndStatus(user.getOpenid(), "using");
		if(benefits!=null){
			for(Benefit benefit:benefits){
				if(benefit.getTotalTimes().equals("always")){
					model.addAttribute("remaingTimes", "always");
					return "userInfoapp";
				}else{
					if(benefit.getTimes()==null || benefit.getTimes().equals("")){
						sum+=(Integer.parseInt(benefit.getTotalTimes()));
					}else{
						sum+=(Integer.parseInt(benefit.getTotalTimes())-Integer.parseInt(benefit.getTimes()));
					}
				}
			}
		}
		benefits=benefitService.getBenefitsByUserAndStatus(user.getOpenid(), "unused");
		if(benefits!=null){
			for(Benefit benefit:benefits){
				if(benefit.getTotalTimes().equals("always")){
					model.addAttribute("remaingTimes", "always");
					return "userInfoapp";
				}else{
					sum+=(Integer.parseInt(benefit.getTotalTimes()));
				}
			}
		}
		model.addAttribute("remaingTimes", sum+"");
		return "userInfoapp";
	}
	
	
	@RequestMapping(value="/info")
	public String infos(HttpServletRequest request,Model model){
		return "info";
	}
	
	@RequestMapping(value="/welllidLocation")
	public @ResponseBody JSONObject welllidLocation(HttpServletRequest request){
		//TODO
		User user=userService.getUserById(request.getParameter("openid"));
//		System.out.println(request.getParameter("openid"));
		
		if(user==null)//如果查不到用户显示陆老师相关信息
		user=userService.getByPhone("31010700001");
		
		String hql = "select count(*) from OrderRecord where wechatID.openid='"+user.getOpenid()+"'";
		int times=openRecordService.getCount(hql);
		
//		
		JSONObject jsonObject=HttpUtil.sendGet("上海","121.405224,31.327224").getJSONObject("result");
		
		JSONObject result=new JSONObject();
		result.put("location", user.getName());
		result.put("lonLat", user.getStudentID());
		result.put("id", user.getTelephone());
		result.put("temp", jsonObject.getString("temp"));
		result.put("humidity", jsonObject.getString("humidity"));
		result.put("openTimes", times);
		
		List total=new ArrayList();	
		total.add(result);
		JSONObject data=new JSONObject();
		data.put("total", 1);
		data.put("rows", total);
		return data;
	}
	
	@RequestMapping(value="/disinfect_app")
	public String disinfectApp(HttpServletRequest request,Model model){
		return "disinfect_record_app";
	}
	
	@RequestMapping(value="/parts_app")
	public String partsApp(HttpServletRequest request,Model model){
		return "parts_scan_app";
	}
	//维修记录
	@RequestMapping(value="/service_app")
	public String serviceApp(HttpServletRequest request,Model model){
		return "service_record_app";
	}
	
	@RequestMapping(value="/machine")
	public String machine(HttpServletRequest request,Model model){
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		logger.info(admin.getUsername()+"  "+new Date()+"进入机器一览界面");
		return "machine";
	}
	
	@RequestMapping(value="/maintenanceWeb")
	public String maintenanceWeb(HttpServletRequest request){
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		logger.info(admin.getUsername()+"  "+new Date()+"进入维护记录界面");
		return "maintenance";
	}
	
	@RequestMapping(value="/parts")
	public String parts(HttpServletRequest request,Model model){
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		logger.info(admin.getUsername()+"  "+new Date()+"进入配件记录界面");
		return "parts";
	}
	
	//微信端 维修中心
	@RequestMapping(value="/maintenance")
	public String maintenance(HttpServletRequest request,HttpServletResponse response,Model model){
		String openid=request.getParameter("openid");
		if(openid==null ||openid.equals("")){
			logger.info("openid为空");
			HttpUtil.sendInfoBack(response, "openid为空！");
			return "erro";
		}
		User user=userService.getUserById(openid);
		if(user==null){
			logger.info("无此用户");
			HttpUtil.sendInfoBack(response, "无此用户");
			return "erro";
		}
		if(user.getUserProperty().getGradeName().equals("service")){
			model.addAttribute("openid", openid);
			logger.info("maintain_app");
			return "maintain_app";
		}else{
			logger.info("maintain_app");
			return "sign_in_app";
		}
		
	}
	
	@RequestMapping(value="/upgradeuser")
	public String upgradeUser(HttpServletRequest request){
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		logger.info(admin.getUsername()+new Date()+"进入升级用户界面！");
		return "upgradeuser";
	}
	
	@RequestMapping(value="/addParts")
	public String addParts(HttpServletRequest request){
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		logger.info(admin.getUsername()+new Date()+"进入添加配件界面！");
		return "addParts";
	}
	
	@RequestMapping(value="/getMachineInfo")
	public String getMachineInfo(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		logger.info(admin.getUsername()+new Date()+"查询机器信息！");
		String school=request.getParameter("school");
		String compus=request.getParameter("compus");
		String machineName=request.getParameter("machineName");
		String url=null;
		if(school.equals("0")){
			url="allMachines";
		}else{
			if(compus.equals("0")){
				url="schMachines";
			}else{
				if(machineName.equals("0")){
					url="comMachines";
				}else{
					url="sinMachine";
				}
			}
		}
		return "forward:/select/"+url;
	}
	
	@RequestMapping(value="/getMaintenanceInfo")
	public String getMaintenanceInfo(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		logger.info(admin.getUsername()+new Date()+"查询维护信息！");
		String school=request.getParameter("school");
		String compus=request.getParameter("compus");
		String machineName=request.getParameter("machineName");
		String url=null;
		if(school.equals("0")){
			url="allMaintenanceRecord";
		}else{
			url="macMaintenanceRecord";
		}
		return "forward:/select/"+url;
	}
	
	@RequestMapping(value="/manage")
	public String manage(HttpServletRequest request,Model model){
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		logger.info(admin.getUsername()+new Date()+"进入管理界面！");
		model.addAttribute("user", admin);
		return "management";
	}
	
	@RequestMapping(value="/addAdmin")
	public String addUser(HttpServletRequest request){
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		logger.info(admin.getUsername()+new Date()+"进入添加管理员界面！");
		return "addAdmin";
	}
	
	@RequestMapping(value="/getPartsInfo")
	public String getPartsInfo(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		logger.info(admin.getUsername()+new Date()+"查询配件信息！");
		String partsId=request.getParameter("partsId");
		String url=null;
		if(partsId.equals("") || partsId==null){
			url="getAllParts";
		}else{
			url="sinParts";
		}
		return "forward:/select/"+url;
	}
		
	
	@RequestMapping(value="/discount")
	public String toDiscount(HttpServletRequest request,HttpServletResponse response){
		return "discount";	
	}
	
	@RequestMapping(value="/intern")
	public String toInternPage(HttpServletRequest request,HttpServletResponse response){
		return "intern";	
	} 
	
	@RequestMapping(value="/udpSend")
	public String udpSend(FormNum formNum,Model model){
		try {
			welllidService.udpSend(formNum);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "welllidInfoWeb";
	}
}
