package com.CRM.data;

import java.sql.Timestamp;
/**
 * BizUser is the client official user
 * @author yjing2
 *
 */
public class BizUser{
	private int id;
	private String uid;//for user banding
	private String username;
	private int sourcesite; // 0: sina, 1: renren; 2: kaixin; 3: linkedin; 4: qq;
	private String rtoken; //request token
	private String rtokensecret; //request token secret
	private String atoken; //access token
	private String atokensecret; //access token secret
	private String location;
	private int province;                 //省份编码（参考省份编码表）
	private int city;                     //城市编码（参考城市编码表）
	private String expirein;
	private String repsinceid; // last update repost id
	private String cmssinceid; // last update comments id， 当前登录用户所接收到的评论
	private String cmsamsinceid; // last update comments id， 最新的提到当前登录用户的评论，即@我的评论   
	private Timestamp updatetime ; 
	
	public String getCmsamsinceid() {
		return cmsamsinceid;
	}
	public void setCmsamsinceid(String cmsamsinceid) {
		this.cmsamsinceid = cmsamsinceid;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getProvince() {
		return province;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public int getCity() {
		return city;
	}
	public void setCity(int city) {
		this.city = city;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getAtoken() {
		return atoken;
	}
	public void setAtoken(String atoken) {
		this.atoken = atoken;
	}
	public String getAtokensecret() {
		return atokensecret;
	}
	public void setAtokensecret(String atokensecret) {
		this.atokensecret = atokensecret;
	}
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
	public String getRtoken() {
		return rtoken;
	}
	public void setRtoken(String rtoken) {
		this.rtoken = rtoken;
	}
	public String getRtokensecret() {
		return rtokensecret;
	}
	public void setRtokensecret(String rtokensecret) {
		this.rtokensecret = rtokensecret;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getExpirein() {
		return expirein;
	}
	public void setExpirein(String expirein) {
		this.expirein = expirein;
	}
	public String getRepsinceid() {
		repsinceid.replaceAll("-", "");
		return repsinceid;
	}
	public void setRepsinceid(String repsinceid) {
		this.repsinceid = repsinceid;
	}
	public String getCmssinceid() {
		cmssinceid.replaceAll("-", "");
		return cmssinceid;
	}
	public void setCmssinceid(String cmssinceid) {
		this.cmssinceid = cmssinceid;
	}
}