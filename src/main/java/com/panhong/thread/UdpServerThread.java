package com.panhong.thread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;

import com.panhong.model.Client;
import com.panhong.model.Command;
import com.panhong.util.SingleUdpList;


public class UdpServerThread implements Runnable {

	public static final int DEST_PORT = 30001;  
    public static final String DEST_IP = "127.0.0.1"; 
	public static final int PORT = 30000;  
    // 定义每个数据报的最大大小为4KB  
    private static final int DATA_LEN = 4096;  
    // 定义接收网络数据的字节数组  
    byte[] inBuff = new byte[DATA_LEN];  
    // 以指定字节数组创建准备接收数据的DatagramPacket对象  
    private DatagramPacket inPacket =   
        new DatagramPacket(inBuff , inBuff.length);  
    // 定义一个用于发送的DatagramPacket对象  
    private DatagramPacket outPacket;  
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(new Date()+"UdpServer");

		try {
			receive();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void receive()throws IOException  
    {  
        try(  
            // 创建DatagramSocket对象  
            DatagramSocket socket = new DatagramSocket(PORT))  
        {  
            // 采用循环接收数据  
//        	Client client = new Client();
//            StringBuffer sbu = new StringBuffer();
//            sbu.append((char) 48);sbu.append((char) 49);sbu.append((char) 50);sbu.append((char) 51);
//            sbu.append((char) 52);sbu.append((char) 53);sbu.append((char) 54);sbu.append((char) 55);
//        	client.setResponse(sbu.toString());	
        	List<Client> list = SingleUdpList.getSingleUdpList().getList();
        	List<Command> commandList = SingleUdpList.getSingleUdpList().getCommandList();
//        	list.add(client);
        	int count = 0;
        	StringBuffer sbu = new StringBuffer();
        	sbu.append((char) 69);sbu.append((char) 82);sbu.append((char) 82);sbu.append((char) 79);
            sbu.append((char) 82);
            String error = sbu.toString();
            while(true)  
            {  
                try {
					// 读取Socket中的数据，读到的数据放入inPacket封装的数组里  
					socket.receive(inPacket);  
					// 判断inPacket.getData()和inBuff是否是同一个数组  
					String[] data = new String(inPacket.getData(), 0 , inPacket.getLength()).split(":");				//直接将命令转化为字符串出现问题
	                byte[] result = new byte[inPacket.getLength()];
	                System.arraycopy(inPacket.getData(), 0, result, 0, result.length);
					
//                client = list.get(0);				//暂时假设只有一个客户端
//                client.setIP(data[0]);			//data[0] :IP
//                client.setPort(Long.valueOf(data[1]));// data[1]: Port
//                client.setContent(data[2]);				//data[2]: Content
										//将收到的数据存进list中
					Client client = new Client(data[0], Long.valueOf(data[1]));	
					String contentHex = getContentHex(result);
					client.setContentHex(contentHex);
					client.setContent(getContent(contentHex));
					System.out.println(list.toString());
					
					
					// 初始化发送用的DatagramSocket，它包含一个长度为0的字节数组  
					outPacket = new DatagramPacket(new byte[0] , 0  , InetAddress.getByName(DEST_IP) , inPacket.getPort()); 
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
					outPacket.setData(buff);  
					// 发送数据报  
					socket.send(outPacket); 
					
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
