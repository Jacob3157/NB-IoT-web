package com.panhong.dao;

import java.util.List;

import com.panhong.model.Benefit;

public interface BenefitDao {
	
	//新增优惠信息
	public void addBenefit(Benefit benefit);
	
//	更新优惠信息
	public void update(Benefit benefit);
	
//	删除优惠信息
	public void deleteBenefit(Benefit benefit);
	
//	查询优惠信息
	public List<Benefit> getAllBenefit(int pageNo, int pageSize);
	//通过Id获取benefit
	public Benefit getBenefitById(int id);
	
//	获取某位用户优惠信息
	public List<Benefit> getBenefitsByUser(String openid,int pageNo, int pageSize);
	
//	通过用户及优惠状态获取优惠信息
	public List<Benefit> getBenefitsByUserAndStatus(String openid,String status);//用于订单处理
	
	public List<Benefit> getBenefitsByUserAndStatus(String openid,String status,int pageNo, int pageSize);//用于订单分页显示
	//获取总记录数
	public int getCount(String hql);


}
