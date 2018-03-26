package com.panhong.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.panhong.model.OrderRecord;
import com.panhong.util.PageBean;

public interface OrderRecordService {
//	增加订单记录
	public void addOrderRecord(OrderRecord orderRecord);
//	更新订单记录
	public void updateOrderRecord(OrderRecord orderReord);
//	删除订单记录
	public void deleteOrderRecord(OrderRecord orderRecord);
//	通过用户获取订单记录
	public PageBean getOrderRecordsByUser(String wechatID,int pageNo, int pageSize);
//	获取所有用户订单
	public PageBean getAllOrders(int pageNo, int pageSize);
//	通过时间获取订单信息
	public PageBean getOrderRecordByTime(int pageNo,int pageSize,String startTime,String endTime);
//	不分页
	public JSONObject getOrderRecordByTime(String startTime,String endTime);
	
	public OrderRecord getOrderRecordById(int id);
	
	//获取总记录数
	public int getCount(String hql);

}
