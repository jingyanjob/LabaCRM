package com.CRM.product;

import weibo4j.model.WeiboException;

import com.CRM.BizUserMgr;
import com.CRM.data.BizUser;
import com.CRM.data.Product;
import com.CRM.data.dao.ProductDao;
import com.CRM.systemtasks.WeiboTask;
import com.xp.cache.CacheFactory;

public class ProductEngine {
	//control all product
	ProductDao pd ;
	
	public ProductEngine() throws Exception{
		pd = com.config.DataApiInstance.instanceSProductDao();
	}
	//新产品
	public void newProduct(Product prod){
		pd.insert(prod);
	}
	//产品下架
	public void inactiveProduct(int id){
		pd.inactive(id);
	}
	//删除产品
	public void delete(String buid, int id){
		pd.delete(id);
		CacheFactory.popItem(buid, String.valueOf(id), 1);
	}
	//产品打折 标志切换
	public void discountSwitch(int id){
		pd.cutOffSwitch(id);
	}
	//获取产品列表
	public Product[] getActiveProducts(String buid){
		int number = 0;//35;
		Product[] prods = pd.getProducts(buid, number);
		if(prods == null){
			prods = new Product[1];
			prods[0] = new Product();
			prods[0].setProductname("您还没有任何菜品，应该马上动手制作自己的菜单啦");
		}
		return prods;
	}
	public Product getProduct(int id){
		return pd.getProduct(id);
	}
	//获取产品列表
	public Product[] getShowProducts(String bunames, int sourcesize) throws Exception {
		int number = 0;// 35;
		BizUserMgr bum = new BizUserMgr();
		BizUser bu = bum.getBizUserByName(bunames, sourcesize);
		Product[] prods = pd.getProducts(bu.getUid(), number);
		if(prods == null){
			prods = new Product[1];
			prods[0].setProductname("您还没有添加过产品，赶快添加并向客户展示您的产品啦 .");
		}
		return prods;
	}
	//
	public void productPublish(String atoken, String status, byte[] pic) throws WeiboException{
		WeiboTask wt = new WeiboTask();
		wt.newWb(atoken, status, pic);
		
	}
	
}