package com.cgutech.filesystem.dd.ding.bean;

public class UserId {
	private String userid;
	private String deviceId;
	private String is_sys;
	private String sys_level;
	
	
	
	@Override
	public String toString() {
		return "UserId [userid=" + userid + ", deviceId=" + deviceId
				+ ", is_sys=" + is_sys + ", sys_level=" + sys_level + "]";
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getIs_sys() {
		return is_sys;
	}
	public void setIs_sys(String is_sys) {
		this.is_sys = is_sys;
	}
	public String getSys_level() {
		return sys_level;
	}
	public void setSys_level(String sys_level) {
		this.sys_level = sys_level;
	}
	
	
}
