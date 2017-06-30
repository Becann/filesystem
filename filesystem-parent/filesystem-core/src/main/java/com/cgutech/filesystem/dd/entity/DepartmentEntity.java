package com.cgutech.filesystem.dd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dd_department_info")
public class DepartmentEntity {
	//private int id;
	private long deid;
	private String name;
	private int parentid;
	
	/*@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}*/
	@Id
	@Column(name="department_id")
	public long getDeid() {
		return deid;
	}
	public void setDeid(long l) {
		this.deid = l;
	}
	@Column(name="department_name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="parent_id")
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

}
