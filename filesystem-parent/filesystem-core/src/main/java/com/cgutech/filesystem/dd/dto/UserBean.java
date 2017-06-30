package com.cgutech.filesystem.dd.dto;

import java.io.Serializable;

public class UserBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userid; //员工唯一标识ID（不可修改）
	private String name;  //成员名称
	private String dingId;//钉钉ID
	private String mobile;//手机号
	private String tel;//分机号
	private String workPlace;//办公地点
	private String remark;//备注
	private String position;//职位信息
	private String email;//员工的邮箱
	private String avatar;//头像url
	private String jobnumber;//员工工号
	private String orgEmail;//员工的企业邮箱

	
	private boolean isAdmin;//是否是企业的管理员, true表示是, false表示不是
	private boolean isBoss;//是否为企业的老板, true表示是, false表示不是
	private boolean isHide;//是否隐藏号码, true表示是, false表示不是
	private boolean isLeader;//是否是部门的主管, true表示是, false表示不是
	private boolean active;//表示该用户是否激活了钉钉
	
	private long order;//表示人员在此部门中的排序，列表是按order的倒序排列输出的，即从大到小排列输出的
	private long[] department;//成员所属部门id列表
	private ExtattrBean extattr;//扩展属性，可以设置多种属性(但手机上最多只能显示10个扩展属性，具体显示哪些属性，请到OA管理后台->设置->通讯录信息设置和OA管理后台->设置->手机端显示信息设置)
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDingId() {
		return dingId;
	}
	public void setDingId(String dingId) {
		this.dingId = dingId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getWorkPlace() {
		return workPlace;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getJobnumber() {
		return jobnumber;
	}
	public void setJobnumber(String jobnumber) {
		this.jobnumber = jobnumber;
	}
	public boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public boolean getIsBoss() {
		return isBoss;
	}
	public void setIsBoss(boolean isBoss) {
		this.isBoss = isBoss;
	}
	public boolean getIsHide() {
		return isHide;
	}
	public void setIsHide(boolean isHide) {
		this.isHide = isHide;
	}
	public boolean getIsLeader() {
		return isLeader;
	}
	public void setIsLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public long getOrder() {
		return order;
	}
	public void setOrder(long order) {
		this.order = order;
	}
	public long[] getDepartment() {
		return department;
	}
	public void setDepartment(long[] department) {
		this.department = department;
	}
	public ExtattrBean getExtattr() {
		return extattr;
	}
	public void setExtattr(ExtattrBean extattr) {
		this.extattr = extattr;
	}
	public String getOrgEmail() {
		return orgEmail;
	}
	public void setOrgEmail(String orgEmail) {
		this.orgEmail = orgEmail;
	} 

	
}
