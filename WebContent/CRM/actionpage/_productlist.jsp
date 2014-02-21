<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link type="text/css" href="css/start/jquery-ui-1.8.23.custom.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/masonry.js"></script>
</head>
<body>
<div style="width:580px;" id="productlistdiv">
	<c:forEach items="${prods}" var="prod">
		<div style="margin: 3px 3px 3px 0; background-color:#dddddd;padding: 1px; float: left; height: 130px; font-size:12px; text-align: center;">
			<a href="javascript:showProductDetail('${prod.id}', '${prod.productname}', '${prod.productdesc}', 'http://100train-img.stor.sinaapp.com/${prod.buid}/prod/${prod.imgurl}');">
			<img style="height:100px;border: 0px solid ;" src="http://100train-img.stor.sinaapp.com/<c:out value="${prod.buid}"></c:out>/prod/<c:out value="${prod.imgurl}"></c:out>"/>
			<br />
			<c:out value="${prod.productname}"></c:out> - 
			<c:out value="${prod.price}"/>元 - 折扣：<c:out value="${prod.incutoff}"/>
			</a>
		</div>
	</c:forEach>
</div>
<div style="width:600px;position:absolute; border: 1px solid #EEeeee;background-color:white;" id="productshowdiv" >
<div title="菜品一览" style="width:100%;position:absolute; margin: 5px 5px 5px; background-color: #EEeeee;" >
	<table  height="100%">
		<tr>
			<td id="productshowimg" width="250px">
				
			</td>
			<td width="350px">
				<table height="100%" width="100%"> 
					<tr>
						<td style="height:40px" align=left>
							<div id="productshowname" ></div>
						</td>
					</tr>
					<tr>
						<td align=left>
							<div id="productshowdesc"></div>
						</td>
					</tr>
					<tr>
						<td style="height:25px" align=center>
								<a href="javascript:publishProductToWb();">发到微博</a>
								&nbsp;&nbsp;&nbsp;
								<a href="javascript:publishProductToWb();">单品促销</a>
								&nbsp;&nbsp;&nbsp;
								<a href="javascript:deleteProduct();">删除</a>
								&nbsp;&nbsp;&nbsp;
								<a href="javascript:close__();">关闭</a>
						</td>
					</tr>
					<tr>
						<td>
							<div id="productpublishstatus"></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>  
</div>
</div>
<script type="text/javascript">
function close__(){
	 $("#productshowdiv").hide();
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