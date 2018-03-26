package com.panhong.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.panhong.model.FormNum;

public interface WelllidService {
	public void udpSend(FormNum formNum) throws IOException;
	
	public void updateCommand(FormNum formNum);

	public void updateCommandName(FormNum formNum) throws UnsupportedEncodingException;
}
