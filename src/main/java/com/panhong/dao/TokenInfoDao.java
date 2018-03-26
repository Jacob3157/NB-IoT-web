package com.panhong.dao;

import java.util.List;

import com.panhong.model.TokenInfo;

public interface TokenInfoDao {
	
	//增加token信息
	public void addToken(TokenInfo tokenInfo);
	
//	删除token
	public void deleteToken(TokenInfo tokenInfo);
	
//	更新token
	public void updateToken(TokenInfo tokenInfo);
	
//	获取最新token
	public TokenInfo getLatestToken();
	
	//通过token获取tokenInfo
	public TokenInfo getTokenBytoken(String token);
	
	//获取所有token
	public List<TokenInfo> getAllToken();
}
