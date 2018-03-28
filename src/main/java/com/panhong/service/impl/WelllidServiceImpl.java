package com.panhong.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.panhong.dao.WelllidDao;
import com.panhong.model.FormNum;
import com.panhong.model.NB.Command;
import com.panhong.service.WelllidService;
import com.panhong.util.SingleUdpList;

@Service
public class WelllidServiceImpl implements WelllidService {

	@Resource
	private WelllidDao welllidDao;
	@Override
	public void udpSend(FormNum formNum) throws IOException {
        StringBuffer sbu = new StringBuffer();
        sbu.append((char) Integer.parseInt(formNum.getOne()));
        sbu.append((char) Integer.parseInt(formNum.getTwo()));
        sbu.append((char) Integer.parseInt(formNum.getThree()));
        sbu.append((char) Integer.parseInt(formNum.getFour()));
        sbu.append((char) Integer.parseInt(formNum.getFive()));
        sbu.append((char) Integer.parseInt(formNum.getSix()));
        sbu.append((char) Integer.parseInt(formNum.getSeven()));
        sbu.append((char) Integer.parseInt(formNum.getEight()));
        
        String s = sbu.toString();
        SingleUdpList.getSingleUdpList().getList().get(0).setResponse(s);
        System.out.println(s);
	}

	@Override
	public void updateCommand(FormNum formNum) {
		Command command =  SingleUdpList.getSingleUdpList()
				.getCommandList().get(formNum.getNum());//获取修改的Command对象
		
        StringBuffer sbu = new StringBuffer();				//拼接获得的命令
        sbu.append((char) Integer.parseInt(formNum.getOne(),16));
        sbu.append((char) Integer.parseInt(formNum.getTwo(),16));
        sbu.append((char) Integer.parseInt(formNum.getThree(),16));
        sbu.append((char) Integer.parseInt(formNum.getFour(),16));
        sbu.append((char) Integer.parseInt(formNum.getFive(),16));
        sbu.append((char) Integer.parseInt(formNum.getSix(),16));
        sbu.append((char) Integer.parseInt(formNum.getSeven(),16));
        sbu.append((char) Integer.parseInt(formNum.getEight(),16));        
        String s = sbu.toString();
        
        StringBuffer sbuHex = new StringBuffer();				//拼接获得的命令
        sbuHex.append(checkNum(formNum.getOne()));sbuHex.append("-");				//如果只有一位，则在前面补零
        sbuHex.append(checkNum(formNum.getTwo()));sbuHex.append("-");
        sbuHex.append(checkNum(formNum.getThree()));sbuHex.append("-");
        sbuHex.append(checkNum(formNum.getFour()));sbuHex.append("-");
        sbuHex.append(checkNum(formNum.getFive()));sbuHex.append("-");
        sbuHex.append(checkNum(formNum.getSix()));sbuHex.append("-");
        sbuHex.append(checkNum(formNum.getSeven()));sbuHex.append("-");
        sbuHex.append(checkNum(formNum.getEight()));
        String sHex = sbuHex.toString().toUpperCase();
        
        if(formNum.getType() == 1) {
        	command.setRequest(s);
        	command.setRequestHex(sHex);
        }
        else if(formNum.getType() == 2) {
        	command.setResponse(s);
        	command.setResponseHex(sHex);
        }
        welllidDao.updateCommand(formNum.getType(), formNum.getNum(), s, sHex);
	}

	@Override
	public void updateCommandName(FormNum formNum) throws UnsupportedEncodingException {
		Command command =  SingleUdpList.getSingleUdpList()
				.getCommandList().get(formNum.getNum());//获取修改的Command对象
		if(formNum.getType() == 1) {
			command.setRequestName(new String(formNum.getName().getBytes("iso-8859-1"),"utf-8"));
		}else if(formNum.getType() == 2) {
			command.setResponseName(new String(formNum.getName().getBytes("iso-8859-1"),"utf-8"));
		}
		welllidDao.updateCommandName(formNum.getType(), formNum.getNum(), new String(formNum.getName().getBytes("iso-8859-1"),"utf-8"));
	}

	
	private String checkNum(String s) {
		if(s.length() == 1) {
			s = "0" + s;
		}
		return s;
	}

	@Override
	public void updateDelayTime(int delayTime) {
		SingleUdpList obj = SingleUdpList.getSingleUdpList();
		
		obj.setDelayTime(delayTime);
		welllidDao.updateDelayTime(delayTime);
		
	}

	@Override
	public List<Command> getCommandInfo() {
		return welllidDao.getCommandInfo();
	}

	@Override
	public Command getCommandById(int id) {
		return welllidDao.getCommandById(id);
	}

	@Override
	public int getDelayTime() {
		return welllidDao.getDelayTime();
	}
}
