package com.panhong.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.panhong.model.FormNum;
import com.panhong.model.NB.Command;

public interface WelllidService {
	public void udpSend(FormNum formNum) throws IOException;
	
	public void updateCommand(FormNum formNum);

	public void updateCommandName(FormNum formNum) throws UnsupportedEncodingException;

	public void updateDelayTime(int delayTime);

	public List<Command> getCommandInfo();
	
	public Command	getCommandById(int id);

	public int getDelayTime();
}
