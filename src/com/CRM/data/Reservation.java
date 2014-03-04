package com.CRM.data;

import java.sql.Timestamp;

import com.CRM.systemtasks.RepostAndComment;

public class Reservation{
	private int sid;//序列号，自增
	private String id;//微博ID
	private String cid;//评论ID
	private String buid;//belongs to which biz user
	private String uid;//for user banding
	private String username;
	private int resstatus;	//0: success; 1: failed; 2: pendding
	private String resresp;//reservation response
	private String text;
	private java.util.Date createdat;
	private Timestamp updatetime;

	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBuid() {
		return buid;
	}
	public void setBuid(String buid) {
		this.buid = buid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getResstatus() {
		return resstatus;
	}
	public void setResstatus(int resstatus) {
		this.resstatus = resstatus;
	}
	public String getResresp() {
		return resresp;
	}
	public void setResresp(String resresp) {
		this.resresp = resresp;
	}
	public java.util.Date getCreatedat() {
		return createdat;
	}
	public void setCreatedat(java.util.Date createdat) {
		this.createdat = createdat;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
}