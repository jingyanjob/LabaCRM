package com.CRM.data;

import java.sql.Timestamp;
/**
 * employee of each BizUser, 
 * @author yjing2
 *
 */
public class BizEmpUser{
	
	private int id;
	private String uid;//for user banding
	private String buid;
	private String username;
	private int sourcesite; // 0: sina, 1: renren; 2: kaixin; 3: linkedin; 4: qq;
	private String rtoken; //request token
	private String rtokensecret; //request token secret
	private String atoken; //access token
	private String atokensecret; //access token secret
	private String location;
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
	private int province;                 //省份编码（参考省份编码表）
	private int city;                     //城市编码（参考城市编码表）
	private String expirein;
	private String repsinceid; // last update repost id
	private String cmssinceid; // last update comments id
	private Timestamp updatetime ; 
	
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
		return repsinceid;
	}
	public void setRepsinceid(String repsinceid) {
		this.repsinceid = repsinceid;
	}
	public String getCmssinceid() {
		return cmssinceid;
	}
	public void setCmssinceid(String cmssinceid) {
		this.cmssinceid = cmssinceid;
	}
	public String getBuid() {
		return buid;
	}
	public void setBuid(String buid) {
		this.buid = buid;
	}
}