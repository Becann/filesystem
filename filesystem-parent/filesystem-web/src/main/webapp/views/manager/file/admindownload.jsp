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
.myspan{
	float:left;
	margin:8px 5px 8px 0px;
}
.mymessage{
	color:red;
	margin-left:40px;
}
.common{
border-style:dashed;
border-width:3px;
border-color:#33CCFF;
}
</style>
<div class="pageContent" >
  
<section>
<div style="width:100%;height:30px">
	<span style="float:left;font-size:14px" class="myspan">请先选择文件用途:</span>
	<span style="margin-top:0" class="myspan">
	<label ><input type="radio" name="shareOrdown" value="down" style="margin-top:8" >用于下载</label> 	
	<label ><input type="radio" name="shareOrdown" value="share" style="margin-top:8">用于分享</label>
	</span>
	<span class="mymessage myspan"></span>
</div>
    <div id="dropzone" >
    <form id="file-upload" 
    class="dropzone common" >  
    <input type="hidden" name="user" value="${username}">
    <input type="hidden" name="shareOrdown" value="" id="shareOrdown">
     <div class="dz-message">
        <h1 style="font-family:arial;color:#33CCFF;font-size:20px;">把文档拖到这里或者点击选择文件</h1><br />
  </div>
    </form>  
    </div>  
</section> 

<script type="text/javascript">
$(document).ready(function(){
	$('input:radio').click(function(){
		var radiovalue=$('input:radio:checked').val();
		if(radiovalue=='share'){
			$(".mymessage").text("已选中分享，下面上传的文件将用于分享，上传成功后前往 （上传文件信息）菜单分享给指定用户");	
		}
		if(radiovalue=='down'){
			$(".mymessage").text("已选中下载，下面上传的文件将用于下载");	
		}
		
	});
});
Dropzone.autoDiscover = false;
Dropzone.options.myAwesomeDropzone = false;
$(".dropzone").dropzone({
    url: "<%=path%>/ws/admin/download",
    maxFiles: 10,
    maxFilesize: 10000,
    addRemoveLinks:true,
    dictFallbackMessage:"The brower is not support",
    init: function() {
    	this.on("sending", function(file,xhr,formData) {
    		var valuu=$('input:radio:checked').val();
    		if(valuu==undefined){
    			alert("请先选择分享还是下载文件！");
    		}else{
    			console.log("radio值："+valuu);
    			$('#shareOrdown').val(valuu);	
    		};
        });
        this.on("success", function(file) {
            console.log("File " + file.name + "uploaded");
        });
        this.on("error",function(file,errorMessage){
       	   //alert(errorMessage); 
         });
        this.on("uploadprogress", function(file, progress, bytesSent) {
        		    // Display the progress
        		  }		
        );
    }
});
</script> 
</div>