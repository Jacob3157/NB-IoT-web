package com.panhong.util;

import java.net.SocketAddress;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.panhong.dao.WelllidDao;
import com.panhong.model.Client;
import com.panhong.model.NB.Command;
import com.panhong.service.WelllidService;
import com.panhong.service.impl.WelllidServiceImpl;

public class UDPServerHandler extends IoHandlerAdapter {


	private static WelllidService welllidService;
	
	private List<Client> list = null;
	private List<Command> commandList = null;
	private int count = 0;
	private String error = null;
	public UDPServerHandler() {
		super();
		// 采用循环接收数据  
		SingleUdpList singleUdpList = SingleUdpList.getSingleUdpList();
    	list = singleUdpList.getList();
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		welllidService = context.getBean(WelllidServiceImpl.class);
		commandList = welllidService.getCommandInfo();
		StringBuffer sbu = new StringBuffer();
		sbu.append((char) 69);sbu.append((char) 82);sbu.append((char) 82);sbu.append((char) 79);
		sbu.append((char) 82);
		error = sbu.toString();
	}
	

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("Session created...");
        SocketAddress remoteAddress = session.getRemoteAddress();
        System.out.println(remoteAddress.toString());
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("Session opened...");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionClosed(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		System.out.println("Session Idle...");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		cause.printStackTrace();
        session.closeNow();
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		try {
			// 读取Socket中的数据，读到的数据放入inPacket封装的数组里  
			IoBuffer ioBuffer=(IoBuffer) message;
            byte[] dataBytes = new byte[ioBuffer.limit()-ioBuffer.position()];
            ioBuffer.get(dataBytes);
			// 判断inPacket.getData()和inBuff是否是同一个数组  
			String[] data = new String(dataBytes, 0 , dataBytes.length).split(":");				
            byte[] result = new byte[dataBytes.length];
            System.arraycopy(dataBytes, 0, result, 0, result.length);
			
			//将收到的数据存进list中
			Client client = new Client(data[0], Long.valueOf(data[1]));	
			String contentHex = getContentHex(result);
			client.setContentHex(contentHex);
			client.setContent(getContent(contentHex));
			System.out.println(list.toString());
			
			int i = 0;
			for(i = 0; i < 16; i++)
			{
				Command command = commandList.get(i);
				if(client.getContentHex().equals(command.getRequestHex())){
					client.setResponse(command.getResponse());
					client.setResponseHex(command.getResponseHex());
					break;
				}
			}
			byte[] buff = null;
			if(i == 16) {
				client.setResponse(error);
                String s = client.getResponse();
                buff = s.getBytes();
			}else {
				buff = strTobyte(client.getResponseHex());
			}
          //*********************************************** 回复数据

          // 组织IoBuffer数据包的方法：本方法才可以正确地让客户端UDP收到byte数组
          IoBuffer buf = IoBuffer.wrap(buff);
          int delayTime = SingleUdpList.getSingleUdpList().getDelayTime();
          if(delayTime != 0) {
//        	  System.out.println(System.currentTimeMillis());
        	  Thread.currentThread().sleep(delayTime);
//        	  System.out.println(System.currentTimeMillis());
          }
          // 向客户端写数据
          WriteFuture future = session.write(buf);
          // 在100毫秒超时间内等待写完成
          future.awaitUninterruptibly(100);
          // The message has been written successfully
          if( future.isWritten() ) {
              System.out.println("return successfully");
          }else{
              System.out.println("return failed");
          }
			System.out.println("发送结束");
			if(count >=10) {						//放入页面实时展示list，最多10个
				list.remove(0);
				list.add(client);
				count++;
			}else {
				list.add(client);
				count++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String getContent(String contentHex) {
		String content = "";
		String[] contentHexs = contentHex.split("-");
		for(int i = 0; i < contentHexs.length; i++) {
			content  = content + (char)Integer.parseInt(contentHexs[i],16);
		}
		
		return content;
	}

	private String getContentHex(byte[] data) {
		String contentHex = "";
		int ii = 58;
		byte b = (byte)ii;
		int flag = 0;
		for(int i = data.length - 1; i >= 0; i-- ) {
			if(data[i] == b) {
				if(flag == 0) {
					flag = 1;
				}
				else if(flag == 1){
					break;
				}
			}else {
				if(flag == 1) {
					String s = Integer.toHexString(data[i] & 0xFF);
					if(s.length() == 1) {
						s = "0" + s;
					}
					contentHex = "-" + s + contentHex;  
				}
			}
		}
		contentHex = contentHex.substring(1, contentHex.length());
		
		return contentHex.toUpperCase();
	}

	public static String strTo16(String s) {
	    String str = "";
	    for (int i = 0; i < s.length(); i++) {
	        int ch = (int) s.charAt(i);
	        String s4 = Integer.toHexString(ch);
	        str = str + "-" + s4;
	    }
	    str = str.substring(1);
	    return str;
	}
	
	public static byte[] strTobyte(String s) {
		String[] sList = s.split("-");
		byte[] bytes = new byte[sList.length];
		for(int i = 0; i < sList.length; i++) {
			int iValue = Integer.parseInt(sList[i], 16);
			bytes[i] = (byte)iValue;
		}
		
		return bytes;
	}
	

	
}
