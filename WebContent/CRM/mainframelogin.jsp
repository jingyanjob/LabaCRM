<%@page import="com.CRM.systemtasks.BUInitTask"%>
<%@page import="com.CRM.data.BizUser"%>
<jsp:directive.page import="weibo4j.model.User"/>
<jsp:directive.page import="weibo4j.Users"/>
<%@page import="com.CRM.systemtasks.WeiboTask"%>
<%@page import="com.xp.cache.CacheFactory" %>
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
<script src=" http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=595918520"
	type="text/javascript" charset="utf-8"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" href="css/start/jquery-ui-1.8.23.custom.css" rel="stylesheet" /><base>
<%--

<script type="text/javascript" src="js/jquery-ui-1.10.3.custom.min.js"></script>
 --%>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/masonry.js"></script>

<script type="text/javascript">
</script>
</head>
<body style="font-size: 14px; margin-top: 20px;">
<center>
		<div id="mainfunctions"
			style="width: 88%; margin-left: 6%; margin-right: 6%;">
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td align="left"><img style="" src="img/logofullwsite.png">
					</td>
					<td align="right">
						<div id="newbuinit"></div>
						<div id="logoninfo"></div>
					</td>
				</tr>
			</table>
			<jsp:include page="mainframe1.jsp"></jsp:include>
		</div>
		<%
		try{
			//AccessToken atoken = (AccessToken)session.getAttribute("sinaaccessToken");
			Object uid = session.getAttribute("uid");
			Object sinaatstr = session.getAttribute("sinaatstr");
		//	Object isnewbu = session.getAttribute("isnewbu");
			User u = new User();
			if (request.getServerName().equals("localhost")) {
				//for local test
			} else {
				if (uid != null) {
					VIPUserMgr vum = new VIPUserMgr();
					if (session.getAttribute("user") == null) {
						WeiboTask us = new WeiboTask();
						u = us.getUser(sinaatstr.toString(), uid.toString());
						session.setAttribute("user", u);
						session.setAttribute("location", u.getLocation());
					} else {
						u = (User) session.getAttribute("user");
						session.setAttribute("location", u.getLocation());
					}
					
					/*out.print("<script> function newbuinit(){"
								+ "document.getElementById(\"newbuinit\").innerHTML=\"进行系统初始化进行中,将很快完成，请稍后: 1. 正在导入新会员......\";"
								+ "}newbuinit();</script>");
					if (isnewbu != null && isnewbu.toString().equals("true")) {
						BizUser bu = new BizUser();
						bu.setUid(uid.toString());
						bu.setAtoken(sinaatstr.toString());
						out.print("<script> function newbuinit(){"
								+ "document.getElementById(\"newbuinit\").innerHTML=\"进行系统初始化进行中,将很快完成，请稍后: 1. 正在导入新会员......\";"
								+ "}newbuinit();</script>");
						String re = BUInitTask.run(bu);
						session.setAttribute("isnewbu", "false");
						out.print("<script> function newbuinitend(){"
								+ "document.getElementById(\"newbuinit\").innerHTML=\"初始化完成， 共导入VIP客户 "
								+ re + " 人\";" + "}newbuinitend();</script>");
					}
					*/
					/*initial user info cross front and end*/
					long vipnumber = vum.getNumber(uid.toString());
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
							+ "document.getElementById(\"logoninfo\").innerHTML="
							+ " '<img style=\"height:35px;\" src=\"" + u.getProfileImageURL()
							+ "\" /><span id=\"businesscard\" href=\"http://weibo.com\" wb_screen_name="
							+ u.getScreenName() + " ><u>" + u.getScreenName()
							+ "</u></span>"
							+ "<input id=bulocation type=hidden value=\""
							+ u.getLocation() + "\"/>" + u.getLocation() + "  "
							+ " 拥有 " + vipnumber + " 位VIP会员"
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
	//	reservationChk();
		/*
		WB2.anyWhere(function(W){
		    W.widget.connectButton({
		        id: "businesscard",
		        type: '3,2',
		        callback : {
		            login:function(o){
		                alert(o);
		            },
		            logout:function(){
		                //alert('logout');
		            }
		        }
		    });
		});*/
	</script>
	<font size="1">LabaCRM.com Copyright @佰传信息  -津ICP备13005372号 </font>
	<img src="SAELogo1.png"/>
</center>
</body>
</html>