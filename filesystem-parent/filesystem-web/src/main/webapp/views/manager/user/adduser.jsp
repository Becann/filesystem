<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<h2 class="contentTitle">添加版块</h2>
<div class="pageContent" >
	<form  method="post" action="<%=path%>/ws/user/adduser" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="108"> 
			<dl>
				<dt>用户名：</dt>
				<dd>
					<input type="text" name="name" maxlength="80" class="required "  minlength="1" />
				</dd>
			</dl>
			<dl>
				<dt>角色：</dt>
				<dd>
					<select class="combox required" name="role">
						<option value="admin">admin</option>
						<option value="user">user</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>状态：</dt>
				<dd>
					<select class="combox required" name="status">
						<option value="ACTIVE">ACTIVE</option>
						<option value="INACTIVE">INACTIVE</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>钉钉推送接收管理员：</dt>
				<dd>
					<select class="combox required" name="ddadmin">
					<c:forEach items="${admins}" var="admin">
						<option value="${admin.name}">${admin.name}</option>
					</c:forEach>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>密码：</dt>
				<dd>
					初始密码为用户名
				</dd>
			</dl>
	</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>

