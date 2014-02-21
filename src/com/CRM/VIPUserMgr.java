package com.CRM;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import weibo4j.model.User;

import com.CRM.data.BizUser;
import com.CRM.data.VIPScore;
import com.CRM.data.VIPUser;
import com.CRM.data.dao.VIPScoreDao;
import com.CRM.data.dao.VIPUserDao;
import com.CRM.systemtasks.WeiboTask;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class VIPUserMgr {
	private VIPUserDao vud ;
	private VIPScoreDao vsd ;
	private WeiboTask wt = new WeiboTask();
	/**
	 * USER
	 * @param vu
	 * @throws Exception 
	 */
	public VIPUserMgr() throws Exception{
		vud =  com.config.DataApiInstance.instanceVIPUserDao();
		vsd =  com.config.DataApiInstance.instanceBizVIPScoreDao();
	}
	
	public int newVIPUser(VIPUser vu){
		if(this.getVUser(vu.getUid(), vu.getBuid()) == null){
			vud.insert(vu);
			VIPScore vs = new VIPScore();
			vs.setIsplus(0);
			vs.setPerscore(0);
			vs.setTotalscore(0);
			vs.setUid(vu.getUid());
			vs.setBuid(vu.getBuid());
			vs.setUsername(vu.getUsername());
			vs.setSourcesite(vu.getSourcesite());
			vs.setWeiboid("1");
			this.newVIPScore(vs);
			return 1;
		}else
			return 0;
	}
	public VIPUser getVUser(String userid, String buid){
		return vud.getVIPUser(userid, buid, 0);
	}
	public VIPUser getVUserByName(String username, String buid){
		return vud.getVIPUserByName(username, buid, 0);
	}
	
	public VIPUser[] getVUsersByName(String username, String buid){
		return vud.getVIPUsersByName(username, buid, 0);
	}
	/**
	 * 
	 * @param location
	 * @param buid
	 * @param number
	 * @return
	 */
	public VIPUser[] getVUsersByLocation(String location, BizUser bu, int number){
		VIPUser[] vus = vud.getVIPUsersByLocation(location, bu.getUid(), 0, number);
	//	System.out.println("Get bilateral start -  for sales case :" + bu.getUid() +" ++ "+ location + vus.length );
		return vus;
	}
	
	public VIPUser[] get2TierUser(VIPUser[] vus, int number){
		//to be delete or implement
		VIPUser[] vusplus = new VIPUser[number];

		return vusplus;
	}
	
	public VIPUser[] getVUsers(String bId){
		return vud.getVIPUser(bId);
	}
	/**
	 * Score
	 * @param vu
	 */
	public void newVIPScore(VIPScore vs){
		vsd.insert(vs);
	}
	
	public VIPScore getVScore(String uid, String buid, int sourceSite ){
		return vsd.getVIPScore(uid, buid,  sourceSite);
	}
	//get vip user number
	public long getNumber(String buid){
		long n = Long.parseLong(vud.getNubmer(buid).get(0).toString());
		return n;
	}
	public static VIPUser setUser(String buid, User u){
		VIPUser vu = new VIPUser();
		vu.setBuid(buid);
		vu.setUid(u.getId());
		vu.setUsername(u.getScreenName());
		vu.setProvince(u.getProvince());
		vu.setCity(u.getCity());
		vu.setLocation(u.getLocation());
		if(u.getDescription() == null)
			vu.setDescription("");
		else
			vu.setDescription(u.getDescription());
		
		if(u.getURL()== null)
			vu.setUrl("");
		else
			vu.setUrl(u.getURL().toString());
		
		if(u.getProfileImageURL() == null)
			vu.setProfileImageUrl("");
		else
			vu.setProfileImageUrl(u.getProfileImageURL().toString());
		if(u.getGender() == null)
			vu.setGender("");
		else
			vu.setGender(u.getGender());
		vu.setFriendsCount(u.getFriendsCount());
		vu.setFollowersCount(u.getFollowersCount());
		vu.setVerifiedType(u.getverifiedType());
		vu.setBiFollowersCount(u.getbiFollowersCount());
		return vu;
		/*
	private String userDomain;            //用户个性化URL
	private int statusesCount;            //微博数
	private int favouritesCount;          //收藏数
	private Date createdAt;               //创建时间
	private boolean following;            //保留字段,是否已关注(此特性暂不支持)
	private boolean verified;             //加V标示，是否微博认证用户
	private boolean allowAllActMsg;       //是否允许所有人给我发私信
	private boolean allowAllComment;      //是否允许所有人对我的微博进行评论
	private boolean followMe;             //此用户是否关注我
	private String avatarLarge;           //大头像地址
	private int onlineStatus;             //用户在线状态
	private Status status = null;         //用户最新一条微博
	private String remark;                //备注信息，在查询用户关系时提供此字段。
	private String verifiedReason;		  //认证原因
		 * */
		
	}
	public static void main(String[] str) throws Exception{
		VIPUserMgr vum = new VIPUserMgr();
		System.out.println(vum.getNumber("3"));
	}
}
