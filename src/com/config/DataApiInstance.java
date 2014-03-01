package com.config;


//import com.chantrans.data.TagsDao;
import com.CRM.data.dao.BizUserDao;
import com.CRM.data.dao.ProductDao;
import com.CRM.data.dao.ReservationDao;
import com.CRM.data.dao.SalesCaseDao;
import com.CRM.data.dao.SalesCaseMRepDao;
import com.CRM.data.dao.ScoreRoleDao;
import com.CRM.data.dao.TuanUseDao;
import com.CRM.data.dao.VIPScoreDao;
import com.CRM.data.dao.VIPUserDao;
import org.springframework.context.ApplicationContext;

/**
 * @uml.dependency supplier="com.xp.data.SpringHibernateBean"
 * @uml.dependency supplier="com.xp.data.UserDao"
 */
public class DataApiInstance {

	private static ApplicationContext context = null;

	private static void intiDAI() {
		if (context == null) {
			context = SpringHibernateBean.getContext();
		}
	}
	public static synchronized BizUserDao instanceBizUserDao() throws Exception{
		intiDAI();
		return (BizUserDao) context.getBean(StringLib.crmBizUserDao);
	}
	public static synchronized VIPUserDao instanceVIPUserDao() throws Exception{
		intiDAI();
		return (VIPUserDao) context.getBean(StringLib.crmVIPUserDao);
	}
	public static synchronized VIPScoreDao instanceBizVIPScoreDao() throws Exception{
		intiDAI();
		return (VIPScoreDao) context.getBean(StringLib.crmVIPScoreDao);
	}
	public static synchronized ScoreRoleDao instanceScoreRoleDao () throws Exception{
		intiDAI();
		return (ScoreRoleDao ) context.getBean(StringLib.crmScoreRoleDao );
	}
	public static synchronized ReservationDao instanceReservationDao () throws Exception{
		intiDAI();
		return (ReservationDao ) context.getBean(StringLib.crmReservationDao);
	}
	public static synchronized SalesCaseDao instanceSalesCaseDao () throws Exception{
		intiDAI();
		return (SalesCaseDao ) context.getBean(StringLib.crmSalesCaseDao);
	}
	public static synchronized SalesCaseMRepDao instanceSalesCaseMRepDao () throws Exception{
		intiDAI();
		return (SalesCaseMRepDao ) context.getBean(StringLib.crmSalesCaseMRepDao);
	}
	public static synchronized ProductDao instanceSProductDao () throws Exception{
		intiDAI();
		return (ProductDao) context.getBean(StringLib.crmProductDao);
	}
	public static synchronized TuanUseDao instanceTuanUserDao () throws Exception{
		intiDAI();
		return (TuanUseDao) context.getBean(StringLib.crmTuanuserDao);
	}
}
