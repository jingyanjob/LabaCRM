package com.CRM.data;

import java.sql.Timestamp;
import java.util.Date;

import weibo4j.model.Status;
import weibo4j.model.User;

public class Product{
	private int id;
	private String buid;//belongs to which biz user
	private int category;// product category ， by: GlobalStaticData.productCatNumber
	private String productname;
	private String productdesc;
	private String imgurl;
	private String price;
	private int active;//0 yes, 1 no
	private String incutoff; 
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
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProductdesc() {
		return productdesc;
	}
	public void setProductdesc(String productdesc) {
		this.productdesc = productdesc;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getIncutoff() {
		return incutoff;
	}
	public void setIncutoff(String incutoff) {
		this.incutoff = incutoff;
	}
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
}