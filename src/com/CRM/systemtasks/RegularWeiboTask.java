package com.CRM.systemtasks;

import java.util.List;

import weibo4j.model.Comment;
import weibo4j.model.Status;

import com.CRM.BizUserMgr;
import com.CRM.GlobalStaticData;
import com.CRM.VIPUserMgr;
import com.CRM.data.BizUser;
import com.CRM.data.VIPScore;

public class RegularWeiboTask {
	private WeiboTask wt = new WeiboTask();
	private VIPUserMgr vipmgr ;
	
	public RegularWeiboTask() throws Exception{
		vipmgr = new VIPUserMgr();
	}
	/**
	 * add repost score
	 * @param bu
	 * @param sinceId
	 * @return
	 */
	private long repostScore(BizUser bu, long sinceId){
		if(sinceId == 0){
			sinceId = 1;
		}
		List<Status> rss = wt.getRepostList(bu.getAtoken(), sinceId);
		if(rss == null){
			return sinceId;
		}
		Status jo = null;
		//int s = GlobalStaticData.getBUSRole(bu.getUid()).getPerrepostscore();
		int s = GlobalStaticData.getDefaultSR().getPerrepostscore();
		VIPScore vs= new VIPScore();
		for(int i=0; i<rss.size();i++){
			jo = (Status)rss.get(i);
			vs.setIsplus(0);
			vs.setPerscore(s);
		    vs.setUid(jo.getUser().getId());
			vs.setTotalscore(0);
			vs.setBuid(bu.getUid());
			vs.setSourcesite(0);
			vs.setUsername(jo.getUser().getName());
			vipmgr.newVIPScore(vs);
			System.out.println("log repost score: " + jo.getUser().getName() + "  " + s);
			if(sinceId < Long.valueOf((jo.getId())).longValue()){
				sinceId = Long.valueOf((jo.getId())).longValue();
			}
		}
		if(jo != null & jo.getId().length() < 13){
			return Integer.valueOf(jo.getId());
		}else{
			return sinceId;
		}
	}
	/**
	 * add comment score
	 * @param bu
	 * @param sinceId
	 * @return new since id
	 */
	private long commentScore(BizUser bu, long sinceId){
		if(sinceId == 0){
			sinceId = 1;
		}
		System.out.println(bu.getAtoken());
		List<Comment> css = wt.getCommentList(bu.getAtoken(), sinceId);
		if(css == null){
			return sinceId;
		}
		Comment jo = null;
		//int s = GlobalStaticData.getBUSRole(bu.getUid()).getPercommentscore();		
		int s = GlobalStaticData.getDefaultSR().getPercommentscore();
		VIPScore vs= new VIPScore();
		for(int i=0; i<css.size();i++){
			jo = (Comment)css.get(i);
			vs.setIsplus(0);
			vs.setPerscore(s);
		    vs.setUid(jo.getUser().getId());
			vs.setTotalscore(0);
			vs.setBuid(bu.getUid());
			vs.setSourcesite(0);
			vs.setUsername(jo.getUser().getName());
			vs.setWeiboid(String.valueOf(jo.getId()));
			vs.setWeibotime(jo.getCreatedAt());
			vipmgr.newVIPScore(vs);
			System.out.println("log comments score: " + jo.getUser().getName() + "  " + s);
			if(jo.getId() < 0){
				if(sinceId < -jo.getId()){
					sinceId = -jo.getId();
				}
			}else{
				if(sinceId < jo.getId()){
					sinceId = jo.getId();
				}
			}
			
		}
		if(jo != null){
			return (int)jo.getId();
		}else{
			return sinceId;
		}
	}
	/**
	 * entry of score tasks
	 * @throws Exception
	 */
	public void scoreTask() throws Exception{
		BizUserMgr bum = new BizUserMgr();
		BizUser[] bus = GlobalStaticData.getBizUsers(true);
		for(int i=0; i<bus.length; i++){
			//System.out.println(bus[i].getUid() +bus[i].getUpdatetime() + bus[i].getRepsinceid() + " -------  new----------- " + bus[i].getCmssinceid());
			long newRepSinceId = repostScore(bus[i], Long.valueOf(bus[i].getRepsinceid()));
			
			long newCmsSinceId = commentScore(bus[i], Long.valueOf(bus[i].getCmssinceid()));
			
			System.out.println(bus[i].getId() + "" + bus[i].getUsername() 
					+ "newRepSinceId: " + newRepSinceId + " newCmsSinceId: " + newCmsSinceId);
			bus[i].setRepsinceid(String.valueOf(newRepSinceId));
			bus[i].setCmssinceid(String.valueOf(newCmsSinceId));
			/**
			 * consider batch update
			 */
			bum.updateBizUser(bus[i]);
		}
	}
	
}
