package com.panhong.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.panhong.controller.CheckController;
import com.panhong.dao.BenefitDao;
import com.panhong.model.Benefit;
import com.panhong.model.User;
import com.panhong.service.BenefitService;
import com.panhong.util.PageBean;
import com.sun.media.jfxmedia.logging.Logger;


@Service("benefitService")
public class BenefitServieImpl implements BenefitService {
	
	@Resource
	private BenefitDao benefitDao;

	public BenefitDao getBenefitDao() {
		return benefitDao;
	}

	public void setBenefitDao(BenefitDao benefitDao) {
		this.benefitDao = benefitDao;
	}

	@Override
	public void addBenefit(Benefit benefit) {
		benefitDao.addBenefit(benefit);
	}

	@Override
	public void update(Benefit benefit) {
		benefitDao.update(benefit);
	}

	@Override
	public void deleteBenefit(Benefit benefit) {
		benefitDao.deleteBenefit(benefit);
	}
	
	private static final Log logger = LogFactory
            .getLog(BenefitService.class);

	@Override
	public PageBean getAllBenefit(int pageNo, int pageSize) {
		String hql = "select count(*) from Benefit";
        int count = benefitDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        int currentPage = PageBean.countCurrentPage(pageNo);
        List<Benefit> list = benefitDao.getAllBenefit(pageNo, pageSize);
        // 把分页信息保存到Bean中
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
	public PageBean getBenefitsByUser(String openid, int pageNo, int pageSize) {
		String hql = "select count(*) from Benefit";
        int count = benefitDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        int currentPage = PageBean.countCurrentPage(pageNo);
        List<Benefit> list = benefitDao.getBenefitsByUser(openid, pageNo, pageSize);
        // 把分页信息保存到Bean中
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
	public List<Benefit> getBenefitsByUserAndStatus(String openid, String status) {
		
        List<Benefit> list = benefitDao.getBenefitsByUserAndStatus(openid, status);
        // 把分页信息保存到Bean中
       
        return list;
	}

	@Override
	public PageBean getBenefitsByUserAndStatus(String openid, String status,
			int pageNo, int pageSize) {
		String hql = "select count(*) from Benefit";
        int count = benefitDao.getCount(hql); // 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count); // 总页数
        int currentPage = PageBean.countCurrentPage(pageNo);
        List<Benefit> list = benefitDao.getBenefitsByUserAndStatus(openid, status, pageNo, pageSize);
        // 把分页信息保存到Bean中
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
	public Benefit getBenefitById(int id) {
		return benefitDao.getBenefitById(id);
	}

	@Override
	public String getRemainingTimesByUser(String openid) {
		int remainingTimes=0;
		//先获取使用中的优惠剩余次数
		List<Benefit> list=benefitDao.getBenefitsByUserAndStatus(openid, "using");
		if(list.isEmpty()){//判空，如果为空，则什么都不做
			logger.info("该用户正在使用次数为空");
		}else{
			for(Benefit benefit:list){
				if(benefit.getTotalTimes().equals("always")){
					return "always";
				}
				int totalTimes=Integer.parseInt(benefit.getTotalTimes());
				int times=Integer.parseInt(benefit.getTimes());
				remainingTimes+=(totalTimes-times);
			}
		}
		
		list=benefitDao.getBenefitsByUserAndStatus(openid, "unused");
		if(list.isEmpty()){//判空，如果为空，则什么都不做
			logger.info("该用户未使用次数为空");
		}else{
			for(Benefit benefit:list){
				if(benefit.getTotalTimes().equals("always")){
					return "always";
				}
				int totalTimes=Integer.parseInt(benefit.getTotalTimes());
				remainingTimes+=totalTimes;
			}
		}
		
		return remainingTimes+"";//返回剩余优惠次数
	}

}
