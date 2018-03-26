package com.panhong.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.panhong.dao.TokenInfoDao;
import com.panhong.model.TokenInfo;
import com.panhong.service.TokenInfoService;

@Service("tokenInfoService")
public class TokenInfoServiceImpl implements TokenInfoService {
	
	@Resource
	private TokenInfoDao tokenInfoDao;

	public TokenInfoDao getTokenInfoDao() {
		return tokenInfoDao;
	}

	public void setTokenInfoDao(TokenInfoDao tokenInfoDao) {
		this.tokenInfoDao = tokenInfoDao;
	}

	@Override
	public void addToken(TokenInfo tokenInfo) {
		tokenInfoDao.addToken(tokenInfo);
	}

	@Override
	public void deleteToken(TokenInfo tokenInfo) {
		tokenInfoDao.deleteToken(tokenInfo);
	}

	@Override
	public void updateToken(TokenInfo tokenInfo) {
		tokenInfoDao.updateToken(tokenInfo);
	}

	@Override
	public TokenInfo getLatestToken() {
		return tokenInfoDao.getLatestToken();
	}

	@Override
	public TokenInfo getTokenBytoken(String token) {
		return tokenInfoDao.getTokenBytoken(token);
	}

	@Override
	public List<TokenInfo> getAllToken() {
		return tokenInfoDao.getAllToken();
	}

}
