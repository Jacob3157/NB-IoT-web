package com.panhong.service;

import java.util.List;

import com.panhong.model.Benefit;
import com.panhong.util.PageBean;

public interface BenefitService {
	
	
//新增优惠信息
	public void addBenefit(Benefit benefit);
	
//		更新优惠信息
	public void update(Benefit benefit);
	
//		删除优惠信息
	public void deleteBenefit(Benefit benefit);
	
//		查询优惠信息
	public PageBean getAllBenefit(int pageNo, int pageSize);
	
	public Benefit getBenefitById(int id);
	
//		获取某位用户优惠信息
	public PageBean getBenefitsByUser(String openid,int pageNo, int pageSize);
	
	//查询某位用户剩余优惠次数 优惠信息状态只包括使用中 未使用 
	public String getRemainingTimesByUser(String openid);
	
//		通过用户及优惠状态获取优惠信息
	public List<Benefit> getBenefitsByUserAndStatus(String openid,String status);//用于订单处理
	
	public PageBean getBenefitsByUserAndStatus(String openid,String status,int pageNo, int pageSize);//用于订单分页显示
}
