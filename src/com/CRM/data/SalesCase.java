package com.CRM.data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import weibo4j.model.Status;
import weibo4j.model.User;

public class SalesCase{
	private int id;
	private String buid;//belongs to which biz user
	private String beuid;//which biz user employee operate
	private String username;
	private int sourcesite; // 0: sina, 1: renren; 2: kaixin; 3: linkedin; 4: qq;
	private String content;
	private String contentshort;//not in db
	private String productids; ////slipt by GlobalStaticData.spliter
	private List products = null; //for ui show, not put into DB
	private String imgurl;
	private int status;//0: new and not met deadline; 1 finished; 2 regular
	private String vipuidlist;//slipt by GlobalStaticData.spliter
	private String vipunamelist;//slipt by GlobalStaticData.spliter
	private String disprice;
	private String oriprice;
	private String tuancode;
	private Timestamp casestart;
	private Timestamp caseend;
	private String caseendshort;
	public String getCaseendshort() {
		caseendshort = caseend.toString().split(" ")[0];
		return caseendshort;
	}

	private Timestamp updatetime ;
	
	private VIPUser[] vus;//not store in db

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

	public String getBeuid() {
		return beuid;
	}

	public void setBeuid(String beuid) {
		this.beuid = beuid;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentshort() {
		return contentshort;
	}

	public void setContentshort(String contentshort) {
		this.contentshort = contentshort;
	}

	public String getProductids() {
		return productids;
	}

	public void setProductids(String productids) {
		this.productids = productids;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getVipuidlist() {
		return vipuidlist;
	}

	public void setVipuidlist(String vipuidlist) {
		this.vipuidlist = vipuidlist;
	}
	public List getProducts() {
		if(products == null){
			products = new ArrayList();
		}
		return products;
	}

	public void setProducts(List products) {
		this.products = products;
	}
	public String getVipunamelist() {
		return vipunamelist;
	}

	public void setVipunamelist(String vipunamelist) {
		this.vipunamelist = vipunamelist;
	}

	public String getDisprice() {
		return disprice;
	}

	public void setDisprice(String disprice) {
		this.disprice = disprice;
	}

	public String getOriprice() {
		return oriprice;
	}

	public void setOriprice(String oriprice) {
		this.oriprice = oriprice;
	}

	public Timestamp getCasestart() {
		return casestart;
	}

	public void setCasestart(Timestamp casestart) {
		this.casestart = casestart;
	}
	public String getTuancode() {
		return tuancode;
	}

	public void setTuancode(String tuancode) {
		this.tuancode = tuancode;
	}
	public Timestamp getCaseend() {
		return caseend;
	}

	public void setCaseend(Timestamp caseend) {
		this.caseend = caseend;
	}

	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	public VIPUser[] getVus() {
		return vus;
	}

	public void setVus(VIPUser[] vus) {
		this.vus = vus;
	}
	
	
}