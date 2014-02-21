package com.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.UIRedirectURL;


public class SpringHibernateBean {
	
	private static ApplicationContext context = null;
	public SpringHibernateBean() {
		// TODO Auto-generated constructor stub
	}
	public static ApplicationContext getContext(){
		try {
			if(context == null){
				context = 
					new FileSystemXmlApplicationContext(UIRedirectURL.getSprintHibernateXML());
			}
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return context;
	}
}