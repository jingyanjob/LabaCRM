<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<div style="width:690px;" id="productlistdiv">
	<c:forEach items="${prods}" var="prod">
		<div style="border:1px solid #bbbbbb;margin: 3px 3px 3px; background-color:#dddddd;padding: 1px; float: left; height: 130px; font-size:12px; text-align: center;">
			<table height=100% width=180px align=center>
				<tr>
					<td>
						<a style="text-transform:none;text-decoration:none;" href="javascript:showProductDetail('${prod.id}', '${prod.productname}', '${prod.productdesc}', 'http://100train-img.stor.sinaapp.com/${prod.buid}/prod/${prod.imgurl}');">
							<img style="height:100px;border: 0px solid ;" src="http://100train-img.stor.sinaapp.com/<c:out value="${prod.buid}"></c:out>/prod/<c:out value="${prod.imgurl}"></c:out>"/>
						</a>
					</td>
				</tr>
				<tr>
					<td style="min-height:25px;background-color:white;border-top:1px solid #bbbbbb;">
						<p>
							<a style="text-transform:none;text-decoration:none;" href="javascript:showProductDetail('${prod.id}', '${prod.productname}', '${prod.productdesc}', 'http://100train-img.stor.sinaapp.com/${prod.buid}/prod/${prod.imgurl}');">
								<c:out value="${prod.productname}"></c:out> &nbsp;&nbsp;
								<c:out value="${prod.price}"/>元 - 折扣：<c:out value="${prod.incutoff}"/>
							</a>
						</p>
					</td>
				</tr>
			</table>
		</div>
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
									<td height="25px" align=center style="background-color:#eeeeee;">
											<a href="javascript:publishProductToWb();">发到微博</a>
											<%--&nbsp;&nbsp;&nbsp;
											<a href="javascript:publishProductToWb();">单品促销</a> --%>
											&nbsp;&nbsp;&nbsp;
											<a href="javascript:deleteProduct();">删除</a>
											&nbsp;&nbsp;&nbsp;
											<a href="javascript:closePl__('productshow${prod.id}');">关闭</a>
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