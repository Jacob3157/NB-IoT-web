package com.panhong.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.panhong.model.Benefit;
import com.panhong.model.OrderRecord;
import com.panhong.model.User;
import com.panhong.model.UserProperty;
import com.panhong.service.BenefitService;
import com.panhong.service.TicketInfoService;
import com.panhong.service.TokenInfoService;
import com.panhong.service.UserPropertyService;
import com.panhong.service.UserService;

public class CommonUtil {
	
	//退款时间间隔 20分钟
	public static final int TIME_INTERVAL=20*60*1000;
	
	//日志显示
	private static final Log logger = LogFactory
	            .getLog(CommonUtil.class);
	
	public static String formatTime(String str){
		DateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat format2 = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			Date date=format1.parse(str);
			return format2.format(date).toString();
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date format2Time(String str){
		DateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
//		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date=format1.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//查看用户是否有优惠订单并给予用户优惠，并操作用户优惠信息
	/**
	 * @author alaric
	 * @param openid 用户openid
	 * @return 返回退款建议信息 以数字表示 0不建议退款  1表示测试员  2表示首次优惠    0 1 2 全为字符串  3不建议退款  4管理员 5优惠用户
	 * @throws ParseException 
	 */
	public static String checkBenefits(String openid,String orderTime,String reason) throws ParseException{
		String result=null;//用于存储返回的结果
		//查看用户是否有使用中优惠 using
		List<Benefit> list=null;
		ApplicationContext context=APPContextUtil.getContext();
    	BenefitService benefitService=(BenefitService) context.getBean("benefitService");
    	list=benefitService.getBenefitsByUserAndStatus(openid, "using");
    	if(list.isEmpty()){//如果为空 检查有没有未使用优惠
    		
//    		System.out.println("判断是否有未使用优惠券");
    		list=benefitService.getBenefitsByUserAndStatus(openid, "unused");
    		if(list.isEmpty()){//如果为空 表明该用户没有优惠
    			result="3";//不建议退款
    			return result;
    		}else{//否则使用该优惠 但要判断优惠是否合法
    			Benefit benefit=benefitService.getBenefitById(list.get(0).getId());
    			//判断优惠总次数是否为0
    			if(benefit.getTotalTimes().equals("0")){
    				//说明订单已经使用完
    				/*benefit.setStatus("used");
    				try{
    					benefitService.update(benefit);
    				}catch(Exception e){
    					e.printStackTrace();
    				}*/
    				result="3";
    				return result;
    			}
    			//判断订单时间是否合法
    			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
				Date time=sdf.parse(orderTime);
				if(time.before(benefit.getStartTime()) || time.after(benefit.getEndTime())){ //如果时间超出优惠时间段 即要么早于开始时间或者晚于结束时间 则不进行退款
					//说明订单已经使用完
					result="3";
					return result;
				}
				
				/*//开始使用优惠
				System.out.println("设置订单状态为using");
				benefit.setStatus("using");//将订单状态设置为using
				try{
					System.out.println(benefit.getStatus());
					benefitService.update(benefit);//更新Benefit记录
				}catch(Exception e){
					e.printStackTrace();
				}*/
				
				result=reason;//退款原因
				return result;
				
    		}
    	}else{//如果有使用中订单
    		Benefit benefit=benefitService.getBenefitById(list.get(0).getId());
    		//判断订单时间是否合法
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			Date time=sdf.parse(orderTime);
			if(time.before(benefit.getStartTime()) || time.after(benefit.getEndTime())){ //如果时间超出优惠时间段 即要么早于开始时间或者晚于结束时间 则不进行退款
				//说明订单已经使用完
				result="3";
				return result;
			}
			if(benefit.getTotalTimes().equals("always")){
				result=reason;//退款原因
				return result;
			}else if(Integer.parseInt(benefit.getTotalTimes())-Integer.parseInt(benefit.getTimes())>0){//给每个新订单 使用次数设置为0
				result=reason;//退款原因
				return result;
			}else{
		/*		//说明订单已经使用完
				benefit.setStatus("used");
				benefitService.update(benefit);*/
				result="3";
				return result;
			}
			
    	}
		
	}
	
	public static List<OrderRecord> validateOrder(List<OrderRecord> orders) throws ParseException{
		
//		ApplicationContext context=APPContextUtil.getContext();
//    	UserService userService=(UserService) context.getBean("userService");
		
		if(orders.isEmpty()) return null;
		int userProperty =orders.get(0).getWechatID().getUserProperty().getUserProperty();
		//工作人员订单
		if(userProperty==10 || userProperty==20){
			for(OrderRecord order : orders){
				// 0表示操作失误  1表示测试员  2表示首次优惠    0 1 2 全为字符串  3不建议退款  4管理员 5优惠用户
				if(order.getChargeback()==null || order.getChargeback().equals("")){
					order.setChargeback("1");
				}
			}
		}else if(userProperty==1){  //未注册用户订单
			for(int i=0;i<orders.size();i++){
				if(orders.get(i).getChargeback()==null || orders.get(i).getChargeback().equals("")){
					int cache=i;
					for(int j=i+1;j<orders.size();j++){
						if(orders.get(i).getMachineID().getMachineID().equals(orders.get(j).getMachineID().getMachineID()) && 
								orders.get(i).getWechatID().getOpenid().equals(orders.get(j).getWechatID().getOpenid())){
							SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
							Date date1=sdf.parse(orders.get(i).getTime_end());
							Date date2=sdf.parse(orders.get(j).getTime_end());
							date1.setTime(date1.getTime()+TIME_INTERVAL);
							if(date1.after(date2)){
								orders.get(i).setChargeback("0");
								cache=j;
								break;
							}
						}
					}
					if(cache==i){
						orders.get(i).setChargeback("3");
					}
				}
			}
		}else if(userProperty==2){ //注册用户订单
			for(int i=0;i<orders.size();i++){
				if(orders.get(i).getChargeback()==null || orders.get(i).getChargeback().equals("")){
					int cache=i;
					for(int j=i+1;j<orders.size();j++){
						if(orders.get(i).getMachineID().getMachineID().equals(orders.get(j).getMachineID().getMachineID()) && 
								orders.get(i).getWechatID().getOpenid().equals(orders.get(j).getWechatID().getOpenid())){
							SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
							Date date1=sdf.parse(orders.get(i).getTime_end());
							Date date2=sdf.parse(orders.get(j).getTime_end());
							date1.setTime(date1.getTime()+TIME_INTERVAL);
							if(date1.after(date2)){
								orders.get(i).setChargeback("0");
								cache=j;
								break;
							}
						}
					}
					if(cache==i){//查看用户是否有优惠未使用
//						String openid=orders.get(i).getWechatID().getOpenid();
//						String result=checkBenefits(openid, orders.get(i).getTime_end(),"5");
						//注册用户 而又不是优惠用户 则不进行退款
						orders.get(i).setChargeback("3");
					}
				}
			}
		}else if(userProperty==3){//宿舍管理员订单
			for(int i=0;i<orders.size();i++){
				if(orders.get(i).getChargeback()==null || orders.get(i).getChargeback().equals("")){
					int cache=i;
					for(int j=i+1;j<orders.size();j++){
						if(orders.get(i).getMachineID().getMachineID().equals(orders.get(j).getMachineID().getMachineID()) && 
								orders.get(i).getWechatID().getOpenid().equals(orders.get(j).getWechatID().getOpenid())){
							SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
							Date date1=sdf.parse(orders.get(i).getTime_end());
							Date date2=sdf.parse(orders.get(j).getTime_end());
							date1.setTime(date1.getTime()+TIME_INTERVAL);
							if(date1.after(date2)){
								orders.get(i).setChargeback("0");
								cache=j;
								break;
							}
						}
					}
					if(cache==i){
						String openid=orders.get(i).getWechatID().getOpenid();
						
						String result=checkBenefits(openid, orders.get(i).getTime_end(),"4");
						
						orders.get(i).setChargeback(result);
						
					}
				}
			}
		}else if(userProperty==8){//优惠用户订单
			for(int i=0;i<orders.size();i++){
				if(orders.get(i).getChargeback()==null || orders.get(i).getChargeback().equals("")){
					int cache=i;
					for(int j=i+1;j<orders.size();j++){
						if(orders.get(i).getMachineID().getMachineID().equals(orders.get(j).getMachineID().getMachineID()) && 
								orders.get(i).getWechatID().getOpenid().equals(orders.get(j).getWechatID().getOpenid())){
							SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
							Date date1=sdf.parse(orders.get(i).getTime_end());
							Date date2=sdf.parse(orders.get(j).getTime_end());
							date1.setTime(date1.getTime()+TIME_INTERVAL);
							if(date1.after(date2)){
								orders.get(i).setChargeback("0");
								cache=j;
								break;
							}
						}
					}
					if(cache==i){
						
						String openid=orders.get(i).getWechatID().getOpenid();
						
						String result=checkBenefits(openid, orders.get(i).getTime_end(),"5");
						
						orders.get(i).setChargeback(result);
						
						
					}
				}
			}
		}
		return orders;
		
	}

}
