package com.xp.cache;

import java.util.Hashtable;

import com.CRM.BizUserMgr;
import com.CRM.ReservationMgr;
import com.CRM.VIPUserMgr;
import com.CRM.product.ProductEngine;
import com.CRM.sales.SalesEngine;

public class CacheFactory {
	private static Hashtable<String, BUCacheData> caches = new Hashtable();
	private static int max = 100;// cache的数量, 每个BU登陆后 建立一个cache， 存储vip list, salescase list, product list
	private static int currentn = 0;
	private static BizUserMgr bum = null;
	private static VIPUserMgr vum = null;
	private static ReservationMgr rvm = null;
	private static SalesEngine se = null;
	private static ProductEngine pe = null;
	private static BUCacheData bc;
	public static boolean isInit = true;
	private static void init(){
		try {
			if(bum == null)
				bum = new BizUserMgr();
			if(vum == null)
				vum = new VIPUserMgr();
			if(rvm == null)
				rvm = new ReservationMgr();
			if(se == null)
				se = new SalesEngine();
			if(pe == null)
				pe = new ProductEngine();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//type: 0 vip, 1 product, 2 salescase
	public static void popItem(String buid, String id, int type) {
		if(caches.containsKey(buid)){
			caches.get(buid).popUp(type, id);
			System.out.println("========================== pupup + " + id);
		}
	}
	public synchronized static BUCacheData getCache(String buid) {
		init();
		System.out.println("=========start cache========= + " + buid);
		if(caches.containsKey(buid)){
			System.out.println("==========get cache================ + " + buid);
			return (BUCacheData)caches.get(buid);
		}else{
			System.out.println("============init cache============== + " + buid);
			BUCacheData bc = new BUCacheData(buid, currentn);
			bc.setVips(vum.getVUsers(buid));
			bc.setProducts(pe.getActiveProducts(buid));
			bc.setSalesCases(se.getSalesCaseList(buid));
			caches.put(buid, bc);
			isInit = false;
			return bc;
		}
	}
}
