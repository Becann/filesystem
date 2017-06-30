<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="pageContent">
	<form method="post" action="<%=path%>/ws/file/audit" class="pageForm" onsubmit="return navTabSearch(this);">
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>审核状态：</label>
				<label class="radioButton"><input name="auditStatus" type="radio" value="0" />待审核</label>
				<label class="radioButton"><input name="auditStatus" type="radio" value="1"/>审核通过</label>
				<label class="radioButton"><input name="auditStatus" type="radio" value="2"/>审核未通过</label>	
			</div>
			<div class="divider">divider</div>
			<div class="unit">
				<label>是否已经被下载：</label>
				<label class="radioButton"><input name="downStatus" type="radio" value="no" />未下载</label>
				<label class="radioButton"><input name="downStatus" type="radio" value="yes"/>已经下载</label>
			</div>
			<div class="divider">divider</div>
			<div class="unit">
				<label>文件名：</label>
				<input type="text" size="25" name="filename"/>
				<span class="inputInfo">模糊文件名</span>
			</div>
			<div class="unit">
				<label>申请人：</label>
				<input type="text" size="25" name="applyuser" class="lettersonly"/>
				<span class="inputInfo">申请下载文件用户英文名称</span>
			</div>
			<div class="unit">
				<label>申请日期：</label>
				<input type="text" size="25" name="applyDate" class="date"/>
				<span class="inputInfo">申请下载日期，大于等于</span>
			</div>
			<div class="unit">
				<label>下载日期：</label>
				<input type="text" size="25" name="downDate" class="date"/>
				<span class="inputInfo">文件下载日期，大于等于</span>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">开始检索</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="reset">清空重输</button></div></div></li>
			</ul>
		</div>
	</form>
</div>


