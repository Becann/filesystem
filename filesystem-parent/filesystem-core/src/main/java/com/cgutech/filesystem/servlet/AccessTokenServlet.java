package com.cgutech.filesystem.servlet;


import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import com.cgutech.filesystem.dd.utils.AccessTokenUtil;

/**
 * 定时2小时获取一次AccessToken
 * @author Becan
 *
 */
public class AccessTokenServlet extends GenericServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String accessToken=null;
	@Override 
	public void init() throws ServletException {
		// 安排指定的任务task在指定延迟delay后开始进行重复的固定速率period执行．
		  // Timer.scheduleAtFixedRate(TimerTask task,long delay,long period)	 
		    Timer timer = new Timer();
		    timer.scheduleAtFixedRate(new TimerTask() {
		      public void run() {
		    System.out.println("定时获取accesstoken");
		       accessToken=AccessTokenUtil.getAccessToken();
		       System.out.println(accessToken);
		      }
		    }, 1000*60*60,1000*60*60*2);// 1小时后每隔2小时执行一次
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	public static String getAccessToken(){
		if(accessToken==null){
			accessToken=AccessTokenUtil.getAccessToken();
		}
		return accessToken;
	}
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

