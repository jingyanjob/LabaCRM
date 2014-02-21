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
<center>
<div style="width:900px;min-height:600pxs;background-color:#eeeeee;">
	<c:forEach items="${prods}" var="prod">
		<li style="margin: 3px 3px 3px 0; background-color:#dddddd;padding: 1px; float: left; width: 120px; height: 120px; font-size:12px; text-align: center;">
			<a href="javascript:alert(${prod.id});"><c:out value="${prod.productname}"></c:out></a>
			<br />
			<c:out value="${prod.productdesc}"></c:out>
		</li>
	</c:forEach>
</div>
</center>
<script type="text/javascript">
/*
	$(document).ready(function() {
		$("#productsortable").sortable();
	});
*/
</script>
</body>
</html>