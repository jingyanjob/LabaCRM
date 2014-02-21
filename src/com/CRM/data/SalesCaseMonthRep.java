package com.CRM.data;

import java.sql.Timestamp;
import java.util.Date;

public class SalesCaseMonthRep {
	private int id;
	private String buid;//belongs to which biz user
	private int sourcesite; // 0: sina, 1: renren; 2: kaixin; 3: linkedin; 4: qq;
	private String yyyy;
	
	private String mm;
	private int scnumber;
	private int vipnumber;
	private int inactnumber;
	private int dealnumber;
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
	public int getSourcesite() {
		return sourcesite;
	}
	public void setSourcesite(int sourcesite) {
		this.sourcesite = sourcesite;
	}
	public String getYyyy() {
		return yyyy;
	}
	public void setYyyy(String yyyy) {
		this.yyyy = yyyy;
	}
	public String getMm() {
		return mm;
	}
	public void setMm(String mm) {
		this.mm = mm;
	}
	public int getScnumber() {
		return scnumber;
	}
	public void setScnumber(int scnumber) {
		this.scnumber = scnumber;
	}
	public int getVipnumber() {
		return vipnumber;
	}
	public void setVipnumber(int vipnumber) {
		this.vipnumber = vipnumber;
	}
	public int getInactnumber() {
		return inactnumber;
	}
	public void setInactnumber(int inactnumber) {
		this.inactnumber = inactnumber;
	}
	public int getDealnumber() {
		return dealnumber;
	}
	public void setDealnumber(int dealnumber) {
		this.dealnumber = dealnumber;
	}
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}	
	
}
