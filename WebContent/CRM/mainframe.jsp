<%@page import="com.CRM.systemtasks.BUInitTask"%>
<%@page import="com.CRM.data.BizUser"%>
<jsp:directive.page import="weibo4j.model.User"/>
<jsp:directive.page import="weibo4j.Users"/>
<%@page import="com.CRM.systemtasks.WeiboTask"%>
<%@page import="com.CRM.VIPUserMgr" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="weibo4j.*"%>
<%@ page language="java" import="weibo4j.http.*"%>
<%@ page language="java" import="weibo4j.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>喇叭CRM</title>
<meta property="wb:webmaster" content="27304571f6f8edb0" />
<meta property="wb:webmaster" content="5866d1c86d7d44f0" />
<script src=" http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=595918520"
	type="text/javascript" charset="utf-8"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" href="css/index.css" rel="stylesheet" />
<link type="text/css" href="jq104/css/ui-lightness/jquery-ui-1.10.4.custom.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="jq104/js/jquery-ui-1.10.4.custom.min.js"></script>
<style>
.maindivtop { 
	width: 100%; 
	left:0px;
	top: 0px;
	height:60px;
	background-color: #eeeeee;/*#F4F5F3;*/
	position:fixed;
	left: 0;
	width: 100%;
	border-bottom: 2px #F4F5F3 groove;
	z-index:99;
}
.introductdiv{
	height:200px;width:960px;
}
.introductsubdivleft{
	height:100%;width:480px;float:left;
	vertical-align:middle;
}
.introductsubdivright{
	height:100%;width:480px;float:right;
	vertical-align:middle;
}
</style>
</head>
<body style="font-size: 14px; margin-top: 20px; background-color: #ffffff">
<center>
	<div class=maindivtop>
				<div style="width:990px;">
					<div style="width:50%;left:2px;float:left;text-align:left;">
					
					<img src="img/logofullblank.png" style="margin-top:9px;height:45px"/>
					</div>
					<div style="height:50px;width:50%;float:right;text-align:right;">
						<br />
						<a id="helplink" href="http://labacrm.com/CRM/admin/help.jsp" target="_new" >使用帮助</a>
						<a id="onlineagentlink" href="http://labacrm.com/CRM/admin/onlineagent.jsp" target="_new" >在线客服</a>
						<a id="aboutuslink" href="http://labacrm.com/CRM/admin/aboutus.jsp" target="_new" >关于喇叭</a>
						<a id="contactuslink" href="http://labacrm.com/CRM/admin/contactus.jsp" target="_new" >联系我们</a>
					</div>
					<br/>
					
				</div>
		</div>
		<div style="display:none; height:150px;margin-left:5%;margin-top:60px;width:90%;" id="wblogon">		
			
			<div style="height:150px; width:40%;float:left;text-align:center;">
				<br /><br /><br /><br />
				<a href="https://api.weibo.com/oauth2/authorize?client_id=595918520&response_type=code&redirect_uri=http://labacrm.com/CRM/sinaauthcallback.jsp" style="text-decoration: none; border: none;" target="_self"> 
					<img style="border: none;" src="http://tjs.sjs.sinajs.cn/t3/style/images/common/card/widget_connect.gif" />
				</a>
			</div>
			<div style="height:150px; width:60%;float:right;text-align:left;vertical-align:middle;">
				
				&nbsp;&nbsp;<img style="border: none; height:150px" src="img/labakai1.png" />
				
			</div>
			
			
				<%-- <table cellpadding="0" cellspacing="0" width="950px">
				<tr>
					<td align=cneter width="300">
						
					</td>
				
					<td align="left">
						<img style="width:500px" src="img/mainpage.jpg">
					</td>
				</tr>
			</table>--%>
			<br /><br />
		</div>
		<%
			//AccessToken atoken = (AccessToken)session.getAttribute("sinaaccessToken");
				
							Object uid = session.getAttribute("uid");
								//Object sinaatstr = session.getAttribute("sinaatstr");
								//Object isnewbu = session.getAttribute("isnewbu");
								//User u = new User();
							if(request.getServerName().equals("localhost")){
								 %> <script type="text/javascript">
									function wblogon() {
										document.getElementById("wblogon").style.display = "block";
										//document.getElementById("mainfunctions").style.display = "none";
									}
									wblogon();
								</script> 
								<%	
							}else{
								if (uid != null) {
									%>
									 	<script>
								        	function _gotoLogin(){
								        		window.open("/CRM/sinaauthcallback.jsp","_self");
								        	}
								        	 _gotoLogin();
								        </script>
								 	<%
							 	} else {
							 %> <script type="text/javascript">
									function wblogon() {
										document.getElementById("wblogon").style.display = "block";
										//document.getElementById("mainfunctions").style.display = "none";
									}
									wblogon();
								</script> 
							<%
							 	}
							 	
							}
							%>
	<script type="text/javascript">
	/*
		WB2.anyWhere(function(W) {
			W.widget.hoverCard({
				id : "businesscard"
			});
		});*/
	</script>
	
	<div class="splitLine" ></div>
	
	<div class="introductdiv">
		<font size=5>会员与积分管理</font>
		<br />
		<div class="introductsubdivleft">
			<p>
			<font color="gray" >
				还在花钱制作会员卡？
				还在要求客户出示会员卡？
				还在和没带卡的顾客争论到底能不能享受会员价？<br /><br/>
			</font>
			<font >
				在喇叭，您在线的粉丝和您关注的，都将自动成为您的会员，轻轻松松进行会员和积分管理，再也不用担心卡在哪里啦,
				<br/>同时，转发、评论您微博的会员，都将赠送一定的奖励积分，没有比这更好的宣传途径了。
			</font>
			</p>
		</div>
		<div class="introductsubdivright">
			<img alt="" style="width:350px" src="img/novipcard.png">
		</div>
	</div>
	<div class="splitLine" ></div>
	<div class="introductdiv">
		<font size=5>在线预订和管理</font>
		<br />
		<div class="introductsubdivleft">
			<img alt="" style="width:350px" src="img/reservation.png">
		</div>
		<div class="introductsubdivright">
			<br />
			<p>
				客户直接在线预定，直接浏览贵店菜单、选菜，简单方便；<br/><br/>
				在喇叭上您可以实时查看预定并在线回复，简单方便却不会让您漏掉任何一个预定，大大提高预定管理效率和客户满意度；
			</p>
		</div>
		
	</div>
	<div class="splitLine" ></div>
	
	<div class="introductdiv">
		<font size=5>您的在线菜单</font>
		<br />
		<div class="introductsubdivleft">
			<br />
				<p>
					简单的操作，即可拥有一份精美的在线菜单；
					您的客户可以轻松浏览，并直接选菜、下单，这么好的东东，谁用谁知道。
				</p>
		</div>
		<div class="introductsubdivright">
			<img alt="" style="width:350px" src="img/menu.png">
		</div>
	</div>
	<div class="splitLine" ></div>
	<div class="introductdiv">
		<font size=5>自助式团购</font>
		<br/>
		<div class="introductsubdivleft">
				<img alt="" style="width:350px" src="img/tuan.png">
		</div>
		<div class="introductsubdivright">
			<br />
			<p>
				轻松开团，并可实时发布在微博等最热门网络平台，让您的团购不再受制于任何一个团购网站。	
			</p>
		</div>
		
	</div>
	<div class="splitLine" ></div>
	<font size="1">LabaCRM.com Copyright @佰传信息  -津ICP备13005372号 </font>
	<img src="SAELogo1.png"/>
</center>
<script type="text/javascript">
$(document).ready(function() {
 $(function() {
    $("input[type=button]").button();
    $("#helplink").button();
    $("#onlineagentlink").button();
    $("#aboutuslink").button();
    $("#contactuslink").button();					
  });
});
</script>
</body>
</html>