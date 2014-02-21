<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	var userXmlHttp ;
	function getXmlHttp() {
		try {
			var request = new XMLHttpRequest();
		}
		catch (e) {
			try {
				request = new ActiveXObject("Msxml2.XMLHTTP");
			}
			catch (othermicrosoft) {
				try {
					request = new ActiveXObject("Microsoft.XMLHTTP");
				}
				catch (failed) {
					request = false;
				}
			}
		}
		if (!request) {
			alert("Error initializing XMLHttpRequest!");
		} else {
			return request;
		}
	}
	function sendXmlReq(url, xml) {
		var xmlHttp = getXmlHttp();
		xmlHttp.open("POST", url, true);
		xmlHttp.setRequestHeader("Content-Type","text/html; charset=utf-8");
		xmlHttp.setRequestHeader("pageEncoding","utf-8");
		if(xml === ""){
			xmlHttp.send(null);
		}else{
			xmlHttp.send(xml);
		}
		return xmlHttp;
	}
	function logonHttp(xml){
		var url = baseurl+"/logon.dc?action=logon";
		userXmlHttp = sendXmlReq(url, xml);
		userXmlHttp.onreadystatechange = updatePagenLogon;
	}
	function updatePagenLogon(){
		try{
			if (userXmlHttp.readyState == 4) {
				if(userXmlHttp.responseText == "1"){
					document.getElementById("logonmsgtd").innerHTML = "<font color='red'>用户名或密码错误！</font>";
				}else{
				//	document.getElementById("logondiv").style.display = "none";
				//	document.getElementById("persondiv").style.display = "block";
				//	document.getElementById("persondiv").innerHTML = userXmlHttp.responseText;
					refreshWindow();
				}
			}
		}catch(e){
			alert(e);
		}
	}
	function registerHttp(xml){
		var url = baseurl+"/logon.dc?action=register";
		userXmlHttp = sendXmlReq(url, xml);
		userXmlHttp.onreadystatechange = updatePageRegister;
	}
	function register(){
		var username = document.getElementById("regusername").value;
		var password = document.getElementById("regpassword").value;
		var pwconfirm = document.getElementById("regpwconfirm").value;
		var email = document.getElementById("regemail").value;
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
		
	function updatePageRegister(){
		try{
			if (userXmlHttp.readyState == 4) {
				//document.getElementById("logondiv").style.display = "none";
			//	document.getElementById("persondiv").style.display = "block";
				refreshWindow()
			}
		}catch(e){
			alert(e);
		}
	}

	function logon(){
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		var xml = "<root>";
		xml = xml + "<username>" + username + "</username>";
		xml = xml + "<password>" + password + "</password>";
		xml = xml + "</root>" ;
		logonHttp(xml);
	}
	function refreshWindow(){
		window.location.reload();
	}
	function showLogonDiv(){
		document.getElementById("registerdiv1").style.display = "none";
		document.getElementById("logondiv1").style.display = "block";
	}
	function showRegisterDiv1(){
		document.getElementById("logondiv1").style.display = "none";
		document.getElementById("registerdiv1").style.display = "block";
	}
</script>
<title></title>
</head>
<body>
<table  cellspacing="0">
	<tr>
		<td width="100px" style="background-color: #CCF5FD;border-right: 2px white solid;">
			<a href="javascript:showLogonDiv();">登陆 </a>
		</td>
		<td width="100px" style="background-color: #C0F3AF">
			<a href="javascript:showRegisterDiv1();">注册</a>
		</td>
	</tr>
</table>
<div id="logondiv1">
<table width=600px cellpadding="0" cellspacing="0" style="background-color: #CCF5FD">
	<tr>
		<td width="100px">用户名</td>
		<td><input id="username" style="width: 120px"></td>
	</tr>
	<tr>
		<td width="100px">密&nbsp;&nbsp;&nbsp;&nbsp;码</td>
		<td><input id="password"  type="password" style="width: 120px"></td>
	</tr>
	<tr>
		<td width="100px"></td>
		<td><input style="width: 60px" type="button" onclick="logon();"
			value="登陆" /></td>
	</tr>
	<tr>
		<td width="100px"></td>
		<td id="logonmsgtd"></td>
	</tr>
</table>
</div>
<div id="registerdiv1" style="display: none;">
<table width=600px cellpadding="0" cellspacing="0" style="background-color: #C0F3AF">

				<tr>
					<td  width="100px">
						用户名
					</td>
					<td >
						<input id="regusername" style=" width:120px">
					</td>
				</tr>
				<tr>
					<td >
						密码
					</td>
					<td >
						<input type="password" id="regpassword" style="width:120px">
					</td>
				</tr>
				<tr>
					<td >
						密码确认
					</td>
					<td >
						<input type="password" id="regpwconfirm" style="width:120px">
					</td>
				</tr>
				<tr>
					<td >
						email
					</td>
					<td >
						<input id="regemail" style="width:120px">
					</td>
				</tr>
				<tr>
					<td></td>
					<td ><input style="width:60px" type=button onclick="register();" value="注册" /></td>
				</tr>
			</table>
</div>
</body>
</html>