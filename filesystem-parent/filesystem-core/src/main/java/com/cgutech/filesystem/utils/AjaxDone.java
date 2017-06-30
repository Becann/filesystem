package com.cgutech.filesystem.utils;

public class AjaxDone {
	private int statusCode = 200;
	private String message;
	private String navTabId;
	private String rel;
	private String callbackType;
	private String forwardUrl;

	public int getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNavTabId() {
		return this.navTabId;
	}

	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}

	public String getRel() {
		return this.rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getCallbackType() {
		return this.callbackType;
	}

	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}

	public String getForwardUrl() {
		return this.forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public void ajaxForwardSuccess(String message, String callbackType) {
		this.message = message;
		this.statusCode = 200;
		this.callbackType = callbackType;
	}

	public void ajaxForwardError(String message, String callbackType) {
		this.message = message;
		this.statusCode = 300;
		this.callbackType = callbackType;
	}
}