<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<h2 class="contentTitle">密码修改</h2>
<div class="pageContent" >
	<form method="post" action="<%=path%>/ws/admin/change"  class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="108"> 
			<div class="unit">
				<label>账号：</label>
				<input type="text" name="adminname" class="required"  minlength="1" maxlength="20" value="${admin.name}" />
			</div>
			<div class="unit">
				<label>原密码：</label>
				<input type="password" name="oldpassword" class="required"  alt="请输入原密码" minlength="1" maxlength="20"/>
			</div>
			<div class="unit">
				<label>新密码：</label>
				<input type="password" id="newpassword" name="newpassword" class="required" alt="请输入新密码" minlength="1" maxlength="20"/>
			</div>
			<div class="unit">
				<label>再次输入新密码：</label>
				<input type="password"  class="required" equalTo="#newpassword" maxlength="20"  maxlength="20" alt="请重复输入密码"/>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>

