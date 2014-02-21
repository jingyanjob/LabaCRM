package com.xp.cache;

import java.util.Hashtable;

public class BaseCache {
	protected BaseCache(int n){
		this.setNumber(n);
	}
	protected BaseCache(String buid, int n){
		this.setCacheId(buid);
		this.setNumber(n);
	}
	private Hashtable itemCache = new Hashtable();
	private String[] keyset;
	private String cacheId = "id";
	private int number ;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getCacheId() {
		return cacheId;
	}
	private void setCacheId(String cacheId) {
		this.cacheId = cacheId;
	}
	public String[] getKeyset() {
		return keyset;
	}
	public void setKeyset(String keys, int n) {
		this.keyset = new String[n];
	}
	public Hashtable getItemCache(){
		return this.itemCache;
	}
	public void setItemCache(Hashtable tab){
		this.itemCache = tab;
	}
	public void pushItem(String key, Object bis){
		this.popItem(key);
		this.getItemCache().put(key, bis);
	}
	
	public boolean isItemInCache(String key){
		return this.getItemCache().containsKey(key);
	}
	
	public void popItem(String key){
		if(this.isItemInCache(key)){
			this.getItemCache().remove(key);
		}
	}
	
}
