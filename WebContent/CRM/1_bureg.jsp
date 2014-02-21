<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
</head>
<body style="font-size: 12px;">
<div style="width: 60%;margin-left: 20%;margin-right: 20%;">
	<div>
		<input disabled="disabled" id="logonwb" value="logonwb" style="border: 0px; font: 12px black;"/>
	</div>
	<div>
		请尽量使用 企业V 微博注册，如过您的微博没有认证，请填写如下信息，多谢；
	</div>
	<div>
		<table>
			<tr>
				<td width="80px">联系人 </td>
				<td> <input id="contact" /></td>
			</tr>
			<tr>
				<td>电 话 </td>
				<td> <input id="phone"  /></td>
			</tr>
			<tr>
				<td></td>
				<td> <input id="regbtn" value="注 册" type="button" class="ui-accordion-header ui-helper-reset ui-state-default ui-state-active"/></td>
			</tr>
		</table>
	</div>
</div>
<div>
	<jsp:include page="0_introduce.jsp"></jsp:include>
</div>
</body>
</html>