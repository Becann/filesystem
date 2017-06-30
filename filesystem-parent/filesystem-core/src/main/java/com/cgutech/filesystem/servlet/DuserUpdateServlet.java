package com.cgutech.filesystem.servlet;


import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.cgutech.filesystem.dd.service.DUserService;
import com.cgutech.filesystem.dd.service.impl.DUserServiceImpl;

/**
 * 每周更新一次钉钉用户信息
 * @author Becan
 *
 */
public class DuserUpdateServlet extends GenericServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override 
	public void init() throws ServletException {
		// 安排指定的任务task在指定的时间firstTime开始进行重复的固定速率period执行．
		  // Timer.scheduleAtFixedRate(TimerTask task,Date firstTime,long period)
		    Calendar calendar = Calendar.getInstance();
		    calendar.set(Calendar.HOUR_OF_DAY, 23); // 控制时
		    calendar.set(Calendar.MINUTE, 0);    // 控制分
		    calendar.set(Calendar.SECOND, 0);    // 控制秒
		 
		    Date time = calendar.getTime();     // 得出执行任务的时间,此处为今天的23：00：00
		 
		    Timer timer = new Timer();
		    timer.scheduleAtFixedRate(new TimerTask() {
		      public void run() {
		        DUserService dUserService = new DUserServiceImpl();
		        //定时更新钉钉用户信息到数据库
		        dUserService.saveDdUserInfo();
		      }
		    }, time, 1000*60*60*24*7);// 这里设定将延时每周固定执行
		  
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

