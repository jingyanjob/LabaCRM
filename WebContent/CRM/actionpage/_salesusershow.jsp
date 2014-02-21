<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta property="wb:webmaster" content="27304571f6f8edb0" />
<script src=" http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=595918520"
	type="text/javascript" charset="utf-8"></script>
<title></title>
</head>
<body>
	<div id="salestagtnames" style="width:510px">
	 	<c:out value="${names}" escapeXml="false"></c:out>
	</div>
	<input type=button onclick="checkAll('salestagtnames');" value="全选"/> 
	<input type=button onclick="unCheckAll('salestagtnames')" value="全不选"/> 
	<input type=hidden id="salestagtids" value="${ids}"/>
</body>
</html>