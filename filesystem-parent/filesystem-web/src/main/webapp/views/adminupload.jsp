<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link rel="stylesheet" href="<%=path%>/css/dropzone.css?v=1473248119" /> 
<!-- <link rel="stylesheet" href="../css/style.css?v=1473248119" /> -->


<style>
section{
	margin:100px;
}
.common{
border-style:dashed;
border-width:3px;
border-color:#33CCFF;
}
</style>
<div class="pageContent" > 
<section> 
    <div id="dropzone" >
    <form id="file-upload" 
    class="dropzone common" >  
    <input type="hidden" name="user" value="${username}">
     <div class="dz-message">
          <h1 style="font-family:arial;color:#33CCFF;font-size:20px;">上传文档到远程桌面</h1><br />
  </div>
    </form>  
    </div>  
</section> 
<script type="text/javascript">
Dropzone.autoDiscover = false;
Dropzone.options.myAwesomeDropzone = false;
$(".dropzone").dropzone({
    url: "<%=path%>/ws/admin/upload",
    maxFiles: 10,
    maxFilesize: 10000,
    addRemoveLinks:true,
    dictFallbackMessage:"The brower is not support",
    init: function() {
        this.on("success", function(file) {
            console.log("File " + file.name + "uploaded");
        });
        this.on("error",function(file,errorMessage){
       	  // alert(errorMessage); 
         });
        this.on("uploadprogress", function(file, progress, bytesSent) {
        		    // Display the progress
        		  }		
        );
    }
});

</script> 
</div>