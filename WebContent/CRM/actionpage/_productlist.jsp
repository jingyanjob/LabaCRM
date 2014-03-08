<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style>
.productdiv { 
	border-bottom:2px groove #eeeeee;
	min-height: 98px; 
	font-size:12px; 
	text-align: center;
	width:98%;
	margin-top:8px;
}
。productinfodiv{
	width:100%;height:20%;border-bottom:1px solid #eeeeee;
}
a{
	text-decoration: none; border: none; color: black;
}

</style>

</head>
<body>
<br />
<div style="width:95%;" id="productlistdiv">
	<c:forEach items="${prods}" var="prod">
		<div class="ui-widget-header ui-state-default  productdiv">
			<div style="width:15%;height:100%;float:left;text-align:left;vertical-align: middle;">
				<img style="max-height:85px; width:85px;border: 0px solid ;"
				src="http://100train-img.stor.sinaapp.com/<c:out value="${prod.buid}"></c:out>/prod/<c:out value="${prod.imgurl}"></c:out>"/>
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
				<div style="width:100%;height:50%;border-top:1px solid #eeeeee;text-align:left;">
					&nbsp;&nbsp;&nbsp;<c:out value="${prod.productdesc}"></c:out>
				</div>
				<div style="width: 100%; height: 20%;border-top:1px solid #eeeeee;text-align:center;vertical-align: bottom;background-color:white;">
					<a href="javascript:publishProductToWb();">发到微博</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:deleteProduct();">删除</a> 
				</div>
			</div>
		</div>
		<%--
		<div id="productshow${prod.id}" style="width:600px;position:absolute; border: 1px solid #bbbbbb;background-color:white;display:none;" id="productshowdiv" >
			<div title="菜品一览" style="width:590px; margin: 5px 5px 5px; background-color: #fffff1;" >
				<table  height="100%">
					<tr>
						<td id="productshowimg" width="250px">
							<img style="width:250px" src='http://100train-img.stor.sinaapp.com/${prod.buid}/prod/${prod.imgurl}' />
						</td>
						<td width="350px">
							<table height="100%" width="100%"> 
								<tr>
									<td height="30px" align=center>
										<b>${prod.productname}</b>
									</td>
								</tr>
								<tr>
									<td align=left style="border-top: 1px solid #bbbbbb;">
										${prod.productdesc}
									</td>
								</tr>
								
								<tr>
									<td height="20px" id="productpublishstatus">
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>  
			</div>
		</div>
		--%>
	</c:forEach>
</div>
<script type="text/javascript">
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