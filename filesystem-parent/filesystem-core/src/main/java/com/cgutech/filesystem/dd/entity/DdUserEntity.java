package com.cgutech.filesystem.dd.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name="dd_user_info")
public class DdUserEntity {
	private int id;
	private String userid;
	private String uname;
	private String ddId;
	private String department;
	
	
	@Column(name="userid")
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Column(name="uname")
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	@Column(name="dingId")
	public String getDdId() {
		return ddId;
	}
	public void setDdId(String ddId) {
		this.ddId = ddId;
	}
	@Column(name="department")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj==this){
			return true;
		}
		if(obj!=null&&obj.getClass()==DdUserEntity.class){
			DdUserEntity user = (DdUserEntity)obj;
			if(user.getUserid().equals(this.getUserid())){
				return true;
			}
		}
		return false;
	}
	
}

