package com.CRM.sales;

import com.CRM.GlobalStaticData;
import com.CRM.VIPUserMgr;
import com.CRM.data.BizUser;
import com.CRM.data.SalesCase;
import com.CRM.data.TuanUse;
import com.CRM.data.VIPUser;
import com.CRM.data.dao.SalesCaseDao;
import com.CRM.data.dao.TuanUseDao;
import com.xp.cache.CacheFactory;

public class SalesEngine {
	public static int[] g_FindType = {0,1,2};//0:by location; 1 by....
	public static int defaultSize = 50;
	private SalesCaseDao scd;
	private TuanUseDao tud;
	
	public SalesEngine() throws Exception{
		scd = com.config.DataApiInstance.instanceSalesCaseDao();
		tud = com.config.DataApiInstance.instanceTuanUserDao();
	}
	
	
	public void newSalesCase(SalesCase sc, String at){
		scd.insert(sc);
		
	}
	void scToVip(String idList, String wbId){
		String[] ids = idList.split(GlobalStaticData.spliter);
		
	}
	/**
	 * keyword: 搜索字段值
	 * findtype:搜索字段
	 */
	public SalesCase tempSalesCase(int findtype, String keyword, BizUser bu) throws Exception{
		VIPUserMgr vum = new VIPUserMgr();
		VIPUser[] vus = {};
		if(findtype == g_FindType[0]){
			vus = vum.getVUsersByLocation(keyword, bu,defaultSize);
		}
		SalesCase sc = new SalesCase();
		sc.setVus(vus);
		sc.setBuid(bu.getUid());
		sc.setStatus(0);
		StringBuffer idsb = new StringBuffer();
		StringBuffer namesb = new StringBuffer();
		for(int i=0; i<vus.length;i++){
			if(i<(vus.length-1)){
				idsb.append(vus[i].getUid()+",");
				namesb.append(vus[i].getUsername()+",");
			}else{
				idsb.append(vus[i].getUid());
				namesb.append(vus[i].getUsername());
			}
		}
		
		sc.setVipuidlist(idsb.toString());
		sc.setVipunamelist(namesb.toString());
		return sc;
	}
	public void delete(String buid,String id){
		scd.delete(id, 0);
		CacheFactory.popItem(buid, id, 2);
	}
	public SalesCase[] getSalesCaseList(String buid){
		/**
		 * free use only can show 10 latest test case
		 */
		int number = 0;
		SalesCase[] scs = scd.getSalesCases(buid, 1, number);
		if(scs == null){
			/*
			 <a href="javascript:showSCDetail('<c:out value="${sc.id}"/>',
			 '<c:out value="${sc.vipunamelist}"/>','<c:out value="${sc.content}"/>','<c:out value="${sc.imgurl}"/>')">
				时间：<c:out value="${sc.updatetime}"></c:out>
				内容预览：<c:out value="${sc.contentshort}"></c:out>
			 * */
			scs = new SalesCase[1];
			scs[0] =  new SalesCase();
			scs[0].setId(0);
			scs[0].setContentshort("您还没有发起过营销活动，赶快新建一个营销方案并进行微推广啦.");
			scs[0].setVipunamelist("");
			scs[0].setContent("您还没有发起过营销活动，赶快新建一个营销方案并进行微推广啦.");
			scs[0].setImgurl("");//			
		}else{
			for(int i=0; i<scs.length;i++){
				if(scs[i].getContent().length()>16){
					String temp = scs[i].getContent().substring(0, 16) + "...";
					scs[i].setContentshort(temp);
				}else{
					scs[i].setContentshort(scs[i].getContent());
				}
			}
		}
		return scs;
	}
	
	public void tuanUse(String buid, String tuancode, String tuanuser){
		TuanUse tu= new TuanUse();
		tu.setBuid(buid);
		tu.setTuancode(tuancode);
		tu.setTuanuser(tuanuser);
		tud.insert(tu);		
	}
	
	public void salesCaseStatus(int caseId){
		
	}
	
	
	
}
