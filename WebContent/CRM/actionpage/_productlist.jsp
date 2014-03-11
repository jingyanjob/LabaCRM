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
	border-bottom:1px groove #eeeeee;
	border-right:1px groove #eeeeee;
	min-height: 98px;
	font-size:12px; 
	text-align: center;
	width:98%;
	margin-top:8px;
	background-color: white;
}
.productinfodiv0{
	width:85%;height:98;float:right;text-align:center;
}
.productinfodiv1{
	width:100%;height:20px;border-bottom:1px solid #dddddd;
}
a{
	text-decoration: none; border: none; color: black;
}
.productbottomdiv{
	width:100%;height:20px;border-top:1px solid #dddddd;text-align:right;vertical-align: bottom;
}
.prodlistbaseback{
width:98%;font-size:12px;
}
</style>
</head>
<body style=" background-color:#eeeeee;">
<br />
<div id="_pcatecheck" class=prodlistbaseback >
<input onclick="showProductByCate()" type=checkbox checked value="0">主厨推荐
<input onclick="showProductByCate()" type=checkbox checked value="1">热菜
<input onclick="showProductByCate()" type=checkbox checked value="2">时蔬
<input onclick="showProductByCate()" type=checkbox checked value="3">冷菜
<input onclick="showProductByCate()" type=checkbox checked value="4">汤/煲
<input onclick="showProductByCate()" type=checkbox checked value="5">主食
<input onclick="showProductByCate()" type=checkbox checked value="6">酒水/饮料
</div>
<div id="productlistdiv">
	<c:forEach items="${prods}" var="prod">
		<div lang="<c:out value="${prod.category}"></c:out>" class="productdiv"> <%--ui-widget-header  ui-state-default  --%>
			<div style="width:15%;height:100%;float:left;text-align:left;vertical-align: middle;">
				<img style="max-height:95px; margin-top:2px; width:90px;border: 0px solid ;"
				src="http://100train-img.stor.sinaapp.com/<c:out value="${prod.buid}"></c:out>/prod/<c:out value="${prod.imgurl}"></c:out>" />
			</div>
			<div class="productinfodiv0">
				<div class="productinfodiv1">
						<div style="width:50%;float:left; text-align:left">
							<c:out value="${prod.productname}"></c:out>
							&nbsp;&nbsp;&nbsp;<c:out value="${prod.catedesc}"></c:out>
						</div>
						<div style="width:50%;float:right;text-align:right;">
							<c:out value="${prod.price}" />元 - 折扣：<c:out value="${prod.incutoff}" />&nbsp;&nbsp;&nbsp;
						</div>
				</div>
				<div style="width:100%;height:58px;text-align:left;overflow-y:auto;">
					<c:out value="${prod.productdesc}"></c:out>
				</div>
				<div class="productbottomdiv">
					<%--
					<a href="javascript:publishProductToWb(${prod.id});">发到微博</a>&nbsp;&nbsp;&nbsp;
					--%>
					<a href="javascript:initProduct('${prod.id}');">修改</a> 
					&nbsp;&nbsp;
					<a href="javascript:deleteProduct(${prod.id});">删除</a> 
					&nbsp;&nbsp;
					<wb:publish  button_size="small"  default_text="${prod.productname}， ${prod.price}元， ${prod.productdesc}" 
						default_image="http://100train-img.stor.sinaapp.com/<c:out value="${prod.buid}"></c:out>/prod/<c:out value="${prod.imgurl}"></c:out>">
						发到微博
					</wb:publish>&nbsp;&nbsp;
				</div>
			</div>
		</div>
	</c:forEach>
</div>
<script type="text/javascript">
function showProductByCate(){
	var checks = getCheckBoxValue("_pcatecheck");
	var chk = checks.split(gspliter);
	var divs = document.getElementById("productlistdiv").childNodes;
	for(var i=0; i< divs.length; i++){
		if(divs.item(i).nodeName =="DIV"){
			//alert(divs.item(i).title +" --  "+ divs.item(i).nodeName +" --  "+ divs.item(i).attributes );
		//if(divs.item(i).cate){
			if(	   divs.item(i).lang === "0" 
				|| divs.item(i).lang === "1" 
				|| divs.item(i).lang === "2" 
				|| divs.item(i).lang === "3" 
				|| divs.item(i).lang === "4" 
				|| divs.item(i).lang === "5" 
				|| divs.item(i).lang === "6" ){
				divs.item(i).style.display = "none";
				for(var j=0; j < chk.length; j++){
					if(divs.item(i).lang === chk[j]){
						divs.item(i).style.display = "block";
					}
				}
			}
		}
	}
}

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