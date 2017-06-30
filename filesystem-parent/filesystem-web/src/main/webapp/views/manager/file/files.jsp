<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<style>
img {
display:block; 
width:20px; 
height:20px; 
text-indent:-1000px; 
overflow:hidden; 
float:left; 
margin-right: 3px;
margin-left: 3px
}
</style>


<form id="pagerForm" method="post" action="<%=path%>/ws/file/list">
	<input type="hidden" name="pageNum" value="1" /> 
	<input type="hidden" name="numPerPage" value="${filelist.pagenum}" />
	<input type="hidden" name="user" value ="${user }" />
	<input type="hidden" name="filename" value="${filename }"/>
	<input type="hidden" name="before" value="${before }"/>
	<input type="hidden" name="after" value="${after }" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="<%=path%>/ws/file/list" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>搜索： 
					<c:if test="${admin.role eq 'admin' }"><input type="text" name="user" placeholder="文件创建者" /></c:if>
					<input type="text" name="filename"  placeholder="文件名模糊字"/>
					<input type="text" name="before" class="date" readonly="true" placeholder="起始日期"/>
				    <input type="text" name="after" class="date" readonly="true" placeholder="截止日期"/>
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
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="<%=path%>/ws/file/deleteupload" class="delete"><span>批量删除</span></a></li>		
		</ul>
		</c:if>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
			<c:if test="${admin.role eq 'admin'}">
			<th ><input type="checkbox" group="ids" class="checkboxCtrl"></th></c:if>
				<th>文件名</th>
				<th>上传用户</th>
				<th>上传路径</th>
				<th>上传时间</th>
				<th width="60px">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${filelist.list}" var="file">
			<tr>
			<c:if test="${admin.role eq 'admin'}">
			<td><input name="ids" value="${file.id}" type="checkbox"></td></c:if>
					<td >${file.f_name}</td>
					<td >${file.f_user}</td>
					<td >${file.f_path}</td>
					<td >${file.ctime}</td>
					<td width="60px">
					<a title="下载" 
					href="<%=path%>/ws/file/downloadFile?id=${file.id }&flag=upd" 
					><img height="16px" alt="下载" src="<%=path%>/img/down.png"></a>
					<a title="分享" target="dialog"
					href="<%=path%>/ws/file/tshare?id=${file.id}" 
					><img alt="分享" src="<%=path%>/img/share.png"></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option ${filelist.pagenum eq '10' ? 'selected="selected"
					' : ''}
					value="10">10</option>
				<option ${filelist.pagenum eq '20' ? 'selected="selected"
					' : ''}
					value="20">20</option>
				<option ${filelist.pagenum eq '50' ? 'selected="selected"
					' : ''}
					value="50">50</option>
				<option ${filelist.pagenum eq '100' ? 'selected="selected"
					' : ''}
					value="100">100</option>
				<option ${filelist.pagenum eq '200' ? 'selected="selected"
					' : ''}
					value="200">200</option>
			</select> <span>条，共${filelist.totalnum}条</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${filelist.totalnum}" numPerPage="${filelist.pagenum}"
			pageNumShown="20" currentPage="${filelist.userpage}"></div>
	</div>
</div>
<script >
</script>


