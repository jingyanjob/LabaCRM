<%@ page import="com.CRM.data.BizUser"%>
<jsp:directive.page import="weibo4j.model.User"/>
<jsp:directive.page import="weibo4j.Users"/>
<%@ page import="com.CRM.systemtasks.WeiboTask"%>
<%@ page import="com.CRM.BizUserMgr"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" href="jq104/css/ui-lightness/jquery-ui-1.10.4.custom.css" rel="stylesheet" />
<script src=" http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=595918520"
	type="text/javascript" charset="utf-8"></script>
<title>喇叭CRM</title>
<meta property="wb:webmaster" content="27304571f6f8edb0" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
.maindivtop { 
	width: 100%; 
	left:0px;
	top: 0px;
	height:60px;
	background-color:#F4F5F3;
	position:fixed;
	left: 0;
	width: 100%;
	border-bottom: 2px #F4F5F3 groove;
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
		<div class="logininfo">
					<%--
						 class="ui-widget-header ui-state-default logininfo"
					 --%>
			<div id="logoninfo" style="width:445px;float:left;text-align:left">
			&nbsp;&nbsp;登录信息
			</div>
			<div id="newbuinit" style="width:445px;float:right;text-align:right">
				初始化信息
			</div>
					</div>
		<div class=maindiv1 >
			<jsp:include page="mainframe1.jsp"></jsp:include>
		</div>
		<div class=companyinfo >
			<font size="2">佰传信息技术有限公司 出品  labacrm.com - 津ICP备13005372号 </font>
			<br>
			<img style="height:25px;" src="SAELogo1.png"/>
		</div>
		<%
		try{
			//AccessToken atoken = (AccessToken)session.getAttribute("sinaaccessToken"); 
			Object uid = session.getAttribute("uid");
			Object sinaatstr = session.getAttribute("sinaatstr");
			Object expire = session.getAttribute("expire");
		//	Object isnewbu = session.getAttribute("isnewbu");
			User u = new User();
			if (request.getServerName().equals("localhost")) {
				//for local test
			} else {
				if (uid != null) {
					if (session.getAttribute("user") == null) {
						WeiboTask wt = new WeiboTask();
						u = wt.getUser(sinaatstr.toString(), uid.toString());
						session.setAttribute("user", u);
						session.setAttribute("location", u.getLocation());
					} else {
						u = (User) session.getAttribute("user");
						session.setAttribute("location", u.getLocation());
					}
					out.print("<script> function newbuinit(){"
								+ "document.getElementById(\"newbuinit\").innerHTML=\"系统正在初始化，马上完成...\";"
								+ "}newbuinit();</script>");
					BizUserMgr bum = new BizUserMgr();
					String re = bum.init(sinaatstr.toString(), expire.toString(), uid.toString());
					String buinfo = "<span id=\"businesscard\" href=\"http://weibo.com\" wb_screen_name="
							+ u.getScreenName()
							+ " ><u>"
							+ u.getScreenName()
							+ "</u></span>"
							+ "<input id=hiduid type=hidden value="
							+ uid
							+ "  />"
							+ "<input id=hidatstr type=hidden value="
							+ sinaatstr + "  />";
					//CacheFactory.getCache(uid.toString());
					out.print(" <script>currentUID=\""
							+ uid
							+ "\"; currentATStr=\""
							+ sinaatstr
							+ "\"; function loginsuccess(){"
							+ "document.getElementsByName(\"imgbuid\").item(0).value=" + uid +";"//product pic
							//+ "document.getElementsByName(\"imgbuid\").item(1).value=" + uid +";"//salescase pic
							+ "document.getElementById(\"logoninfo\").innerHTML=' &nbsp;&nbsp;"
							//+ " '<img style=\"height:35px;\" src=\"" + u.getProfileImageURL()
							//+ "\" />"
							+ "<span id=\"businesscard\" href=\"http://weibo.com\" wb_screen_name="
							+ u.getScreenName() + " ><u>" + u.getScreenName()
							+ "</u></span>"
							+ "<input id=bulocation type=hidden value=\""
							+ u.getLocation() + "\"/>" //+ u.getLocation() + "  "
							//+ " 拥有 " + vipnumber + " 位VIP会员"
							+ "<input id=hiduid type=hidden value=" + uid
							+ "  />" + "<input id=hiduname type=hidden value="
							+ u.getScreenName() + "  />"
							+ "<input id=hidatstr type=hidden value="
							+ sinaatstr + "  />';"
							+ "}loginsuccess();</script>");
				} else {
				}
			}
		}catch(Exception e){
			out.println(e.getMessage() + "出错了，非常抱歉，已通知管理员，请您稍后重试一下，十分感谢。");
			out.println(e.getMessage() + e.getCause().getLocalizedMessage() + e.getStackTrace().toString()); 
			e.printStackTrace();
		}
		%>
		<%----%>
<script type="text/javascript">
		WB2.anyWhere(function(W) {
			W.widget.hoverCard({
				id : "businesscard",
				type: '3,2'
			});
		});

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