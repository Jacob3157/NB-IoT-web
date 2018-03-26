package com.panhong.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.panhong.model.TicketInfo;
import com.panhong.model.TokenInfo;
import com.panhong.util.TokenUtil;

public class TokenThread implements Runnable {
	
	private static Logger log = LoggerFactory.getLogger(TokenThread.class);
    // 第三方用户唯一凭证
    public static String appid = "";
    // 第三方用户唯一凭证密钥
    public static String appsecret = "";
    public static TokenInfo accessToken = null;
    public static TicketInfo ticketInfo=null;

	@Override
	public void run() {
		while (true) {
            try {
                accessToken = TokenUtil.getToken(appid, appsecret);
                if (null != accessToken) {
                    //调用存储到数据库
					TokenUtil.saveToken(accessToken);
                    log.info("获取access_token成功，有效时长{}秒 token:{}", accessToken.getExpiresIn(), accessToken.getAccessToken());
                    //开始获取ticket
                    do{
                    	ticketInfo=TokenUtil.getTicket(accessToken.getAccessToken());
                    }while(ticketInfo==null);
                	TokenUtil.saveTicket(ticketInfo);
                	log.info("获取ticket成功，有效时长{}秒ticket:{}",ticketInfo.getExpires_in(),ticketInfo.getTicket());
                    // 休眠6000秒
                    Thread.sleep((accessToken.getExpiresIn() - 1200)*1000);
                } else {
                    // 如果access_token为null，60秒后再获取
                    Thread.sleep(60 * 1000);
                }
            } catch (InterruptedException e) {
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e1) {
                    log.error("{}", e1);
                }
                log.error("{}", e);
            }
        }
    }

}
