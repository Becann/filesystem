<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!--jquery-ui  -->
 <link href="<%=path%>/jquery-ui/jquery-ui.min.css" rel="stylesheet" type="text/css" /> 
<script src="<%=path%>/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
<%-- <script type="text/javascript" src="<%=path%>/js/input-prompt.js"></script> --%>
<style>

    .ui-autocomplete {
    text-align:left;
    position: absolute;
    /* top: 100%;
    left: 0; */
    z-index: 1000;
    float: left;
    display: none;
    min-width: 160px;   
    /* padding: 4px 0;
    margin: 0 0 10px 25px; */
    list-style: none;
    background-color: #ffffff;
    border-color: #ccc;
    border-color: rgba(0, 0, 0, 0.2);
    border-style: solid;
    border-width: 1px;
    /*  -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    border-radius: 0px;
  -webkit-box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
    -moz-box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
     box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
     -webkit-background-clip: padding-box;
    -moz-background-clip: padding;
    background-clip: padding-box; */
    *border-right-width: 2px;
    *border-bottom-width: 2px;
}

.ui-menu-item > a.ui-corner-all {
    display: block;
    padding: 0px 0px;
    clear: both;
    font-weight: normal;
    line-height: 18px;
    color: #555555;
    white-space: nowrap;
    text-decoration: none;
}

.ui-state-hover, .ui-state-active {
	/* font:italic bold 12px/30px Georgia,serif; */
	font:15px arial,sans-serif;
	left:0;
    color: #ffffff;
    text-decoration: none;
    background-color: #ccc !important;
    border-radius: 0px;
    border-color: #ccc !important;
    -webkit-border-radius: 0px;
    -moz-border-radius: 0px;
    background-image: none;
}
</style>
<div class="pageContent">
	<form method="post" action="<%=path%>/ws/file/share" class="pageForm" onsubmit="return validateCallback(this,dialogAjaxDone);">
	<input type="hidden" name="id" value="${fid}">
	<input type="hidden" name="shareman" value="${admin.name}">
		<div class="pageFormContent" layoutH="50">
			<div class="unit">
				<label>被分享人：</label>
				<input type="text" size="25" name="beshareman" class="required" 
				placeholder="请输入被分享人英文名称" id="autocomplete"/>
				<!-- <span class="inputInfo">被分享人英文名称</span> -->
			</div>	
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" id="subButton">分享</button></div></div></li>
			</ul>
		</div>
	</form>
</div>

<script type="text/javascript">
$.pdialog.resizeDialog({style: {height: 150}}, $.pdialog.getCurrent(), "");
var murl="<%=path%>/ws/user/auto";
var tags=[];
$(document).ready(function(){
	$.ajax({
		url:murl,
		async:false,
		dataType:"json",
		success:function(result){
		tags = result.names.split(",");
	}});
	 /* $.get(url,function(data,status){
		 tags = data.names.split(",");
	 }); */
	
});
console.log(tags);      
$("#autocomplete").autocomplete({
		delay:1,
	  source: function( request, response ) {
	          var matcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( request.term ), "i" );
	          response( $.grep( tags, function( item ){
	              return matcher.test( $.trim(item) );
	          }) );
	      },
	     focus: function(event,ui){
	    	/*  console.log($(ui).css("background-color"));
	    	 $(ui).css("background-color","#D0D0D0");
	    	 $(ui.item).css("background-color","#D0D0D0"); */
		}
});
</script>
