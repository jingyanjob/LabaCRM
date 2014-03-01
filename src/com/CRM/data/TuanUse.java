package com.CRM.data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import weibo4j.model.Status;
import weibo4j.model.User;

public class TuanUse{
	private int id;
	private String buid;//belongs to which biz user
	private String tuancode;
	private String tuanuser;
	private Timestamp updatetime ;
	
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
	public String getTuancode() {
		return tuancode;
	}
	public void setTuancode(String tuancode) {
		this.tuancode = tuancode;
	}
	public String getTuanuser() {
		return tuanuser;
	}
	public void setTuanuser(String tuanuser) {
		this.tuanuser = tuanuser;
	}
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
}