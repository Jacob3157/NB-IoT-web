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
		
        StringBuffer sbu = new StringBuffer();				//拼接获得的命令  字符型
        StringBuffer sbuHex = new StringBuffer();				//拼接获得的命令  16进制
        if(formNum.getOne() != null && formNum.getOne() != "" ) {
        	sbu.append((char) Integer.parseInt(formNum.getOne(),16));
        	sbuHex.append(checkNum(formNum.getOne()));sbuHex.append("-");				//如果只有一位，则在前面补零
        }
        if(formNum.getTwo() != null && formNum.getTwo() != "" ) {
        	sbu.append((char) Integer.parseInt(formNum.getTwo(),16));
        	sbuHex.append(checkNum(formNum.getTwo()));sbuHex.append("-");
        }
        if(formNum.getThree() != null && formNum.getThree() != "" ) {
        	sbu.append((char) Integer.parseInt(formNum.getThree(),16));
        	sbuHex.append(checkNum(formNum.getThree()));sbuHex.append("-");
        }
        if(formNum.getFour() != null && formNum.getFour() != "" ) {
        	sbu.append((char) Integer.parseInt(formNum.getFour(),16));
        	sbuHex.append(checkNum(formNum.getFour()));sbuHex.append("-");
        }
        if(formNum.getFive() != null && formNum.getFive() != "" ) {
        	sbu.append((char) Integer.parseInt(formNum.getFive(),16));
        	sbuHex.append(checkNum(formNum.getFive()));sbuHex.append("-");
        }
        if(formNum.getSix() != null && formNum.getSix() != "" ) {
        	sbu.append((char) Integer.parseInt(formNum.getSix(),16));
        	sbuHex.append(checkNum(formNum.getSix()));sbuHex.append("-");
        }
        if(formNum.getSeven() != null && formNum.getSeven() != "" ) {
        	sbu.append((char) Integer.parseInt(formNum.getSeven(),16));
        	sbuHex.append(checkNum(formNum.getSeven()));sbuHex.append("-");
        }
        if(formNum.getEight() != null && formNum.getEight() != "" ) {
        	sbu.append((char) Integer.parseInt(formNum.getEight(),16));        
        	sbuHex.append(checkNum(formNum.getEight()));sbuHex.append("-");
        }
        String s = sbu.toString();
        sbuHex.deleteCharAt(sbuHex.length()-1);
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
