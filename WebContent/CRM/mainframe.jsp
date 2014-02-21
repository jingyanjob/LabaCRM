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
<script type="text/javascript">
</script>
</head>
<body style="font-size: 14px; margin-top: 20px; background-color: #EEEEEE">
<center>
		<div style="display:none;background-color: white;" id="wblogon">
			<br /><br />
			<table cellpadding="0" cellspacing="0" width="950px">
				<tr>
					<td align=right>
						请点击登录：
						<a href="https://api.weibo.com/oauth2/authorize?client_id=595918520&redirect_uri=http://labacrm.com/CRM/sinaauthcallback.jsp&response_type=code" style="text-decoration: none; border: none;" target="_self"> <img style="border: none;" src="http://tjs.sjs.sinajs.cn/t3/style/images/common/card/widget_connect.gif" />
						</a>
					</td>
					<td align="left">
						<img style="width:700px" src="img/mainpage.png">
					</td>
				</tr>
			</table>
			<br /><br />
		</div><%
								//AccessToken atoken = (AccessToken)session.getAttribute("sinaaccessToken");
								Object uid = session.getAttribute("uid");
								Object sinaatstr = session.getAttribute("sinaatstr");
								Object isnewbu = session.getAttribute("isnewbu");
								User u = new User();
							if(request.getServerName().equals("localhost")){
							}else{
								if (uid != null) {
									%>
									 	<script>
								        	function _back(){
								        		window.open("/CRM/mainframelogin.jsp","_self");
								        	}
								        	 _back();
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
		WB2.anyWhere(function(W) {
			W.widget.hoverCard({
				id : "businesscard"
			});
		});
	</script>
	
	<div class="splitLine" ></div>
	
	<div style="height:200px;width:600px">
		<font size=5>会员和积分管理</font>
		<p>您微博的粉丝和您关注的所有人，都回自动成为您的会员
					<br />
					转发、评论您微博的会员，都将赠送一定的奖励积分，以增加会员对您的宣传积极性
				</p>
	</div>
	<div class="splitLine" ></div>
	<div style="height:200px;width:600px">
		<font size=5>微博在线预订管理</font>
			<p>
					发微博 @您  并输入 预订 + 内容(时间、人数等)，即可实现在线预订
					<br>
					您可以随时查看预订情况，并第一时间回复确认
				</p>
	</div>
	<div class="splitLine" ></div>
	
	<div style="height:200px;width:600px">
		<font size=5>自助式的互联网营销功能</font>
		<p>
					您微博的粉丝和您关注的所有人，都回自动成为您的会员；
					<br />
					转发、评论您微博的会员，都将赠送一定的奖励积分，以增加会员对您的宣传积极性；
				</p>
	</div>
	<div class="splitLine" ></div>
	<div style="height:200px;width:600px">
		<font size=5>菜品的统一管理和发布</font>
		<p>
					您微博的粉丝和您关注的所有人，都回自动成为您的会员；
					<br />
					转发、评论您微博的会员，都将赠送一定的奖励积分，以增加会员对您的宣传积极性；
				</p>
	</div>
	<div class="splitLine" ></div>
	<font size="1">LabaCRM.com Copyright @佰传信息  -津ICP备13005372号 </font>
	<img src="SAELogo1.png"/>
</center>
</body>
</html>