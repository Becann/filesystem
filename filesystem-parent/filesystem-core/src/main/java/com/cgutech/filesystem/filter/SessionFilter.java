package com.cgutech.filesystem.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.cgutech.filesystem.utils.AjaxDone;

public class SessionFilter implements Filter {
	private static final Logger logger = Logger.getLogger(SessionFilter.class);

	private static final Pattern loginUriPattern = Pattern.compile(".*/ws/admin/login");

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		HttpSession session = req.getSession();
		String ipAddr = req.getRemoteAddr();
		if(uri.contains("ws/file/downloadFile")){
			chain.doFilter(request, response);
		}else{
		if ((session == null || session.getAttribute("admin") == null)
				&& needSession(uri)) {
			logger.warn("会话已过期：IP:" + ipAddr + ", URI:" + uri);
			AjaxDone ajaxDone = new AjaxDone();
			ajaxDone.setStatusCode(301);
			ajaxDone.setMessage("会话超时，请重新登录");
			req.setAttribute("ajaxDone", ajaxDone);
			logger.info(req.getContextPath() + "/views/ajaxDone.jsp");
			req.getRequestDispatcher("/views/ajaxDone.jsp").forward(req, res);
			//res.sendRedirect(req.getContextPath() + "/views/ajaxDone.jsp");
		} else {
			chain.doFilter(request, response);
			}
		}
	}

	private boolean needSession(String uri) {
		if (!loginUriPattern.matcher(uri).matches()) {
			return true;
		}
		return false;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

}
