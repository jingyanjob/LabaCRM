package com.CRM.systemtasks;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.TimerTask;

import weibo4j.model.Status;
import weibo4j.model.User;

import com.CRM.VIPUserMgr;
import com.CRM.data.BizUser;
import com.CRM.data.VIPUser;
import com.CRM.systemtasks.RegularWeiboTask;

public class BUInitTask { 
	public String run(BizUser bu) throws Exception {
		//initial business user`s VIP: follows & friends;
		return newBUInitial(bu);
	}
	/**
	 * c初始化BU的会员，规则为：所有粉丝和所有关注；免费用户最多初始化2000粉丝和2000关注，付费待定?
	 * @param bu
	 * @return
	 * @throws Exception 
	 */
	public String newBUInitial(BizUser bu) throws Exception{
		WeiboTask wt = new WeiboTask();
		VIPUserMgr vum = new VIPUserMgr();
		List<User> follows = wt.getFollowUps(bu.getAtoken(), bu.getUid());
		List<User> friends =  wt.getFriends(bu.getAtoken(), bu.getUid());//
		List<User> all = new ArrayList<User>();//
		System.out.println("粉丝数：" + follows.size() + "  关注数：" + friends.size());
		VIPUser[] vu = new VIPUser[follows.size() + friends.size()];
		Hashtable<String, User> ht = new Hashtable<String, User>();
		all.addAll(follows);
		all.addAll(friends);
		for(User u: all){
			if(!ht.containsKey(u.getId())){
				ht.put(u.getId(), u);
			}
		}
		Enumeration<User>  e = ht.elements(); 
		int i = 0;
		int importno = 0;
		while(e.hasMoreElements()){ 
			User u= e.nextElement();
			vu[i] = new VIPUser();
			vu[i] = VIPUserMgr.setUser(bu.getUid(), u);
			importno = importno +vum.newVIPUser(vu[i]);
			i++;
		}
		return "" + importno;
	}
	
	public static void main(String[] str){
		BizUser bu = new BizUser();
		bu.setUid("");
		bu.setAtoken("");
		
	}
}
