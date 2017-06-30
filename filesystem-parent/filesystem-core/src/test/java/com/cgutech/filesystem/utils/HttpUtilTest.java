package com.cgutech.filesystem.utils;

import org.junit.Test;

import com.cgutech.filesystem.dd.dto.ResponseBean;
import com.cgutech.filesystem.dd.utils.AccessTokenUtil;
import com.google.gson.Gson;

public class HttpUtilTest {
	public static final String CORPID = "ding6f397efed850445f";
	public static final String CORPSECRET = "MUDYrZ8xxwEEyezwTEMmCoot6VO8qz9zn6L7_pGbS9Gaxk8G8_jnDFW-65L7fx0j";
	public static String url_token = "https://oapi.dingtalk.com/gettoken?corpid="+CORPID+"&corpsecret="+CORPSECRET;
	public static String  list="https://oapi.dingtalk.com/department/list?access_token=";
	public static void main(String[] args) {
		//获取ACCESS_TOKEN get
		 String res = HttpUtil.doGet(url_token);
		 System.out.println(res);
		 
		 Gson gson = new Gson();
		 ResponseBean accessToken=gson.fromJson(res, ResponseBean.class);
		 String ACCESS_TOKEN = accessToken.getAccess_token();
		 //获取部门列表 
		 String dlist= HttpUtil.doGet(list+ACCESS_TOKEN);
		 System.out.println(dlist);
		 ResponseBean listbean = gson.fromJson(dlist, ResponseBean.class);
		 System.out.println(listbean.getDepartment().size());
	}
	

	/**
	 * 
	 * @author becan@cgutech.com
	 * 2016年9月5日 下午2:34:47
	 * @return String
	 */
	@Test
	public void test(){
		System.out.println(AccessTokenUtil.getAccessToken());
	}
}
