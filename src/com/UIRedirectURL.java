package com;

public class UIRedirectURL {
	public static int runMode = 5; //0: dev, 1: test, 5: real run
	
	public static String devProjectName = "/100train";
	//imobile logon successful url
	public static String realProjectName = "/CRM";//
	
	public static String logonSuccUrl = "/imobilepage/uiframe/frame1.jsp";
	
	public static String logonPage = "/imobilepage/index";
	
	public static String getCurrentLogonSucUrl(){
		if(runMode == 0){
			return logonSuccUrl;//devProjectName +
		}else if(runMode == 5){
			return realProjectName + logonSuccUrl;
		}else{
			return devProjectName + logonSuccUrl;
		}
	}
	public static String getCurrentLProjectUrl(){
		if(runMode == 0){
			return "";// devProjectName;
		}else if(runMode == 5){
			return realProjectName;
		}else{
			return devProjectName;
		}
	}
	public static String getLogonUrl(){
		if(runMode == 0){
			return  logonPage;//devProjectName +
		}else if(runMode == 5){
			return realProjectName + logonPage;
		}else{
			return devProjectName + logonPage;
		}
	}
	public static String getSprintHibernateXML(){
		if(runMode == 0){
			return "classpath:/com/config/Spring_Hibernate_bean.xml";
		}else if(runMode == 5){
			return "classpath:/com/config/Spring_Hibernate_bean_real.xml";
		}else{
			return "classpath:/com/config/Spring_Hibernate_bean.xml";
		}
	}
}
