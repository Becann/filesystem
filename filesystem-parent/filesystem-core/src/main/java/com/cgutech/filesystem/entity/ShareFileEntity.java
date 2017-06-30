package com.cgutech.filesystem.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cgu_share_file")
public class ShareFileEntity {
	private int id;
	private String filename;
	private String shareman;
	private String besharedman;
	private String sharetime;
	private String filepath;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="file_name")
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	@Column(name="share_man")
	public String getShareman() {
		return shareman;
	}
	public void setShareman(String shareman) {
		this.shareman = shareman;
	}
	@Column(name="be_shared_man")
	public String getBesharedman() {
		return besharedman;
	}
	public void setBesharedman(String besharedman) {
		this.besharedman = besharedman;
	}
	
	@Column(name="share_time")
	public String getSharetime() {
		return sharetime;
	}
	public void setSharetime(String sharetime) {
		this.sharetime = sharetime;
	}
	@Column(name="file_path")
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
}	

