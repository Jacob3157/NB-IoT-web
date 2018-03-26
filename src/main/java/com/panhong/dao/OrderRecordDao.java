package com.panhong.dao;

import java.util.List;

import com.panhong.model.OrderRecord;

public interface OrderRecordDao {
//	增加订单记录
	public void addOrderRecord(OrderRecord orderRecord);
//	更新订单记录
	public void updateOrderRecord(OrderRecord orderReord);
//	删除订单记录
	public void deleteOrderRecord(OrderRecord orderRecord);
//	通过用户获取订单记录
	public List<OrderRecord> getOrderRecordsByUser(String wechatID,int pageNo, int pageSize);
//	查询所有订单
	public List<OrderRecord> getAllOrderRecord(int pageNo, int pageSize);
//	根据交易完成时间，进行退单
	public List<OrderRecord> getOrderRecordByTime(int pageNo,int pageSize,String startTime,String endTime);
	//no pagination
	public List<OrderRecord> getOrderRecordByTime(String price,String startTime,String endTime);
	//no pagination
	public List<OrderRecord> getOrderRecordByUserProperty(int userProperty,String startTime,String endTime);
	//获取总记录数
	public int getCount(String hql);
	
	public OrderRecord getOrderRecordById(int id);
}
