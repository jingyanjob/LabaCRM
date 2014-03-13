<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:wb="http://open.weibo.com/wb">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=595918520" type="text/javascript" charset="utf-8"></script>
<style>
.maindiv{
	margin-top:80px;
	width:990px;
}
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
/* select panel*/
.selecteddiv{
	position:fixed;
	bottom:1px;
	background-color:#aaaaaa;
	border: 1px solid #333333;
	width:990px;
	height:80px;
	background:#000;
	color:#F00; 
	filter:alpha(Opacity=60);
	-moz-opacity:0.6;
	opacity: 0.6;;
}
.selectedshowdiv{
	margin-top:3px;
	margin-left:3px;
	width:80px;
	display: none;
	background-color: white;
	float: left;
	filter:alpha(Opacity=100);
	-moz-opacity:1;
	opacity: 1;
}
.prodlistbaseback{
width:98%;font-size:12px;
height:20px;
}
</style>
</head>
<body style=" background-color:#eeeeee;">

<div id="productlistdiv" class=maindiv>
<br />
欢迎浏览 <c:out value="${bu.username}"></c:out> 的菜单，在这儿您可以直接选菜并预定；
<br/>
	<div id="_pcatecheck" class=prodlistbaseback >
		<input onclick="showProductByCate()" id="cate01" value="主厨推荐"/>
		<input onclick="showProductByCate()" id="cate02" value="热菜"/>
		<input onclick="showProductByCate()" id="cate03" value="时蔬"/>
		<input onclick="showProductByCate()" id="cate04" value="冷菜"/>
		<input onclick="showProductByCate()" id="cate05" value="汤/煲" />
		<input onclick="showProductByCate()" id="cate06" value="主食">
		<input onclick="showProductByCate()" id="cate07" value="酒水/饮料">
	</div>
	<br />
	<c:forEach items="${prods}" var="prod">
		<a href="javascript:selectThis('${prod.id}','${prod.price}','${prod.productname}');" 
			<c:if test="${prod.category != '0'}" >style="display:none;"</c:if>
		>
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
					<div style="width:100%;height:78px;text-align:left;overflow-y:auto;">
						<c:out value="${prod.productdesc}"></c:out>
					</div>
				</div>
			</div>
		</a>
	</c:forEach>
	<br/>
</div>
<div class="selecteddiv">
	<div id="selectedshow" style="widht:100%">
		<c:forEach items="${prods}" var="prod1">.
			<a href='javascript:unSelectThis("${prod1.id}", "${prod1.price}","${prod1.productname}")'>
				<div id="${prod1.id}" class=selectedshowdiv >
					${prod1.productname}
				</div>
			</a>
		</c:forEach>
	</div>
	<br/>
	<div style="width:100%;text-align:left;font-size:18px; height:25px;color:white;" >
		<div id="totoalprice" style="width:100px;float:left">
			已选菜价：0 元
		</div>
		<div  style="width:150px;float:right;margin-right:100px;border:2px solid white;">
			<a id="submitreserve" href="javascript:reserved();"><font color=white>亲，选好了,点这儿可直接预定</font> </a>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function() {
    $("#cate01").button();
    $("#cate02").button();	
    $("#cate03").button();	
    $("#cate04").button();	
    $("#cate05").button();	
    $("#cate06").button();	
    $("#cate07").button();					
});
var totalPrice =0;
var menuselected = "";
function selectThis(id,price,pname){
	 var p = parseInt(price);
	 if( document.getElementById(id).style.display === "block" ){
	 	return;
	 }
	 document.getElementById(id).style.display = "block" ;
	 totalPrice = totalPrice + p;
	 document.getElementById("totoalprice").innerHTML = "已选菜价：" + totalPrice + " 元";
	 menuselected = menuselected + " " + pname;
}
function unSelectThis(id, price, pname){
 	var p = parseInt(price);
	document.getElementById(id).style.display = "none" ;
	totalPrice = totalPrice - p;
	document.getElementById("totoalprice").innerHTML = "已选菜价：" + totalPrice;
	menuselected = menuselected.replace(pname, "");
}
function reserved(){
	alert(menuselected);
}

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


</script>
</body>
</html>