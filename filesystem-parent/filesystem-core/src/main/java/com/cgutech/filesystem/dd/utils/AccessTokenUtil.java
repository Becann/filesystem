package com.cgutech.filesystem.dd.utils;

import com.cgutech.filesystem.dd.dto.ResponseBean;
import com.cgutech.filesystem.utils.HttpUtil;
import com.cgutech.filesystem.utils.ReadProperties;
import com.cgutech.filesystem.utils.TeaUtil;
import com.google.gson.Gson;

/**
 *AccessTokenUtil.java
 *@author Becan E-mail:becan@cgutech.com
 *@date 2016年9月5日 下午3:07:42
 */
public class AccessTokenUtil {
	public static final String CORPID = TeaUtil.decrypt(ReadProperties.read("corpid"));
	public static final String CORPSECRET = TeaUtil.decrypt(ReadProperties.read("corpsecret"));
	public static String url_token = ReadProperties.read("ddapi_url")+"gettoken?corpid="+CORPID+"&corpsecret="+CORPSECRET;
	
	/**
	 * 
	 * @author Becan E-mail:becan@cgutech.com
	 * @date 2016年9月5日 下午3:08:31
	 * @parameter
	 * @retrun String
	 */
	public static String getAccessToken(){
		//获取ACCESS_TOKEN get
		 String res = HttpUtil.doGet(url_token);
		 //System.out.println(res);
		 if(res.equals("error")||!res.startsWith("{")){
			 return null;
		 }
		 Gson gson = new Gson();
		 ResponseBean responseBean=gson.fromJson(res, ResponseBean.class);
		 String ACCESS_TOKEN = responseBean.getAccess_token();
		 return ACCESS_TOKEN;
	}
	
}

