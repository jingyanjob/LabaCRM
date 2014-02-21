package com.CRM.systemtasks;

import java.util.Date;
import java.util.TimerTask;

import com.CRM.systemtasks.RegularWeiboTask;

public class GeneralTask extends TimerTask {

	public void run() {
		System.out.println("Run at " + (new Date()));
		// TODO 此处添加具体任务代码
		RegularWeiboTask rgt = null;
		try {
			rgt = new RegularWeiboTask();
			rgt.scoreTask();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
