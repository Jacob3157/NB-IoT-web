package com.panhong.thread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.ExpiringSessionRecycler;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;

import com.panhong.model.Client;
import com.panhong.model.Command;
import com.panhong.util.SingleUdpList;
import com.panhong.util.UDPServerHandler;


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
			receiveByMina();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void receiveByMina() throws IOException {
		 // ** Acceptor设置
        NioDatagramAcceptor acceptor = new NioDatagramAcceptor();
        // 此行代码能让你的程序整体性能提升10倍
        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
        chain.addLast("threadPool",new ExecutorFilter(Executors.newCachedThreadPool()));
        chain.addLast("logger", new LoggingFilter());
        // 设置MINA2的IoHandler实现类
        acceptor.setHandler(new UDPServerHandler());
        // 设置会话超时时间（单位：毫秒），不设置则默认是10秒，请按需设置
        acceptor.setSessionRecycler(new ExpiringSessionRecycler(15 * 1000));

        // ** UDP通信配置 设置是否重用地址？也就是每个发过来的udp信息都是一个地址
        DatagramSessionConfig dcfg = acceptor.getSessionConfig();
        dcfg.setReuseAddress(true);
        // 设置输入缓冲区的大小，压力测试表明：调整到2048后性能反而降低
        dcfg.setReceiveBufferSize(1024);
        // 设置输出缓冲区的大小，压力测试表明：调整到2048后性能反而降低
        dcfg.setSendBufferSize(1024);

        // ** UDP服务端开始侦听
        acceptor.bind(new InetSocketAddress(PORT));

        System.out.println("UDPserver start in 30000 ..");
	}

	public void receive()throws IOException  
    {  
        try(  
            // 创建DatagramSocket对象  
            DatagramSocket socket = new DatagramSocket(PORT))  
        {  
            // 采用循环接收数据  
        	List<Client> list = SingleUdpList.getSingleUdpList().getList();
        	List<Command> commandList = SingleUdpList.getSingleUdpList().getCommandList();
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
					
					//将收到的数据存进list中
					Client client = new Client(data[0], Long.valueOf(data[1]));	
					String contentHex = getContentHex(result);
					client.setContentHex(contentHex);
					client.setContent(getContent(contentHex));
					System.out.println(list.toString());
					System.out.println(inPacket.getSocketAddress());
					System.out.println(inPacket.getAddress());
					System.out.println(inPacket.getPort());
					
					// 初始化发送用的DatagramSocket，它包含一个长度为0的字节数组  
					outPacket = new DatagramPacket(new byte[0] , 0  , inPacket.getAddress() , inPacket.getPort()); 
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
