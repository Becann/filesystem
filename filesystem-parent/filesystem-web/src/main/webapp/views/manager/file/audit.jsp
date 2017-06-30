<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<style>
a.btnDel,a.btnSelect{
background:url(<%=path%>/dwz-ria/themes/default/images/button/imgX.gif) no-repeat; 
display:block; 
width:22px; 
height:20px; 
text-indent:-1000px; 
overflow:hidden; 
float:left; 
margin-right: 3px
}
a.btnDel{background-position: -23px 0}
a.btnSelect{background-position: -92px 0}
.center a.btnDel,.center a.btnSelect{display:inline-block;float:none}
.right a.btnDel,.right a.btnSelect{display:inline-block;float:none;text-indent:1000px}
</style>
<form id="pagerForm" method="post" action="<%=path%>/ws/file/audit">
	<input type="hidden" name="pageNum" value="1" /> 
	<input type="hidden" name="numPerPage" value="${filelist.pagenum}" />
	<input type="hidden" name="auditStatus" value="${auditStatus }">
	<input type="hidden" name="applyuser" value="${applyuser }">
	<input type="hidden" name="applyDate" value="${applyDate }">
	<input type="hidden" name="downDate" value="${downDate }">
	<input type="hidden" name="filename" value="${filename }">
	<input type="hidden" name="downStatus" value="${downStatus }">
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="<%=path%>/ws/file/audit" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>搜索： <input type="text" name="applyuser" alt="申请人" />
					</td>
					<td>
					<select class="combox" name="auditStatus">
					<option value="">所有</option>
					<option value="0">未审核</option>
					<option value="1">审核通过</option>
					<option value="2">审核未通过</option>
				    </select>
				</td>
					<td>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div>
					</td>
					<td>
					<a class="button" href="<%=path%>/ws/file/retrieval" target="dialog" mask="true" title="查询框"><span>高级检索</span></a>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
<div class="panelBar">
		<ul class="toolBar">
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="<%=path%>/ws/file/delete" class="delete"><span>批量删除</span></a></li>		
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
			    <th ><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th>文件名</th>
				<th>申请用户</th>
				<th>申请日期</th>
				<th>文件路径</th>
				<th>下载状态</th>
				<th>下载日期</th>
				<th>文件审核状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${filelist.list}" var="file">
			<tr target="file_id" rel="${file.id}">
			<td><input name="ids" value="${file.id}" type="checkbox"></td>
					<td >${file.fname}</td>
					<td >${file.auser}</td>
					<td >${file.atime}</td>
					<td >${file.path}</td>
					<td >${file.dstatus}</td>
					<td >${file.dtime}</td>
					<td >
					<c:if test="${file.auditstatus eq '0'}">
					未审核
					<a title="通过审核"  target="ajaxTodo"
					href="<%=path%>/ws/file/editAudit?status=1&id=${file.id}" 
					class="btnSelect">通过审核</a>
					<a title="不通过审核" target="ajaxTodo"
					href="<%=path%>/ws/file/editAudit?status=2&&id=${file.id}" 
					class="btnDel">不通过审核&nbsp;&nbsp;</a>
					</c:if>
					<c:if test="${file.auditstatus eq '1'}">
					审核通过
					</c:if>
					<c:if test="${file.auditstatus eq '2'}">
					审核不通过
					</c:if></td>
					<td><a title="删除" target="ajaxTodo" href="<%=path%>/ws/file/delete?id=${file.id}" class="btnDel">删除</a>
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


