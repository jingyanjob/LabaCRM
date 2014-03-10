package com.xp.cache;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import com.CRM.data.Product;
import com.CRM.data.SalesCase;
import com.CRM.data.VIPUser;

public class BUCacheData {
	private Hashtable<String, VIPUser> vht = new Hashtable<String, VIPUser> ();
	private Hashtable<String, Product> pht = new Hashtable<String, Product> ();
	private Hashtable<String, SalesCase> sht = new Hashtable<String, SalesCase> ();
	private VIPUser[] vips ;
	private Product[] products;
	private SalesCase[] salesCases;
	private String uid;
	public void popUp(int type, String id){
		switch(type){
			case 0:
				vht.remove(id);
			break;
			case 1:
				pht.remove(id);
			break;
			case 2:
				sht.remove(id);
			break;
		}
	}
	
	public Hashtable<String, VIPUser> getVht() {
		return vht;
	}
	public void setVht(Hashtable<String, VIPUser> vht) {
		this.vht = vht;
	}
	public Hashtable<String, Product> getPht() {
		return pht;
	}
	public void setPht(Hashtable<String, Product> pht) {
		this.pht = pht;
	}
	public Hashtable<String, SalesCase> getSht() {
		return sht;
	}
	public void setSht(Hashtable<String, SalesCase> sht) {
		this.sht = sht;
	}
	
	public VIPUser[] getVips() {
		vips = new VIPUser[vht.size()];
		Set<String> keySet = vht.keySet();
		Iterator<String> it = keySet.iterator();
		int i = 0;
		while(it.hasNext()){
			vips[i] = new VIPUser();
			String num = it.next();
			vips[i] = vht.get(num);
			i++;
		}
		return vips;
	}
	public void setVips(VIPUser[] vips) {
		this.vips = vips;
		vht.clear();
		for(int i=0; i< vips.length;i++){
			vht.put(String.valueOf(vips[i].getId()), vips[i]);
		}
	}
	public Product[] getProducts() {
		products = new Product[pht.size()];
		Set<String> keySet = pht.keySet();
		Iterator<String> it = keySet.iterator();
		int i = 0;
		while(it.hasNext()){
			products[i] = new Product();
			String num = it.next();
			products[i] = pht.get(num);
			System.out.println("categary - " + products[i].getCategory() + " desc " + products[i].getCategory() );
			i++;
		}
		return products;
	}
	public void setProducts(Product[] products) {
		this.products = products;
		pht.clear();
		for(int i=0; i< products.length;i++){
			System.out.println("categary - " + products[i].getCategory() + " desc " + products[i].getCategory() );
			pht.put(String.valueOf(products[i].getId()), products[i]);
		}
		this.products = products;
	}
	public SalesCase[] getSalesCases() {
		salesCases = new SalesCase[sht.size()];
		Set<String> keySet = sht.keySet();
		Iterator<String> it = keySet.iterator();
		int i = 0;
		while(it.hasNext()){
			salesCases[i] = new SalesCase();
			String num = it.next();
			salesCases[i] = sht.get(num);
			i++;
		}
		return salesCases;
	}
	public void setSalesCases(SalesCase[] salesCases) {
		this.salesCases = salesCases;
		sht.clear();
		for(int i=0; i< salesCases.length;i++){
			sht.put(String.valueOf(salesCases[i].getId()), salesCases[i]);
		}
		this.salesCases = salesCases;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public BUCacheData(String uid, int n){
		this.uid = uid;
	}
	
}
