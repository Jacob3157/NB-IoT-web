package com.panhong.thread;

import java.util.TimerTask;

import com.panhong.util.HttpUtil;

public class InformTask extends TimerTask{
	
	private String openid;
	
	
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public InformTask(String openid){
		this.openid=openid;
	}

	@Override
	public void run() {
		HttpUtil.postOpenid(openid);
	}

}
