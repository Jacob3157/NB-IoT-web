package com.panhong.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.panhong.dao.OrderRecordDao;
import com.panhong.dao.impl.OrderRecordDaoImpl;
import com.panhong.model.OrderRecord;
import com.panhong.service.OrderRecordService;
import com.panhong.util.CommonUtil;
import com.panhong.util.PageBean;
import com.sun.media.jfxmedia.logging.Logger;


@Service
public class OrderRecordServiceImpl implements OrderRecordService {
	
	//日志显示
	private static final Log logger = LogFactory
		            .getLog(OrderRecordServiceImpl.class);

	@Resource
	private OrderRecordDao orderRecordDao;
	
	public OrderRecordDao getOrderRecordDao() {
		return orderRecordDao;
	}

	public void setOrderRecordDao(OrderRecordDao orderRecordDao) {
		this.orderRecordDao = orderRecordDao;
	}

	@Override
	public void addOrderRecord(OrderRecord orderRecord) {
		orderRecordDao.addOrderRecord(orderRecord);
	}

	@Override
	public void updateOrderRecord(OrderRecord orderReord) {
		orderRecordDao.updateOrderRecord(orderReord);
	}

	@Override
	public void deleteOrderRecord(OrderRecord orderRecord) {
		orderRecordDao.deleteOrderRecord(orderRecord);
	}

	@Override
	public PageBean getOrderRecordsByUser(String wechatID,int pageNo, int pageSize) {
		String hql = "select count(*) from OrderRecord where wechatID.openid='"+wechatID+"'";
		int count=orderRecordDao.getCount(hql);
		int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
		int currentPage = PageBean.countCurrentPage(pageNo);
		List<OrderRecord> list=orderRecordDao.getOrderRecordsByUser(wechatID,pageNo,pageSize);
		PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotal(count);
        pageBean.setTotalPage(totalPage);
        pageBean.setRows(list);
        pageBean.init();
        return pageBean;
	}

	@Override
	public PageBean getAllOrders(int pageNo, int pageSize) {
		String hql = "select count(*) from OrderRecord";
		int count=orderRecordDao.getCount(hql);
		int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
		int currentPage = PageBean.countCurrentPage(pageNo);
		List<OrderRecord> list=orderRecordDao.getAllOrderRecord(pageNo, pageSize);
		PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotal(count);
        pageBean.setTotalPage(totalPage);
        pageBean.setRows(list);
        pageBean.init();
        return pageBean;
	}

	@Override
	public PageBean getOrderRecordByTime(int pageNo, int pageSize,
			String startTime, String endTime) {
		String hql = "select count(*) from OrderRecord where time_end>='"+startTime+"' and time_end<='"+endTime+"'";
		int count=orderRecordDao.getCount(hql);
		int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
		int currentPage = PageBean.countCurrentPage(pageNo);
		List<OrderRecord> list=orderRecordDao.getOrderRecordByTime(pageNo, pageSize, startTime, endTime);
		PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotal(count);
        pageBean.setTotalPage(totalPage);
        pageBean.setRows(list);
        pageBean.init();
        return pageBean;
	}

	@Override
	public JSONObject getOrderRecordByTime(String startTime,
			String endTime) {
		JSONObject data=new JSONObject();
		String hql = "select count(*) from OrderRecord where time_end>='"+startTime+"' and time_end<='"+endTime+"'";
		int count=orderRecordDao.getCount(hql);
//		添加未注册用户订单
		List<OrderRecord> total=orderRecordDao.getOrderRecordByUserProperty(1,startTime, endTime);
		try {
		 total=CommonUtil.validateOrder(total);
		
		//添加注册用户订单
		List<OrderRecord> list=orderRecordDao.getOrderRecordByUserProperty(2,startTime, endTime);
		list=CommonUtil.validateOrder(list);
		if(list!=null){
			if(total==null){
				total=list;
			}else{
				total.addAll(list);
			}
		}
//		添加管理员订单
		list=orderRecordDao.getOrderRecordByUserProperty(3,startTime, endTime);
		list=CommonUtil.validateOrder(list);
		
		if(list!=null){
			if(total==null){
				total=list;
			}else{
				total.addAll(list);
			}
		}
//		添加优惠用户订单
		list=orderRecordDao.getOrderRecordByUserProperty(8,startTime, endTime);
		list=CommonUtil.validateOrder(list);
		
		if(list!=null){
			if(total==null){
				total=list;
			}else{
				total.addAll(list);
			}
		}
//		添加工作人员订单
		list=orderRecordDao.getOrderRecordByUserProperty(10,startTime, endTime);
		list=CommonUtil.validateOrder(list);
		
		if(list!=null){
			if(total==null){
				total=list;
			}else{
				total.addAll(list);
			}
		}
		
		
		} catch (ParseException e) {
			System.out.println("日期解析异常");
			e.printStackTrace();
		}
		data.put("total", count);
		data.put("rows", total);
		return data;
	}

	@Override
	public OrderRecord getOrderRecordById(int id) {
		return orderRecordDao.getOrderRecordById(id);
	}

	@Override
	public int getCount(String hql) {
		return orderRecordDao.getCount(hql);
	}

}
