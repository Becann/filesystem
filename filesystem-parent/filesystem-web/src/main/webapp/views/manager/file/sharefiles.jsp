<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<form id="pagerForm" method="post" action="<%=path%>/ws/file/sharelist">
	<input type="hidden" name="pageNum" value="1" /> 
	<input type="hidden" name="numPerPage" value="${sharelist.pagenum}" />
	<input type="hidden" name="shareman" value="${shareman }"/>
	<input type="hidden" name="filename" value="${filename }"/>
	<input type="hidden" name="besharedman" value="${besharedman }"/>
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="<%=path%>/ws/file/sharelist" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>搜索： 
					<c:if test="${admin.role eq 'admin' }"><input type="text" name="besharedman" placeholder="被分享人" /></c:if>
					<input type="text" name="shareman" placeholder="分享人"/>
				    <input type="text" name="filename"  placeholder="被分享文件名称"/>
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
		<c:if test="${admin.role eq 'admin'}">
		<ul class="toolBar">
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="<%=path%>/ws/file/deleteshare" class="delete"><span>批量删除</span></a></li>		
		</ul>
		</c:if>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
			<c:if test="${admin.role eq 'admin'}">
			<th ><input type="checkbox" group="ids" class="checkboxCtrl"></th></c:if>
				<th>文件名</th>
				<th>分享用户</th>
				<th>被分享用户</th>
				<th>分享时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${sharelist.list}" var="file">
			<tr>
			<c:if test="${admin.role eq 'admin'}">
			<td><input name="ids" value="${file.id}" type="checkbox"></td></c:if>
					<td >${file.filename}</td>
					<td >${file.shareman}</td>
					<td >${file.besharedman}</td>
					<td >${file.sharetime}</td>
					<td ><a title="下载" 
					href="<%=path%>/ws/file/downloadFile?id=${file.id}&flag=share" 
					><img alt="下载" src="<%=path%>/img/down.png"></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option ${sharelist.pagenum eq '10' ? 'selected="selected"
					' : ''}
					value="10">10</option>
				<option ${sharelist.pagenum eq '20' ? 'selected="selected"
					' : ''}
					value="20">20</option>
				<option ${sharelist.pagenum eq '50' ? 'selected="selected"
					' : ''}
					value="50">50</option>
				<option ${sharelist.pagenum eq '100' ? 'selected="selected"
					' : ''}
					value="100">100</option>
				<option ${sharelist.pagenum eq '200' ? 'selected="selected"
					' : ''}
					value="200">200</option>
			</select> <span>条，共${sharelist.totalnum}条</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${sharelist.totalnum}" numPerPage="${sharelist.pagenum}"
			pageNumShown="20" currentPage="${sharelist.userpage}"></div>
	</div>
</div>


