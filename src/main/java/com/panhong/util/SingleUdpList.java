package com.panhong.util;


import java.util.ArrayList;
import java.util.List;

import com.panhong.model.Client;
import com.panhong.model.NB.Command;


public class SingleUdpList {

	private List<Client> list = new ArrayList<Client>();
	private List<Command> commandList = new ArrayList<Command>();
	private static SingleUdpList single_list = new SingleUdpList();
	private int delayTime;
	private SingleUdpList() {
		for(int i = 0; i < 16; i++) {
			commandList.add(new Command(i));
		}
	}
	
	public static SingleUdpList getSingleUdpList() {
		
		return single_list;
	}

	public List<Client> getList() {
		return list;
	}

	public void setList(List<Client> list) {
		this.list = list;
	}

	public List<Command> getCommandList() {
		return commandList;
	}

	public void setCommandList(List<Command> commandList) {
		this.commandList = commandList;
	}

	public int getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}

	
}
