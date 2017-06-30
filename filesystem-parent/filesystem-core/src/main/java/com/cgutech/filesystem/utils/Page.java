package com.cgutech.filesystem.utils;

import java.util.ArrayList;
import java.util.List;

public class Page {
	private int beginpage;
	private int endpage;
	private int pagenum;
	private int userpage;
	private int totalpage;
	private int totalnum;
	private String postaddress;
	List<?> list = new ArrayList<Object>();

	public Page(int userpage, int length, int pagenum) {
		this.pagenum = pagenum;
		this.userpage = userpage;
		if (length % this.pagenum == 0)
			this.totalpage = (length / this.pagenum);
		else {
			this.totalpage = (length / this.pagenum + 1);
		}

		if (this.totalpage < 10) {
			setBeginpage(1);
			setEndpage(this.totalpage);
		} else if (userpage < 5) {
			setBeginpage(1);
			setEndpage(10);
		} else if (userpage > this.totalpage - 4) {
			setBeginpage(this.totalpage - 9);
			setEndpage(this.totalpage);
		} else {
			setBeginpage(userpage - 4);
			setEndpage(userpage + 5);
		}
	}

	public int getBeginpage() {
		return this.beginpage;
	}

	public void setBeginpage(int beginpage) {
		this.beginpage = beginpage;
	}

	public int getEndpage() {
		return this.endpage;
	}

	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}

	public int getPagenum() {
		return this.pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public int getUserpage() {
		return this.userpage;
	}

	public void setUserpage(int userpage) {
		this.userpage = userpage;
	}

	public int getTotalpage() {
		return this.totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getTotalnum() {
		return this.totalnum;
	}

	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}

	public List<?> getList() {
		return this.list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public String getPostaddress() {
		return this.postaddress;
	}

	public void setPostaddress(String postaddress) {
		this.postaddress = postaddress;
	}
}