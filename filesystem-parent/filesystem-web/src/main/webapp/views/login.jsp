<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文档服务器</title>
<link href="<%=path%>/dwz-ria/themes/css/login.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a href=""><img src="<%=path%>/img/admin/loginlogo.png" /></a>
			</h1>
			<div class="login_headerContent">
				<h2 class="login_title"><img src="<%=path%>/img/admin/logintitle.png" /></h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form action="<%=path%>/ws/admin/login" method="post">
					<div align="center"><font color="red">${message}</font></div>
					<p>
						<label>用户名：</label>
						<input type="text" name="username" size="20" class="login_input" />
					</p>
					<p>
						<label>密码：</label>
						<input type="password" name="password" size="20" class="login_input" />
					</p>
					<!-- <p>
					<label>登入平台：</label>
					<select name="platform" class="login_input">
						<option value="terminal">用户终端登入</option>
						<option value="remote">远程桌面登入</option>
					</select>	
					</p> -->						
					<div class="login_bar" text-align="center">
						<input class="sub" type="submit" value=" " />
					</div>
				</form>
			</div>
			<div class="login_banner"><img src="<%=path%>/img/admin/loginbanner.png" /></div>
			<div class="login_main" text-align="center">
				<div class="login_inner">
					<p>您可以使用 脚语 管理，随时添加，随地删除</p>
					<p>您还可以使用 脚语随时监控，快速处理各种业务。</p>
					<p>在 这里 您可以做一切你想做的事情…</p>
				</div>
			</div>
		</div>
		<div id="login_footer">
			Copyright &copy; <%=(new java.text.SimpleDateFormat("yyyy")).format(new Date())%> 成谷科技 All Rights Reserved.
		</div>
	</div>
</body>
</html>