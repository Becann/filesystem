package com.cgutech.filesystem.dd.ding.bean;

import org.springframework.stereotype.Service;

@Service
public class DingPushMessage {
	private String touser;
	private String toparty;
	private String agentid;
	private String msgtype;
	Text text = new Text();
	Link link = new Link();
	
	public class Text{
		private String content;

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
		
	}
	
	public class Link{
		private String messageUrl;
		private String picUrl;
		private String title;
		private String text;
		public String getMessageUrl() {
			return messageUrl;
		}
		public void setMessageUrl(String messageUrl) {
			this.messageUrl = messageUrl;
		}
		public String getPicUrl() {
			return picUrl;
		}
		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		
	}

	@Override
	public String toString() {
		return "DingPushMessage [touser=" + touser + ", toparty=" + toparty
				+ ", agentid=" + agentid + ", text=" + text + "]";
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getToparty() {
		return toparty;
	}

	public void setToparty(String toparty) {
		this.toparty = toparty;
	}

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}
	
	
}
