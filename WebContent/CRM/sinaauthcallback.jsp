<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="weibo4j.*" %> 
<%@ page language="java" import="weibo4j.http.*" %> 
<%@ page language="java" import="weibo4j.util.*" %> 
<%@page import="com.CRM.BizUserMgr"%>
<%@page import="com.CRM.data.BizUser"%>
<%@page import="com.CRM.data.ScoreRole"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微博登陆</title>
</head>
<body>
<% 
    // 获得HTTP请求中的 oauth_verifier 参数 
	String code=request.getParameter("code");
    try{
	    if(code != null){
	        Oauth oauth = new Oauth();
	        AccessToken atoken = oauth.getAccessTokenByCode(code);
	        String uid = atoken.getUid();
	        String at = atoken.getAccessToken();
	        String expire = atoken.getUid();
	        session.setAttribute("uid", uid);//atoken.getUid());
	        session.setAttribute("sinaatstr",at);//atoken.getAccessToken());
	        out.println("<div style='width:99%;align:center;font:25px;'>正在进行用户系统初始化，将很快完成：1. 正在导入新会员......</div>");
	        BizUserMgr bum = new BizUserMgr();
	 	    bum.init(at, expire, uid);
	 	   %>
		 <script>
	        	function _back(){
	        		window.open("/CRM/mainframelogin.jsp","_self");
	        	}
	        	 _back();
	        </script>
	 	      <%
		    //   response.sendRedirect(url); may missing the session if use response.sendRedirect
	    }else{
	        out.println("code String error"); 
	    } 
    }catch(Exception e){
    	out.println("ERROR HERE:");
    	out.println(e.getMessage() + e.getCause().getLocalizedMessage() + e.getStackTrace().toString()); 
    	// out.println( " <meta   http-equiv= 'refresh'   content='0' url='"+url+"'> "); 
    }
%>
</body>
</html>