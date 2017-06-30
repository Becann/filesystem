package com.cgutech.filesystem.dd.dto;

import java.io.Serializable;

public class DepartmentBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private long id;  //	部门id
	private String name;  //部门名称
	private int parentid;  //父部门id，根部门为1
	private boolean createDeptGroup;  //是否同步创建一个关联此部门的企业群, true表示是, false表示不是
	private boolean autoAddUser;  //当群已经创建后，是否有新人加入部门会自动加入该群, true表示是, false表示不是
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public boolean isCreateDeptGroup() {
		return createDeptGroup;
	}
	public void setCreateDeptGroup(boolean createDeptGroup) {
		this.createDeptGroup = createDeptGroup;
	}
	public boolean isAutoAddUser() {
		return autoAddUser;
	}
	public void setAutoAddUser(boolean autoAddUser) {
		this.autoAddUser = autoAddUser;
	}
	
	
}
