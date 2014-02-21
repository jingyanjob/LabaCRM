<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../basic_js.js"></script>
<script type="text/javascript" src="basic_js.js"></script>
<script type="text/javascript">
	function register(){
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		var pwconfirm = document.getElementById("pwconfirm").value;
		var email = document.getElementById("email").value;
		if(password != pwconfirm){
			alert("请确保'密码'和'密码确认'的输入一直！");
			return;
		}
		var xml = "<root>";
		xml = xml + "<username>" + username + "</username>";
		xml = xml + "<password>" + password + "</password>";
		xml = xml + "<email>" + email + "</email>";
		xml = xml + "</root>" ;
		registerHttp(xml);
	}
	
</script>
<title></title>
</head>
<body>
<table width=100% cellpadding="0" cellspacing="0">
	<tr>
		<td align=left>
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td >
						用户名
					</td>
					<td >
						<input id="username" style=" width:120px">
					</td>
				</tr>
				<tr>
					<td >
						密码
					</td>
					<td >
						<input type="password" id="password" style="width:120px">
					</td>
				</tr>
				<tr>
					<td >
						密码确认
					</td>
					<td >
						<input type="password" id="pwconfirm" style="width:120px">
					</td>
				</tr>
				<tr>
					<td >
						email
					</td>
					<td >
						<input id="email" style="width:120px">
					</td>
				</tr>
				<tr>
					<td></td>
					<td ><input style="width:60px" type=button onclick="register();" value="注册" /></td>
				</tr>
			</table>
		</td>
	</tr>
	
</table>
</body>
</html>