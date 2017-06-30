package com.cgutech.filesystem.dd.ding.pushTemplate;

import com.cgutech.filesystem.dd.ding.bean.DingPushMessage;

public class LinkMessage {
	DingPushMessage dingPushMessage = new DingPushMessage();

	public DingPushMessage getDingPushMessageBean(String userId,String url,String title,String content) {
		   dingPushMessage.setAgentid("14438348");
		   dingPushMessage.setToparty("");
		   dingPushMessage.setTouser(userId);
		   dingPushMessage.setMsgtype("link");   
		   dingPushMessage.getLink().setMessageUrl(url);
		   dingPushMessage.getLink().setPicUrl("");
		   dingPushMessage.getLink().setTitle(title);
		   dingPushMessage.getLink().setText(content);
		   return dingPushMessage;
	}

	public void setDingPushMessageBean(DingPushMessage dingPushMessageBean) {
		this.dingPushMessage = dingPushMessageBean;
	}
}
