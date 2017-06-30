package com.cgutech.filesystem.dd.ding.bean;

public class DingAccessToken {
	private String errode;
	private String errmsg;
	private String access_token;
	@Override
	public String toString() {
		return "DingAccessToken [errode=" + errode + ", errmsg=" + errmsg
				+ ", access_token=" + access_token + "]";
	}
	public String getErrode() {
		return errode;
	}
	public void setErrode(String errode) {
		this.errode = errode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
}
