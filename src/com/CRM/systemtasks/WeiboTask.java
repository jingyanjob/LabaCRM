package com.CRM.systemtasks;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import weibo4j.Comments;
import weibo4j.Friendships;
import weibo4j.Timeline;
import weibo4j.Users;
import weibo4j.Weibo;
import weibo4j.http.ImageItem;
import weibo4j.model.Comment;
import weibo4j.model.Paging;
import weibo4j.model.Status;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

public class WeiboTask {
	private Weibo weibo = null;
	private void init(String atoken){
		if(weibo == null){
			weibo = new Weibo();
			weibo.setToken(atoken);
		}
	}
	
	public User getUser(String atoken, String uid) throws WeiboException{
		init(atoken);
		User u = new User();
		Users us = new Users();
		u = us.showUserById(uid);
		return u;
	}
	public List<User> getFriends(String atoken, String uid){
		List<User> l = new ArrayList<User>();
		List<User> ls = new ArrayList<User>();
		init(atoken);
		Friendships fm = new Friendships();
		
		try {
			boolean isFinished = false;
			long turns = 0;
			int size = 200;
			
			while(!isFinished){
				l = fm.getFriendsByID(uid, size,  Long.valueOf(turns).intValue());
				if(l.size() > 1)
					ls.addAll(l);
				else
					isFinished = true;
				if(turns > com.CRM.GlobalStaticData.freeBUVIPUserNo){
					isFinished = true;
				}
				turns = turns + size;
			}
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return ls;
	}
	
	public List<User> getFollowUps(String atoken, String uid){
		List<User> l = new ArrayList<User>();
		List<User> ls = new ArrayList<User>();
		init(atoken);
		Friendships fm = new Friendships();
		try {
			boolean isFinished = false;
			long turns = 0;
			int size = 200;
			
			while(!isFinished){
				l = fm.getFollowersById(uid, size,  Long.valueOf(turns).intValue());
				if(l.size() > 1)
					ls.addAll(l);
				else
					isFinished = true;
				if(turns > com.CRM.GlobalStaticData.freeBUVIPUserNo){
					isFinished = true;
				}
				turns = turns + size;
			}
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return ls;
	}
	//getBilateral
	public List<User> getBilateral(String atoken, String uid, int size){
		List<User> l = new ArrayList<User>();
		init(atoken);
		Friendships fm = new Friendships();
		try {
			boolean isFinished = false;
			long page = 0;
			l = fm.getBilateral(uid, size,  Long.valueOf(page).intValue());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return l;
	}
	
	public List<Status> getRepostList(String atoken, long sinceId){
		/**	private int page = -1;              //页码。注意：最多返回200条分页内容。
    		private int count = -1;             //指定每页返回的记录条数。
    		private long sinceId = -1;          //若指定此参数，则只返回ID比since_id大（即比since_id发表时间晚的）的微博消息。
    		private long maxId = -1;            //若指定此参数，则返回ID小于或等于max_id的微博消息
		 */
		Paging page = new Paging();
	//	page.setSinceId(sinceId);
		
		Integer filter_by_author = 0;
		Integer filter_by_source = 0;
		Integer filter_by_type = 0;
		init(atoken);
		Timeline tm = new Timeline();
		List<Status> status = null;
		try {
			status = tm.getMentions(page, filter_by_author, filter_by_source, filter_by_type);
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return status;
	}
	public List<Comment> getCommentList(String atoken, long sinceId){
		/**	private int page = -1;              //页码。注意：最多返回200条分页内容。
			private int count = -1;             //指定每页返回的记录条数。
			private long sinceId = -1;          //若指定此参数，则只返回ID比since_id大（即比since_id发表时间晚的）的微博消息。
			private long maxId = -1;            //若指定此参数，则返回ID小于或等于max_id的微博消息
		 */
		Paging page = new Paging();
		page.setSinceId(sinceId);
		Integer filter_by_author = 0;
		Integer filter_by_source = 0;
		
		//Weibo weibo = new Weibo();
		//weibo.setToken(atoken);
		init(atoken);
		Comments com = new Comments();
		List<Comment> statusToMe = null;
		List<Comment> statusMentions = null;
		List<Comment> status = new ArrayList(); 
		try {
			statusToMe = com.getCommentToMe(page, filter_by_source, filter_by_author);
			statusMentions = com.getCommentMentions(page, filter_by_source, filter_by_author);
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		if(statusToMe != null){
			status.addAll(statusToMe);
		}
		if(statusMentions != null){
			status.addAll(statusMentions);
		}
		return status;
	}
	public void reservationConfirm(String atoken, String cid, String id, String comment) throws WeiboException, UnsupportedEncodingException{
		init(atoken);
		Comments com = new Comments();
		if(cid.equals(id)){
			com.createComment(comment, id);
		}else
			com.replyComment(cid, id, comment, 0, 1);//URLEncoder.encode( 
		
	}
	/**
	 * new wb, with picture
	 * @throws WeiboException 
	 * */
	public Status newWb(String atoken, String status, byte[] pic) throws WeiboException{
		init(atoken);
		Timeline tm = new Timeline();
		Status s = null;
		ImageItem item = new ImageItem("pic", pic);
		try {
			s = tm.UploadStatus(status, item);
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return s;
	}
}
