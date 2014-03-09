package com.CRM;

import java.util.HashMap;

import com.CRM.data.BizUser;
import com.CRM.data.ScoreRole;

public class GlobalStaticData {
	private static BizUserMgr bum ;
	private static BizUser[] bus = {} ;
	
	/********************VIP score part***************************/
	public static int perCommentScore = 20;
	public static int perRepostScore = 50;
	public static int perDayMaxScore = 200;
	public static String spliter = "#@";
	public static String spliter1 = "@#";
	public static int vipBatchSize = 200;//初始化、更新vip用户时 ，每次请求微博粉丝\关注的列表个数
	
	public static String vsChangebyBU_wbid = "10000";// 商家手动修改vip 积分时候的weibo id
	
	//sr.setPercommentscore(50);
	//sr.setPerrepostscore(100);
	//sr.setPerdaymaxscore(500);
	//
	/********************BU part***************************/
	public final static int freeBUVIPUserNo = 2000;
	public final static int payBUVIPBUSerNO = 1000000;
	private static HashMap<String, ScoreRole> buSR = new HashMap<String, ScoreRole>();
	public static BizUser[] getBizUsers(boolean refresh) throws Exception{
		if(bus.length == 0 || refresh) {
			//initial global BizUser list;
			BizUserMgr bum = new BizUserMgr();
			bus = bum.getBizUsers();
		}
		return bus;
	}

	public static ScoreRole getBUSRole(String uid){
		ScoreRole sr = new ScoreRole();
		if(buSR.containsKey(uid)){
			return buSR.get(uid);
		}else{
			sr = bum.getScoreRole(uid);
			return sr;
		}
	}
	public static ScoreRole getDefaultSR(){
		ScoreRole sr = new ScoreRole();
		sr.setPercommentscore(perCommentScore);
		sr.setPerrepostscore(perRepostScore);
		sr.setPerdaymaxscore(perDayMaxScore);
		return sr;	
	}
	
	/**
	 * product
	 */
	public final static int productCatNumber = 7;
	public final static String[] productCatDesc = {"主厨推荐","热菜","时蔬","冷菜","汤/煲","主食","酒水/饮料"};
	
	public static void main(String[] str){
		System.out.print(bus.length);
	}
}
