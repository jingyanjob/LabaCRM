<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="weibo4j.*" %> 
<%@ page language="java" import="weibo4j.http.*" %> 
<%@ page language="java" import="weibo4j.util.*" %> 
<%@page import="com.CRM.data.BizUser"%>
<%@page import="weibo4j.model.WeiboException"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微博登陆</title>
</head>
<body>
<% 
	String code=request.getParameter("code");
    try{
	    if(code != null){
	        Oauth oauth = new Oauth();
	        AccessToken atoken = oauth.getAccessTokenByCode(code);
	        String uid = atoken.getUid();
	        String at = atoken.getAccessToken();
	        String expire = atoken.getExpireIn();
	        session.setAttribute("uid", uid);//atoken.getUid());
	        session.setAttribute("sinaatstr",at);//atoken.getAccessToken());
	        session.setAttribute("expire",expire);//atoken.getAccessToken());
	        out.println("<div style='width:99%;align:center;font:25px;'>系统正在初始化，马上完成...</div>");
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
	    	Object uid = session.getAttribute("uid");
	    	if(uid != null){
	    		 %>
	 		 	<script>
	 	        	function _back(){
	 	        		window.open("/CRM/mainframelogin.jsp","_self");
	 	        	}
	 	        	 _back();
	 	        </script>
	 	 	      <%
	    	}else{
	    		out.println("呀，出错了，已经通知管理员，您也可以几分钟后再次尝试登陆，对此给您带来的不变，我们深感歉意。"); 
	    	}
	    } 
    }catch(WeiboException e){
    	//out.println("ERROR HERE:");
    	out.println("呀，出错了，已经通知管理员，您也可以几分钟后再次尝试登陆，对此给您带来的不变，我们深感歉意"); 
    	//out.println(e.getMessage() + e.getCause().getLocalizedMessage() + e.getStackTrace().toString()); 
    	//out.println(e.getStatusCode() + " ---------- " + e.getError() + " ++++ " + e.getErrorCode()); 
    	//e.printStackTrace();
    }finally{
	      // out.print("<script>function _back(){");
	      //  out.print("window.open('/CRM/mainframelogin.jsp?code="+code+"' + ,'_self');} _back();</script>");
    }
%>
</body>
</html>