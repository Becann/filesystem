<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文件系统管理后台</title>
<link href="<%=path%>/dwz-ria/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=path%>/dwz-ria/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=path%>/dwz-ria/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="<%=path%>/dwz-ria/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>

<script src="<%=path%>/dwz-ria/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/jquery.validate.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<script type="text/javascript" src="<%=path%>/dwz-ria/chart/raphael.js"></script>
<script type="text/javascript" src="<%=path%>/dwz-ria/chart/g.raphael.js"></script>
<script type="text/javascript" src="<%=path%>/dwz-ria/chart/g.bar.js"></script>
<script type="text/javascript" src="<%=path%>/dwz-ria/chart/g.line.js"></script>
<script type="text/javascript" src="<%=path%>/dwz-ria/chart/g.pie.js"></script>
<script type="text/javascript" src="<%=path%>/dwz-ria/chart/g.dot.js"></script>

<script src="<%=path%>/dwz-ria/js/dwz.core.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.util.date.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.drag.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.tree.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.accordion.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.ui.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.theme.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.navTab.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.tab.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.resize.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.dialog.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.dialog.ext.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.stable.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.ajax.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.pagination.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.database.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.effects.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.panel.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.history.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.combox.js" type="text/javascript"></script>
<script src="<%=path%>/dwz-ria/js/dwz.print.js" type="text/javascript"></script>
<!--
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="<%=path%>/dwz-ria/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="<%=path%>/js/dropzone.js"></script>
<script type="text/javascript">
$(function(){
	DWZ.init("<%=path%>/dwz-ria/dwz.frag.xml", {
		//loginUrl:"../login_dialog.jsp", loginTitle:"登录",	// 弹出登录对话框
		loginUrl:"<%=path%>/views/login.jsp",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"<%=path%>/dwz-ria/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

</script>

</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="">标志</a>
				<ul class="nav">
					<li><font color="#FFFFFF">您好,${admin.name}</font></li>
					<li><a href="<%=path%>/ws/admin/logout"><font color="#FFFFFF">退出</font></a></li>
					<li><a href="<%=path %>/views/manager/adminchangepwd.jsp" target="dialog" width="600">修改账号密码</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li id="zhong"></li>
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li><!--
					<li theme="red"><div>红色</div></li>
					--><li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					
					<div class="accordionHeader">
						<h2><span>Folder</span>管理</h2>
					</div>
					<div class="accordionContent">
					<c:if test="${admin.role eq 'admin'}">
						<ul class="tree treeFolder">
							<li><a>用户管理</a>
								<ul>
									<li><a href="<%=path%>/ws/user/list" target="navTab" rel="cguUser">用户账户</a></li>
								</ul>
							</li>
							<li><a>文件管理</a>
								<ul>
									<li><a href="<%=path%>/ws/file/list" target="navTab" rel="cguFile">上传文件信息</a></li>
									<li><a href="<%=path%>/ws/file/audit" target="navTab" rel="cguAudit">下载文件审核</a></li>
									<li><a href="<%=path%>/ws/file/sharelist" target="navTab" rel="shareFile">分享文件信息</a></li>
								</ul>
							</li>
							<li><a>上传下载</a>
								<ul>
									<c:if test="${platform eq 'terminal' }">							
										<li><a href="<%=path%>/ws/admin/adminupload?username=${admin.name}" target="navTab" rel="upload">上传文件</a></li>
									</c:if>	
									<c:if test="${platform eq 'remote' }">
										<li><a href="<%=path%>/ws/admin/admindownload?username=${admin.name}" target="navTab" rel="download">下载或分享文件</a></li>							
									</c:if>
								</ul>
							</li>
						</ul>
						</c:if>
						<c:if test="${admin.role eq 'user'}">
							<ul class="tree treeFolder">
								<li><a href="<%=path%>/ws/file/list?user=${admin.name}" target="navTab" rel="cguUserFile">上传文件信息</a></li>															
								<li><a href="<%=path%>/ws/admin/admindownload?username=${admin.name}" target="navTab" rel="download">下载或分享文件</a></li>
								<li><a href="<%=path%>/ws/file/sharelist?besharedman=${admin.name}" target="navTab" rel="shareFile">分享文件信息</a></li>														
							</ul>
						</c:if>
					</div>	
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo" style="text-align: center;">
							<p><span><h4>欢迎使用CGU文件系统后台</h4></span></p>
							<p>官方微博:<a href="http://weibo.com" target="_blank">http://weibo.com</a></p>
						</div>
						<div class="unit" align="center" style="padding-top: 50px">
							<!-- <img src="manage.png" width="1223px" height="630px"/> -->
						</div>
						<!-- <div style="width:230px;position: absolute;top:60px;right:0" layoutH="80">
						<iframe width="100%" height="550" class="share_self"  frameborder="0" scrolling="no" src="http://widget.weibo.com/weiboshow/index.php?language=&width=0&height=550&fansRow=1&ptype=1&speed=0&skin=9&isTitle=1&noborder=1&isWeibo=1&isFans=1&uid=1974769027&verifier=b3036f78&dpc=1"></iframe>	
						</div>  -->
					</div>
					
				</div>
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; <%=(new java.text.SimpleDateFormat("yyyy")).format(new Date())%> <a href="" target="dialog">成谷科技</a> </div>
	
</body>
<script type="text/javascript">
			window.onload=showTime();
  		function time(){
   var now=new Date() 
   document.getElementById("zhong").innerHTML = "<font color='#FFFFFF'>"+now.getFullYear()
    +"年"+(now.getMonth()+1)+"月"+now.getDate()
    +"日"+now.getHours()+"时"+now.getMinutes()
    +"分"+now.getSeconds()+"秒</font>";
  }
  function showTime() {
    setInterval(time, 1000);  //每秒刷新一次
  }
</script>
</html>