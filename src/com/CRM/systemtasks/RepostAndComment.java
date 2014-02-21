package com.CRM.systemtasks;

import java.util.Date;

import weibo4j.model.Source;
import weibo4j.model.Status;
import weibo4j.model.User;

public class RepostAndComment {
	private long idstr;                                  //保留字段，请勿使用                     
	private Date createdAt;                              //status创建时间
	private String id;                                   //需要评论的微博ID。
	private String cid;									//需要回复的评论ID。
	private String text;                                 //微博内容
	private Source source;                               //微博来源
	private boolean favorited;                           //是否已收藏
	private long inReplyToStatusId;                      //回复ID
	private long inReplyToUserId;                        //回复人ID
	private String inReplyToScreenName;                  //回复人昵称
	private String thumbnailPic;                        //微博内容中的图片的缩略地址
	private String bmiddlePic;                          //中型图片
	private String originalPic;                         //原始图片
	private Status retweetedStatus = null;              //转发的博文，内容为status，如果不是转发，则没有此字段
	private String geo;                                  //地理信息，保存经纬度，没有时不返回此字段
	private double latitude = -1;                        //纬度
	private double longitude = -1;                       //经度
	private int repostsCount;                           //转发数
	private int commentsCount;                          //评论数
	private String mid;                                  //微博MID
	private String annotations;                          //元数据，没有时不返回此字段
	private String reposts;
	private String statuses;
	private User user = null;                            //作者信息
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public long getIdstr() {
		return idstr;
	}
	public void setIdstr(long idstr) {
		this.idstr = idstr;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Source getSource() {
		return source;
	}
	public void setSource(Source source) {
		this.source = source;
	}
	public boolean isFavorited() {
		return favorited;
	}
	public void setFavorited(boolean favorited) {
		this.favorited = favorited;
	}
	public long getInReplyToStatusId() {
		return inReplyToStatusId;
	}
	public void setInReplyToStatusId(long inReplyToStatusId) {
		this.inReplyToStatusId = inReplyToStatusId;
	}
	public long getInReplyToUserId() {
		return inReplyToUserId;
	}
	public void setInReplyToUserId(long inReplyToUserId) {
		this.inReplyToUserId = inReplyToUserId;
	}
	public String getInReplyToScreenName() {
		return inReplyToScreenName;
	}
	public void setInReplyToScreenName(String inReplyToScreenName) {
		this.inReplyToScreenName = inReplyToScreenName;
	}
	public String getThumbnailPic() {
		return thumbnailPic;
	}
	public void setThumbnailPic(String thumbnailPic) {
		this.thumbnailPic = thumbnailPic;
	}
	public String getBmiddlePic() {
		return bmiddlePic;
	}
	public void setBmiddlePic(String bmiddlePic) {
		this.bmiddlePic = bmiddlePic;
	}
	public String getOriginalPic() {
		return originalPic;
	}
	public void setOriginalPic(String originalPic) {
		this.originalPic = originalPic;
	}
	public Status getRetweetedStatus() {
		return retweetedStatus;
	}
	public void setRetweetedStatus(Status retweetedStatus) {
		this.retweetedStatus = retweetedStatus;
	}
	public String getGeo() {
		return geo;
	}
	public void setGeo(String geo) {
		this.geo = geo;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getRepostsCount() {
		return repostsCount;
	}
	public void setRepostsCount(int repostsCount) {
		this.repostsCount = repostsCount;
	}
	public int getCommentsCount() {
		return commentsCount;
	}
	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getAnnotations() {
		return annotations;
	}
	public void setAnnotations(String annotations) {
		this.annotations = annotations;
	}
	public String getReposts() {
		return reposts;
	}
	public void setReposts(String reposts) {
		this.reposts = reposts;
	}
	public String getStatuses() {
		return statuses;
	}
	public void setStatuses(String statuses) {
		this.statuses = statuses;
	}
}
