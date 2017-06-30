package com.cgutech.filesystem.dd.ding.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cgutech.filesystem.dd.ding.bean.UserId;
import com.cgutech.filesystem.dd.ding.util.DingUtils;
import com.cgutech.filesystem.exception.BizException;



@Service("getUserIdServiceImpl")
public class GetUserIdServiceImpl implements GetUserIdService{
	private static final Logger logger = Logger.getLogger(GetUserIdServiceImpl.class);
	
	@Override
	public String getUserId(String access_token,String code) {
		UserId userId = this.requestUserId(access_token,code);
		return userId.getUserid();
	}

	@Override
	public UserId requestUserId(String access_token,String code) {
		UserId userId = DingUtils.getUserId(access_token, code);
		
		if (userId != null) {
			return userId;
		} else {
			logger.warn("获取userId返回结果异常");
			throw new BizException("获取userId返回结果异常");
		}
	}

}
