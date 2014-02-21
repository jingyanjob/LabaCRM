package com.CRM.sales;

import com.CRM.data.dao.SalesCaseDao;

public class Reports {
	private SalesCaseDao scd = null;
	public Reports() throws Exception{
		scd = com.config.DataApiInstance.instanceSalesCaseDao();
		
	}
	
	public void historyReportInit(){
		
	}
	public void getMonthReport(){
		
	}
	
}
