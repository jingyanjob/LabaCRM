<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:wb="http://open.weibo.com/wb">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script
	src=" http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=595918520"
	type="text/javascript" charset="utf-8"></script>
<style>
</style>
</head>
<body>
	<table cellpadding="1" cellspacing="1" width="99%"
		style="border: 1px solid #eeeeee;">
		<tr>
			<td align=left>名称 <input id="productname" value="${product.productname}" /> 分类 <select
				id="productcat" >
					<option value=0  <c:if test="${product.category==0}">selected</c:if> >主厨推荐</option>
					<option value=1  <c:if test="${product.category==1}">selected</c:if> >热菜</option>
					<option value=2  <c:if test="${product.category==2}">selected</c:if> >时蔬</option>
					<option value=3  <c:if test="${product.category==3}">selected</c:if> >冷菜</option>
					<option value=4  <c:if test="${product.category==4}">selected</c:if> >汤/煲</option>
					<option value=5  <c:if test="${product.category==5}">selected</c:if> >主食</option>
					<option value=6  <c:if test="${product.category==6}">selected</c:if> >酒水/饮料</option>
			</select> 价格 <input id="productprice" value="${product.price}" style="width: 50px" />元 &nbsp;&nbsp;折扣
				<input id="productdiscount" style="width: 50px" value="${product.incutoff}" />折
			</td>
		</tr>
		<tr align=left>
			<td>菜品介绍<br /> <textarea id=productdesc rows="10"
					style="width: 99%">${product.productdesc}</textarea>
			</td>
		</tr>
		<tr>
			<td>
				<c:if test="${product.id != -1}">
					<img style="max-height:95px; margin-top:2px; width:90px;border: 0px solid ;" 
						src="http://100train-img.stor.sinaapp.com/<c:out value="${product.buid}"></c:out>/prod/<c:out value="${product.imgurl}"></c:out>" />
				</c:if>
				
				<form id="productimgform" action="/crm.dc?action=fileUpload2"
					method="post" target="_new" enctype="multipart/form-data"
					align=center>
					配图<input type="file" id="productimg" name="productimg" /> <input name="imgbuid" type=hidden value="${product.buid}" />
						 <input name="subfolder" type=hidden
						value="prod" />
				</form>
			</td>
		</tr>
		<tr align=center>
			<td><br /> <input id="newproductbtn" onclick="newProduct('${product.id}')"
				type=button value=" 提 交 " /></td>
		</tr>
	</table>
</body>
</html>