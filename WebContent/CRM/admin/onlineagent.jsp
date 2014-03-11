<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" href="../jq104/css/ui-lightness/jquery-ui-1.10.4.custom.css" rel="stylesheet" />
<script src=" http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=595918520"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../jq104/js/jquery-ui-1.10.4.custom.min.js"></script>
<title>喇叭CRM</title>
<meta property="wb:webmaster" content="27304571f6f8edb0" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
.maindivtop { 
	width: 100%; 
	left:0px;
	top: 0px;
	height:60px;
	background-color:#eeeeee;
	position:fixed;
	left: 0;
	width: 100%;
	border-bottom: 3px white groove;
	z-index:99;
}
.maindiv1 {
	margin-top:5px;
}

.header-inner{ 
	height:6px;
	background: white url(img/inner-shadow.png) no-repeat 70% 0; 
}
.logininfo{
	width:990px;height:20px;margin-top:65px;color:white;text-align:center;left:0px;
	background-color:#888888;
}
.companyinfo{
	width:990px;bottom:0px;margin-top:515px;color:black;text-align:center;left:0px;
	border-top:1px solid #dddddd;
	background-color:#eeeeee;
}
</style>
</head>
<body style="font-size: 12px;background-color:#ffffff">
<center>
		<div class=maindivtop>
				<div style="width:990px;">
					<div style="width:50%;left:2px;float:left;text-align:left;">
					<img src="../img/logofullblank.png" style="margin-top:9px;height:45px"/>
					</div>
					<div style="height:50px;width:50%;float:right;text-align:right;">
						<br />
						<a id="helplink" href="help.jsp" target="_new" >使用帮助</a>
						<a id="onlineagentlink" href="onlineagent.jsp" target="_new" >在线客服</a>
						<a id="aboutuslink" href="aboutus.jsp" target="_new" >关于喇叭</a>
						<a id="contactuslink" href="contactus.jsp" target="_new" >联系我们</a>
					</div>
					<br/>
					
				</div>
		</div>
		<div class="logininfo">
		 您好，我是在线客户，请问有什么可以帮助您的；
		</div>
		<div class=maindiv1 >
		</div>
		<div class=companyinfo >
			<font size="2">佰传信息技术有限公司 出品  labacrm.com - 津ICP备13005372号 </font>
			<br>
			<img style="height:25px;" src="../SAELogo1.png"/>
		</div>
		
	<script type="text/javascript">


  $(function() {
    $("input[type=button]").button();
    $("#helplink").button();
    $("#onlineagentlink").button();
    $("#aboutuslink").button();
    $("#contactuslink").button();	
  });
  </script>
</center>
</body>
</html>