package com.xp.util;

public class ItemFilter {
	private char itemer;//f: from me;   t: to me
	private String sTime = "";
	private String eTime = "";
	private int recordFrom;
	private int recordTo;
	private int recordNums;
	private String op;
	private char order = 'a';
	public char getItemer() {
		return itemer;
	}
	public void setItemer(char itemer) {
		this.itemer = itemer;
	}
	public String getSTime() {
		return sTime;
	}
	public void setSTime(String time) {
		sTime = time;
	}
	public String getETime() {
		return eTime;
	}
	public void setETime(String time) {
		eTime = time;
	}
	public int getRecordFrom() {
		return recordFrom;
	}
	public void setRecordFrom(int recordFrom) {
		this.recordFrom = recordFrom;
	}
	public int getRecordTo() {
		return recordTo;
	}
	public void setRecordTo(int recordTo) {
		this.recordTo = recordTo;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public int getRecordNums() {
		return recordNums;
	}
	public void setRecordNums(int recordNums) {
		this.recordNums = recordNums;
	}
	public char getOrder() {
		return order;
	}
	public void setOrder(char order) {
		this.order = order;
	}
	
}