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
<div style="100%" id="vipuselistdiv">
	<c:forEach items="${vips}" var="vip">
		<div style="border:1px solid #cccccc;margin: 3px 3px 3px 0; background-color:#eeeeee;padding: 1px; float: left; height: 100px; font-size:12px; text-align: center;">
			<img src="${vip.profileImageUrl}" style="max-height:70px;" id="vip${vip.id}" />
			<br />
			<div style="border:1px solid #cccccc;background-color:white;">
				<a href="http://www.weibo.com/u/${vip.uid}" target="_new"><c:out value="${vip.username}"></c:out></a>
				<br />
				<c:out value="${vip.location}"></c:out>
			</div>
		</div>
	</c:forEach>
</div>
<input type=hidden id="lastitem" value="vip${lastitem.id}" />
<script type="text/javascript">
//$(document).ready(function() {
	var id = document.getElementById("lastitem").value;
	$(function(){
		//alert(id);
		document.getElementById(id).onload = function(){
		 // 	$('#vipuselistdiv').masonry({
		 // 	});
		}
	 });
// });

</script>
</body>
</html>