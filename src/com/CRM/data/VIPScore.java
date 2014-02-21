package com.CRM.data;

import java.sql.Timestamp;
import java.util.Date;

public class VIPScore {
	private int id;
	private String uid;//for user banding
	private String buid;//belongs to which biz user
	private String username;
	private int sourcesite; // 0: sina, 1: renren; 2: kaixin; 3: linkedin; 4: qq;
	private int perscore;
	private int isplus; //0+ , 1-
	private int totalscore;
	private Timestamp updatetime ;
	private String weiboid;
	private Date weibotime ;
	
	public String getWeiboid() {
		return weiboid;
	}
	public void setWeiboid(String weiboid) {
		this.weiboid = weiboid;
	}
	public java.util.Date getWeibotime() {
		return weibotime;
	}
	public void setWeibotime(Date weibotime) {
		this.weibotime = weibotime;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getPerscore() {
		return perscore;
	}
	public void setPerscore(int perscore) {
		this.perscore = perscore;
	}
	public int getIsplus() {
		return isplus;
	}
	public void setIsplus(int isplus) {
		this.isplus = isplus;
	}
	public int getTotalscore() {
		return totalscore;
	}
	public void setTotalscore(int totalscore) {
		this.totalscore = totalscore;
	}
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBuid() {
		return buid;
	}
	public void setBuid(String buid) {
		this.buid = buid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getSourcesite() {
		return sourcesite;
	}
	public void setSourcesite(int sourcesite) {
		this.sourcesite = sourcesite;
	}
	
}
