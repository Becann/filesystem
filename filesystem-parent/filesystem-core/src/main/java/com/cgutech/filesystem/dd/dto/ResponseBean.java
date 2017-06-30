package com.cgutech.filesystem.dd.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Becan
 *
 */
public class ResponseBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//公共属性
	private String errcode; //错误码
	private String errmsg; //错误信息
	
	
	
	//获取到的凭证
	private String access_token; 
	
	//部门数据
	private List<DepartmentBean> department; //部门列表数据。以部门的order字段从小到大排列
	
	//用户数据
	private boolean hasMore;  //在分页查询时返回，代表是否还有下一页更多数据
	private List<UserBean> userlist;  //成员列表
	
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
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
	public List<DepartmentBean> getDepartment() {
		return department;
	}
	public void setDepartment(List<DepartmentBean> department) {
		this.department = department;
	}
	public boolean isHasMore() {
		return hasMore;
	}
	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}
	public List<UserBean> getUserlist() {
		return userlist;
	}
	public void setUserlist(List<UserBean> userlist) {
		this.userlist = userlist;
	}
}
