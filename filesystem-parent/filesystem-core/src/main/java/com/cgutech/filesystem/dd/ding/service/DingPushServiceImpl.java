package com.cgutech.filesystem.dd.ding.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.http.util.TextUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cgutech.filesystem.dd.ding.bean.DingPushMessage;
import com.cgutech.filesystem.dd.ding.util.DingUtils;
import com.cgutech.filesystem.exception.BizException;
import com.cgutech.filesystem.servlet.AccessTokenServlet;
import com.cgutech.filesystem.utils.JsonUtil;


//@Service("dingPushServiceImpl")
public class DingPushServiceImpl implements DingPushService{

	private static final Logger logger = Logger
			.getLogger(DingPushServiceImpl.class);
	
	@Resource
	//DingAccessTokenService dingAccessTokenService = new DingAccessTokenServiceImpl();

	public void pushService(DingPushMessage message) {
		logger.info("dingPush");
		String accessToken = AccessTokenServlet.getAccessToken();
		String url = "https://oapi.dingtalk.com/message/send?access_token="
				+ accessToken;
		String messageJson = JsonUtil.toJson(message);
		String response = DingUtils.httpRequest(url, "POST", messageJson);
		if (!TextUtils.isEmpty(response)) {
			logger.info("收到回复：" + response+new Date().toString());
		
		} else {
			throw new BizException("推送消息异常");
		}

	}

}
