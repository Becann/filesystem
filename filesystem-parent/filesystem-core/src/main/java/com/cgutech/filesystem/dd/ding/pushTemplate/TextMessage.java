package com.cgutech.filesystem.dd.ding.pushTemplate;


import com.cgutech.filesystem.dd.ding.bean.DingPushMessage;

public class TextMessage {
	
	DingPushMessage dingPushMessage = new DingPushMessage();

	public DingPushMessage getDingPushMessageBean(String text,String userId) {
		   dingPushMessage.getText().setContent(text);
		   dingPushMessage.setAgentid("14438348");
		   dingPushMessage.setToparty("");
		   dingPushMessage.setTouser(userId);
		   dingPushMessage.setMsgtype("text");   
		   return dingPushMessage;
	}

	public void setDingPushMessageBean(DingPushMessage dingPushMessageBean) {
		this.dingPushMessage = dingPushMessageBean;
	}
	
	
}
