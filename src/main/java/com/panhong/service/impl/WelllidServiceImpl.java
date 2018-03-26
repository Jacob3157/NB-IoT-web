package com.panhong.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import org.springframework.stereotype.Service;

import com.panhong.model.Command;
import com.panhong.model.FormNum;
import com.panhong.service.WelllidService;
import com.panhong.util.SingleUdpList;

@Service
public class WelllidServiceImpl implements WelllidService {

    // 定义发送数据报的目的地  
//    public static final int DEST_PORT = 30001;  
//    public static final String DEST_IP = "127.0.0.1";  
//    // 定义一个用于发送的DatagramPacket对象  
//    private DatagramPacket outPacket = null;  
	@Override
	public void udpSend(FormNum formNum) throws IOException {
		// TODO Auto-generated method stub
		// 创建一个客户端DatagramSocket，使用端口30002发送  
//        DatagramSocket socket = new DatagramSocket(30002);
        // 初始化发送用的DatagramSocket，它包含一个长度为0的字节数组  
//        outPacket = new DatagramPacket(new byte[0] , 0  , InetAddress.getByName(DEST_IP) , DEST_PORT);  
        // 设置发送用的DatagramPacket中的字节数据  
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
//        byte[] buff = s.getBytes();
//        System.out.println(buff.length);
//        outPacket.setData(buff);  
//        // 发送数据报  
//        socket.send(outPacket); 
//        socket.close();
	}

	@Override
	public void updateCommand(FormNum formNum) {
		// TODO Auto-generated method stub
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
	}

	@Override
	public void updateCommandName(FormNum formNum) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		Command command =  SingleUdpList.getSingleUdpList()
				.getCommandList().get(formNum.getNum());//获取修改的Command对象
		if(formNum.getType() == 1) {
			command.setRequestName(new String(formNum.getName().getBytes("iso-8859-1"),"utf-8"));
		}else if(formNum.getType() == 2) {
			command.setResponseName(new String(formNum.getName().getBytes("iso-8859-1"),"utf-8"));
		}
		
	}

	
	private String checkNum(String s) {
		if(s.length() == 1) {
			s = "0" + s;
		}
		return s;
	}
}
