package com.CRM;

import org.hibernate.HibernateException;

import weibo4j.model.User;
import weibo4j.model.WeiboException;

import com.CRM.data.BizUser;
import com.CRM.data.ScoreRole;
import com.CRM.data.dao.BizUserDao;
import com.CRM.data.dao.ScoreRoleDao;
import com.CRM.systemtasks.BUInitTask;
import com.CRM.systemtasks.WeiboTask;
import com.xp.cache.CacheFactory;

public class BizUserMgr {
	
	private BizUserDao bizUD ;
	private ScoreRoleDao sRD ;
	
	public BizUserMgr() throws Exception{
		bizUD = com.config.DataApiInstance.instanceBizUserDao();
		sRD = com.config.DataApiInstance.instanceScoreRoleDao();
	}

	public String init(String at, String expire, String uid) throws Exception{
		BizUser bu = this.getBizUser(uid);//new BizUser();
		if( bu == null){
			bu = new BizUser();
			bu.setUid(uid);
			bu.setUsername("NEW");
		}
		bu.setAtoken(at);
		bu.setExpirein(expire);
		return this.refreshBizUser(bu, at);
	}
	
	public String refreshBizUser(BizUser bu, String at) throws Exception{
		ScoreRole sr = null;
		if(bu.getUsername().equals("NEW")){
			bu.setRepsinceid("1");
			bu.setCmssinceid("1");
			WeiboTask wt = new WeiboTask();
			User u = wt.getUser(at, bu.getUid());
			bu.setUsername(u.getName());
			bizUD.insert(bu);
			sr = GlobalStaticData.getDefaultSR();
			sr.setUid(bu.getUid());
			sRD.insert(sr);
			
		}else{
			bizUD.update(bu);
		}
		//initial current bu cache
		//CacheFactory.getCache(bu.getUid());
		return BUInitTask.run(bu);
	}
	public BizUser getBizUser(String uid){
		return bizUD.getBizUser(uid);
	}
	public BizUser getBizUserByName(String username, int sourcesite){
		return bizUD.getBizUserByName(username,sourcesite );
	}
	public void updateScoreRole(ScoreRole sr){
		sRD.insert(sr);
	}
	public void updateBizUser(BizUser bu){
		bizUD.update(bu);
	}
	public BizUser[] getBizUsers(){
		return bizUD.getBizUsers();
	}
	public ScoreRole getScoreRole(String uid){
		return sRD.getScoreRole(uid);
	}
	public static void main(String[] str) throws Exception{
	}
}
