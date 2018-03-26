package com.panhong.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.panhong.dao.TicketInfoDao;
import com.panhong.model.TicketInfo;
import com.panhong.service.TicketInfoService;

@Service("ticketInfoService")
public class TicketInfoServiceImpl implements TicketInfoService {
	
	@Resource
	private TicketInfoDao ticketInfoDao;

	public TicketInfoDao getTicketInfoDao() {
		return ticketInfoDao;
	}

	public void setTicketInfoDao(TicketInfoDao ticketInfoDao) {
		this.ticketInfoDao = ticketInfoDao;
	}

	@Override
	public void addTicket(TicketInfo ticketInfo) {
		ticketInfoDao.addTicket(ticketInfo);
	}

	@Override
	public void deleteTicket(TicketInfo ticketInfo) {
		ticketInfoDao.deleteTicket(ticketInfo);
	}

	@Override
	public void updateTicket(TicketInfo ticketInfo) {
		ticketInfoDao.updateTicket(ticketInfo);
	}

	@Override
	public TicketInfo getLatestTicket() {
		return ticketInfoDao.getLatestTicket();
	}

	@Override
	public TicketInfo getTicketByticket(String ticket) {
		return ticketInfoDao.getTicketByticket(ticket);
	}

	@Override
	public List<TicketInfo> getAllTicket() {
		return ticketInfoDao.getAllTicket();
	}

}
