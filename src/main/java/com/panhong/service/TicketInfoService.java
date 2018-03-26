package com.panhong.service;

import java.util.List;

import com.panhong.model.TicketInfo;

public interface TicketInfoService {

//增加token信息
	public void addTicket(TicketInfo ticketInfo);
	
//	删除token
	public void deleteTicket(TicketInfo ticketInfo);
	
//	更新token
	public void updateTicket(TicketInfo ticketInfo);
	
//	获取最新token
	public TicketInfo getLatestTicket();
	
	//通过token获取tokenInfo
	public TicketInfo getTicketByticket(String ticket);
	
	//获取所有token
	public List<TicketInfo> getAllTicket();
}
