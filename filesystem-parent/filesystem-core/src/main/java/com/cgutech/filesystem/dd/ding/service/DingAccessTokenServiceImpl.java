package com.cgutech.filesystem.dd.ding.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cgutech.filesystem.dd.ding.bean.DingAccessToken;
import com.cgutech.filesystem.dd.ding.constant.DingConstant;
import com.cgutech.filesystem.dd.ding.util.DingUtils;
import com.cgutech.filesystem.exception.BizException;



@Service("dingAccessTokenServiceImpl")
public class DingAccessTokenServiceImpl implements DingAccessTokenService{
	private static final Logger logger = Logger.getLogger(DingAccessTokenServiceImpl.class);
	
	@Override
	public String getAccessToken() {
		DingAccessToken dingAccessToken = this.requestAccessToken();
		return dingAccessToken.getAccess_token();
	}

	@Override
	public DingAccessToken requestAccessToken() {
		logger.info("开始请求access token");
		String corpid = DingConstant.CORPID;
		String corpsecret = DingConstant.CORPSECRET;
		DingAccessToken accessToken = DingUtils.getAccessToken(corpid, corpsecret);
		
		if (accessToken != null) {
			return accessToken;
		} else {
			logger.warn("获取Access Token返回结果异常");
			throw new BizException("获取Access Token返回结果异常");
		}
		
		
	}

}
