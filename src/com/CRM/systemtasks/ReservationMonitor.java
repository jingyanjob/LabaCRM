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
		String newCmssinceid = bu.getCmssinceid();
		String newCmsamsinceid = bu.getCmsamsinceid();
		String newRepsinceid = bu.getRepsinceid();
		List<Comment> coms = wt.getCommentList(atoken, Long.parseLong(bu.getCmssinceid()));
		List<Comment> comsam = wt.getCommentAMList(atoken, Long.parseLong(bu.getCmsamsinceid()));
		List<Status> reposts = wt.getRepostList(atoken, Long.parseLong(bu.getRepsinceid()));
		List<Comment> comsf = new ArrayList();
		int s = GlobalStaticData.getDefaultSR().getPercommentscore();
		VIPScore vs= new VIPScore();
		//直接评论
		if(coms != null){
			for(int i=0; i<coms.size();i++){
				//筛选预定列表
				String _s = coms.get(i).getText().toString();
				String _text = _s.split("//@")[0];
				if((_text.indexOf("预定") != -1 || _text.indexOf("预订") != -1 || _text.indexOf("yuding") != -1 || _text.indexOf("yu ding") != -1)
						&& _s.indexOf("#预订成功#") <0 ){
					comsf.add(coms.get(i));
				}
				//评论赠送积分
				Comment jo = (Comment)coms.get(i);
				vs = new VIPScore();
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
		}
		//@我 的评论
		if(comsam != null){
			for(int i=0; i<comsam.size();i++){
				//筛选预定列表
				String _s = comsam.get(i).getText().toString();
				String _text = _s.split("//@")[0];
				if((_text.indexOf("预定") != -1 || _text.indexOf("预订") != -1 || _text.indexOf("yuding") != -1 || _text.indexOf("yu ding") != -1)
						&& _s.indexOf("#预订成功#") <0 ){
					comsf.add(comsam.get(i));
				}
				//评论赠送积分
				Comment jo = (Comment)comsam.get(i);
				vs = new VIPScore();
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
				newCmsamsinceid = String.valueOf(comsam.get(i).getId());
			}
		}
		
		//@我的
		List<Status> repostsf = new ArrayList();
		if(reposts != null){
			s = GlobalStaticData.getDefaultSR().getPerrepostscore();
			for(int i=0; i<reposts.size();i++){
				//筛选预定列表
				String _s = reposts.get(i).getText().toString();
				String _text = _s.split("//@")[0];
				if((_text.indexOf("预定") != -1 || _text.indexOf("预订") != -1 || _text.indexOf("yuding") != -1|| _text.indexOf("yu ding") != -1)
						&& _s.indexOf("#预订成功#") <0){
					repostsf.add(reposts.get(i));
				}
				//转发赠送积分
				Status jo = (Status)reposts.get(i);
				vs = new VIPScore();
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
		}
		RepostAndComment[] ss = new RepostAndComment[(comsf.size() + repostsf.size())] ;
		RepostAndComment[] cmsf = new RepostAndComment[comsf.size()] ;
		RepostAndComment[] reps = new RepostAndComment[repostsf.size()] ;
		int i = 0, j = 0;//, k = 0;
		if(comsf != null){
			for (i = 0; i < comsf.size(); i++) {
				cmsf[i] = parseRepostAndComment(comsf.get(i), null, bu);
			}
		}
		if(repostsf != null){
			for ( j = 0; j < repostsf.size(); j++) {
				reps[j] = parseRepostAndComment(null, repostsf.get(j), bu);
			}
		}
		if(comsf.size() >0 || repostsf.size()>0){
			int n = 0,  m = 0;
			for(; n < cmsf.length; n++){
				ss[n] = cmsf[n];
			}
			m = 0;
			for(; m < reps.length; m++){
				ss[n+m] = reps[m];
			}
		}
		if(i == 0)
			return null;
		else{
			bu.setCmssinceid(newCmssinceid);
			bu.setCmsamsinceid(newCmsamsinceid);
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
			rac.setText(com.getText().split("//@")[0].replaceAll("@"+busername,""));
			rac.setUser(com.getUser());
			rac.setId(String.valueOf(com.getStatus().getId()));
			rac.setCid(String.valueOf(com.getId()));
		}
		if(status != null){
			rac.setCreatedAt(status.getCreatedAt());
			rac.setText(status.getText().split("//@")[0].replaceAll("@"+busername,""));
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
	//sort all status by time desc 
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
	    WeiboTask wt = new WeiboTask();
		
//		int i = 0;
//		for(; i<5; i++){
//			System.out.println( "- "+i);
//		}
		//System.out.println(i);
//		ReservationMonitor rm = new ReservationMonitor();
		String atoken = "2.0058jF6B0Cd61ee8b59f5822sFLouB";
		List list = new ArrayList();
		long sinceid = 1000;
		BizUser bu = new BizUser();
		bu.setCmsamsinceid("1");
		bu.setCmssinceid("1");
		bu.setRepsinceid("1");
		bu.setAtoken(atoken);
		bu.setUid("1286907550");
		List<Comment> coms = wt.getCommentList(atoken, Long.parseLong(bu.getCmssinceid()));
		List<Comment> comsam = wt.getCommentAMList(atoken, Long.parseLong(bu.getCmsamsinceid()));
		List<Status> reposts = wt.getRepostList(atoken, Long.parseLong(bu.getRepsinceid()));
		
		for(int i=0; i<coms.size();i++){
			list.add(coms.get(i).getText() + "  00000-coms " + coms.get(i).getId() + " - "+ coms.get(i).getStatus().getId());
			//System.out.println(coms.get(i).getText());
		}
		for(int i=0; i<comsam.size();i++){
			list.add(comsam.get(i).getText() + "  11111comsam " + comsam.get(i).getId() + " - "+ comsam.get(i).getStatus().getId());
			//System.out.println(comsam.get(i).getText());
		}
		for(int i=0; i<reposts.size();i++){
			list.add(reposts.get(i).getText()+ "  22222--reps " + reposts.get(i).getId());
			//System.out.println(reposts.get(i).getText());
		}
		System.out.println("total: " + list.size());
		int n = 0;
		for(int i=0; i<list.size();i++){
			String _text1 = list.get(i).toString();
			String _text = _text1.split("//@")[0];
			if((_text.indexOf("预定") != -1 || _text.indexOf("预订") != -1 || _text.indexOf("yuding") != -1)
					&& _text1.indexOf("#预订成功#") <0){
				System.out.println(_text1 + " " );
				n++;
			}
			
		}
		System.out.println("total: " + n);
		
	}
}
