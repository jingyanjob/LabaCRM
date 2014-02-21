package com.CRM.systemtasks;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import com.CRM.BizUserMgr;
import com.CRM.GlobalStaticData;
import com.CRM.VIPUserMgr;
import com.CRM.data.BizUser;
import com.CRM.data.VIPScore;

import weibo4j.model.Comment;
import weibo4j.model.Source;
import weibo4j.model.Status;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

public class ReservationMonitor {
	private WeiboTask wt = new WeiboTask();

	public RepostAndComment[] getReservationList(String atoken, BizUser bu) throws Exception {
		VIPUserMgr vipmgr = new VIPUserMgr();
		BizUserMgr bum = new BizUserMgr();
		List<Comment> coms = wt.getCommentList(atoken, Long.parseLong(bu.getCmssinceid()));
		List<Comment> comsf = new ArrayList();
		String newCmssinceid = bu.getCmssinceid();
		String newRepsinceid = bu.getRepsinceid();
		int s = GlobalStaticData.getDefaultSR().getPercommentscore();
		VIPScore vs= new VIPScore();
		
		int size = 0;
		if(coms != null){
			for(int i=0; i<coms.size();i++){
				//筛选预定列表
				String _text = coms.get(i).getText().split("//@")[0];
				if((_text.indexOf("预定") != -1 || _text.indexOf("预订") != -1 )
						&& coms.get(i).getText().indexOf("#预订成功#") <0
					){
					comsf.add(coms.get(i));
				}
				//评论赠送积分
				Comment jo = (Comment)coms.get(i);
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
				newCmssinceid = String.valueOf(coms.get(i).getId());
			}
			size = size + comsf.size();
		}
		List<Status> reposts = wt.getRepostList(atoken, Long.parseLong(bu.getRepsinceid()));
		
		List<Status> repostsf = new ArrayList();
		if(reposts != null){
			s = GlobalStaticData.getDefaultSR().getPerrepostscore();
			for(int i=0; i<reposts.size();i++){
				//筛选预定列表
				String _text = reposts.get(i).getText().split("//@")[0];
				if((_text.indexOf("预定") != -1 || _text.indexOf("预订") != -1 )
						&& reposts.get(i).getText().indexOf("#预订成功#") <0
					){
					repostsf.add(reposts.get(i));
				}
				//转发赠送积分
				Status jo = (Status)reposts.get(i);
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
				newRepsinceid = jo.getId();
			}
			size = size + repostsf.size();
		}
		RepostAndComment[] ss = new RepostAndComment[size] ;// + reposts.size()coms.size()
		int i = 0, j = 0;
		if(coms != null){
			for (i = 0; i < comsf.size(); i++) {
				ss[i] = parseRepostAndComment(comsf.get(i), null, bu);
			}
		}
		j = i;
		if(reposts != null){
			for (; j < ss.length; j++) {
				ss[j] = parseRepostAndComment(null, repostsf.get(j-i), bu);
			}
		}
		
		if(size == 0)
			return null;
		else{
			bu.setCmssinceid(newCmssinceid);
			bu.setRepsinceid(newRepsinceid);
			bum.updateBizUser(bu);
			return this.sortRL(ss);
		}
			
	}
	
	public RepostAndComment parseRepostAndComment(Comment com, Status status, BizUser bu){
		RepostAndComment rac = new RepostAndComment();
		String busername = bu.getUsername();
		if(com != null){
			rac.setCreatedAt(com.getCreatedAt());
			rac.setText(com.getText().split("//@")[0].replaceAll(busername,""));
			rac.setUser(com.getUser());
			rac.setId(String.valueOf(com.getStatus().getId()));//评论的微博ID
			rac.setCid(String.valueOf(com.getId()));
		}else if(status != null){
			rac.setCreatedAt(status.getCreatedAt());
			rac.setText(status.getText().split("//@")[0].replaceAll(busername,""));
			rac.setUser(status.getUser());
			rac.setId(status.getId());
			rac.setCid(status.getId());
		}
		return rac;
	}

	public RepostAndComment[] sortRL(RepostAndComment[] ss) {
		return compareLoop(ss);
		//return s;
	}
	//sort all status by tiem desc 
	public RepostAndComment[] compareLoop(RepostAndComment[] ss){
		int n = ss.length;
		RepostAndComment temp = new RepostAndComment();
		for(int i=n-1; i>=0; i--){
			int j = i -1;
			temp = ss[i];
			for(; j>=0; j--){
				if(ss[j].getCreatedAt().compareTo(temp.getCreatedAt())<0){
					ss[i] = ss[j];
					ss[j] = temp;
					temp = ss[i];
				}
			}
		}
		return ss;
	}
	

	public static void main(String[] str) throws Exception {
		ReservationMonitor rm = new ReservationMonitor();
		String atoken = "2.0058jF6B00rTAH89731329c0YCBP2B";
		long sinceid = 1000;
		BizUser bu = null;
//		WeiboTask wt = new WeiboTask();
//		System.out.println(wt.getUser(atoken, "1286907550").getName());
		RepostAndComment[] ss = rm.getReservationList(atoken, bu);
		System.out.println("----------共有   "+ss.length +"  条记录");
		for(int i=0; i< ss.length ; i++){
			System.out.println(ss[i].getCreatedAt() + "  "
					+ ss[i].getUser().getName()
					+ " "
					+ ss[i].getText()
					+ " "
					+ ss[i].getId()
					);//+ ss[i].get
		}

	}
}
