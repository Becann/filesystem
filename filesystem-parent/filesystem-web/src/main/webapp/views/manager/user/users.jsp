<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<form id="pagerForm" method="post" action="<%=path%>/ws/user/list">
	<input type="hidden" name="pageNum" value="1" /> 
	<input type="hidden" name="numPerPage" value="${userlist.pagenum}" />
	<input type="hidden" name="user" value="${user }"> 
	<input type="hidden" name="role" value="${role }"> 
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="<%=path%>/ws/user/list" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>搜索： <input type="text" name="user" alt="请输入用户名" />
					</td>
					<td>
					<select class="combox" name="role">
					<option value="" >所有</option>
					<option value="admin">管理员</option>
					<option value="user">普通用户</option>
				    </select>
					</td>
					<td>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">


			<li><a class="add" href="<%=path%>/ws/user/uadd"
				target="dialog" title="新增用户" width="800" height="450"><span>新增用户</span>
			</a></li>
			<li><a class="edit" href="<%=path%>/ws/user/change?user={user_name}"
				target="dialog" title="修改用户信息" width="800" height="450"><span>修改用户信息</span>
			</a></li>
			<li><a class="delete" href="<%=path%>/ws/user/delete?user={user_name}"
				target="ajaxTodo" title="删除用户" width="800" height="450"><span>删除用户</span>
			</a></li>

		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<!-- <th width="5%" align="center"><input type="checkbox"
					group="ids" class="checkboxCtrl"></th> -->
				<th>用户名</th>
				<th>用户状态</th>
				<th>用户角色</th>
				<th>钉钉推送管理员</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userlist.list}" var="user">
				<tr target="user_name" rel="${user.name}" align="left" valign="top">
					<%-- <td><input name="ids" type="checkbox" value="${user.id}"> 
					</td>--%>
					<td >${user.name}</td>
					<td >${user.status}</td>
					<td >${user.role}</td>
					<td>${user.dd_admin}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option ${userlist.pagenum eq '10' ? 'selected="selected"
					' : ''}
					value="10">10</option>
				<option ${userlist.pagenum eq '20' ? 'selected="selected"
					' : ''}
					value="20">20</option>
				<option ${userlist.pagenum eq '50' ? 'selected="selected"
					' : ''}
					value="50">50</option>
				<option ${userlist.pagenum eq '100' ? 'selected="selected"
					' : ''}
					value="100">100</option>
				<option ${userlist.pagenum eq '200' ? 'selected="selected"
					' : ''}
					value="200">200</option>
			</select> <span>条，共${userlist.totalnum}条</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${userlist.totalnum}" numPerPage="${userlist.pagenum}"
			pageNumShown="20" currentPage="${userlist.userpage}"></div>
	</div>
</div>


