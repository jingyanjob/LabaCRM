package com.CRM.timer;

import java.util.Timer;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.CRM.systemtasks.GeneralTask;

public class Listener implements ServletContextListener {
	private int second = 1000;
	private int minute = 60 * second;
	private int hour = 60 * minute;
	private int day = 24 * hour;
	private Timer timer = null;
	
	public void contextInitialized(ServletContextEvent event) {
		//System initial
		//SystemInitTask.run(bu)
		timer = new Timer(true);
		// 设置任务计划，启动和间隔时间
		
		System.out.println("================== task start ===================");
		timer.schedule(new GeneralTask(),0, day);
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		timer.cancel();
	}

}
