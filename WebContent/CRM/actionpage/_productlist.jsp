<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:wb="http://open.weibo.com/wb">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script src=" http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=595918520" type="text/javascript" charset="utf-8"></script>
<style>
.productdiv { 
	border-bottom:2px groove #eeeeee;
	min-height: 98px; 
	font-size:12px; 
	text-align: center;
	width:98%;
	margin-top:8px;
}
.productinfodiv{
	width:100%;height:20%;border-bottom:1px solid #eeeeee;
}
a{
	text-decoration: none; border: none; color: black;
}
.productbottomdiv{
	width:100%;height:20%;border-top:1px solid #eeeeee;text-align:center;vertical-align: bottom;
}
</style>
</head>
<body>
<br />
<div style="width:98%;" id="productlistdiv">
	<c:forEach items="${prods}" var="prod">
		<div class="ui-widget-header ui-state-default  productdiv">
			<div style="width:15%;height:100%;float:left;text-align:left;vertical-align: middle;">
				<img style="max-height:85px; width:85px;border: 0px solid ;"
				src="http://100train-img.stor.sinaapp.com/<c:out value="${prod.buid}"></c:out>/prod/<c:out value="${prod.imgurl}"></c:out>" />
			</div>
			<div style="width:85%;height:100%;float:right;text-align:center;">
				<div class="productinfodiv">
					<div style="width:50%;float:left; text-align:left">
						&nbsp;&nbsp;&nbsp;<c:out value="${prod.productname}"></c:out>
					</div>
					<div style="width:50%;float:right;text-align:right;">
						<c:out value="${prod.price}"/>元 - 折扣：<c:out value="${prod.incutoff}"/>&nbsp;&nbsp;&nbsp;
					</div>
				</div>
				<div style="width:100%;height:60%;text-align:left;">
					<c:out value="${prod.productdesc}"></c:out>
				</div>
				<div class="productbottomdiv">
					<%--
					<a href="javascript:publishProductToWb(${prod.id});">发到微博</a>&nbsp;&nbsp;&nbsp;
					--%>
					<wb:publish  button_size="small"  default_text="${prod.productname}， ${prod.price}元， ${prod.productdesc}" 
						default_image="http://100train-img.stor.sinaapp.com/<c:out value="${prod.buid}"></c:out>/prod/<c:out value="${prod.imgurl}"></c:out>">
						发到微博
					</wb:publish>
					&nbsp;&nbsp;&nbsp;
					<a href="javascript:deleteProduct(${prod.id});">删除</a> 
				</div>
			</div>
		</div>
	</c:forEach>
</div>
<script type="text/javascript">
/*
id : 绑定的标签的ID，必填。
button_type : 按钮样式red/gray，默认为red
button_size : big/middle/small，默认为middle
button_text : 自定义button文字，默认为“发布到微博”
default_text : 文本框内容，默认为空
default_image : 预置图片地址，默认为空
toolbar : 工具栏[表情, 图片, 话题]，默认为'face,image,topic'，可增删工具栏模块和更换按钮位置
*/
function publishToWb(id, button_text){
	W.widget.publish({
        'id' : 'publish'
    });
}

function closePl__(id){
	document.getElementById(id).style.display = "none";
	// $(id).hide();
}
$(document).ready(function() {
	$(function(){
	 // $('#productlistdiv').masonry({});
	  
	  $("#productshowdiv").hide();
	  
	});
});
</script>
</body>
</html>