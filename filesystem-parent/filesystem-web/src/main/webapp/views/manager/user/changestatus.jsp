<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<h2 class="contentTitle">添加版块</h2>
<div class="pageContent" >
	<form  method="post" action="<%=path%>/ws/user/changestatus" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="108"> 
		<dl>
				<dd>
					<input type="hidden" name="id" maxlength="80" class="required "  minlength="1"  value="${id}"/>
				</dd>
			</dl>
			<dl>
				<dt>用户名：</dt>
				<dd>
					<input type="text" name="user" maxlength="80" class="required "  minlength="1"  value="${user}"/>
				</dd>
			</dl>
			<dl>
				<dt>角色：</dt>
				<dd>
					<select class="combox required" name="role">
						<option value="user" ${"user" eq role?"selected":"" }>user</option>
						<option value="admin" ${"admin" eq role?"selected":"" }>admin</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>状态：</dt>
				<dd>
					<select class="combox required" name="status">
						<option value="ACTIVE" ${"ACTIVE" eq status?"selected":"" }>ACTIVE</option>
						<option value="INACTIVE" ${"INACTIVE" eq status?"selected":"" }>INACTIVE</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>钉钉推送管理员：</dt>
				<dd>
					<select class="combox required" name="ddadmin">
					<c:forEach items="${admins}" var="admin">
						<option value="${admin.name}" ${admin.name eq dd_admin?"selected":""}>${admin.name}</option>
					</c:forEach>
					</select>
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

