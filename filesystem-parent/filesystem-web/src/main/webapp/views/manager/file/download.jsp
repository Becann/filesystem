<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文件下载</title>
<link rel="stylesheet" href="<%=path %>/css/dropzone.css?v=1473248119" /> 
<link rel="stylesheet" href="<%=path %>/css/style.css?v=1473248119" />
<script src="<%=path%>/dwz-ria/js/jquery-1.7.2.js"></script>
<script src="<%=path %>/js/dropzone.js"></script>
<style type="text/css">
section{
	margin:150px;
}
a{
	float:right;
	margin:150px,10px,150px,20px;
}
</style>

</head>
   
  <body>  
  <section>
  <h1>下载文件(管理员审核通过后将在钉钉上跟您发送下载地址)    <a href="<%=path%>/ws/admin/logout">退出登录</a></h1>
    <div id="dropzone">
    <form id="file-upload"
    class="dropzone common" >  
    <input type="hidden" name="user" value="${admin.name}">
     <div class="dz-message ">
                        把文档拖到这里或者点击选择文件<br />
  </div>
    </form>  
    </div>  
</section>

<script type="text/javascript">
Dropzone.autoDiscover = false;
$(".dropzone").dropzone({
    url: "<%=path%>/ws/admin/download",
    maxFiles: 10,
    maxFilesize: 10000,
    addRemoveLinks:true,
    dictFallbackMessage:"The brower is not support",
    init: function() {
        this.on("success", function(file,response,e) {
        	/* var res=JSON.parse(response);
        	if (res.error) {
                $(file.previewTemplate).children('.dz-error-mark').css('opacity', '1')
              } */
            console.log("File " + file.name + "uploaded");
        });
        this.on("error",function(file,errorMessage){
      	   //alert("用户目录不存在，请通知管理员"); 
        });
        this.on("uploadprogress", function(file, progress, bytesSent) {
        		    // Display the progress
        		  }		
        );  
    }

});
</script>
</body>
</html>