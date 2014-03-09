package com.CRM;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
import com.CRM.data.BizUser;
import com.CRM.data.Product;
import com.CRM.data.Reservation;
import com.CRM.data.SalesCase;
import com.CRM.data.ScoreRole;
import com.CRM.data.VIPScore;
import com.CRM.data.VIPUser;
import com.CRM.product.ProductEngine;
import com.CRM.sales.SalesEngine;
import com.CRM.systemtasks.BUInitTask;
import com.CRM.systemtasks.RepostAndComment;
import com.CRM.systemtasks.ReservationMonitor;
import com.sina.sae.storage.SaeStorage;
import com.xp.cache.CacheFactory;
import com.xp.util.XPUtil;
public class UICtrl extends MultiActionController implements java.io.Serializable {
	public static String jason_lbig = "{\"";
	public static String jason_rbig0 = "\"}";
	public static String jason_rbig1 = "]}";
	public static String jason_lmid = "\":[";
	public static String jason_rmid = "]";
	public static String jason_split0 = "\":\"";
	public static String jason_split1 = "\",\"";
	public static String jason_split2 = "\"}, { \"";
	
	private BizUserMgr bum = null;
	private VIPUserMgr vum = null;
	private ReservationMgr rvm = null;
	private SalesEngine se = null;
	private ProductEngine pe = null;
	private void init(){
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
	
	private XPUtil util = new XPUtil();
	public ModelAndView testUI(HttpServletRequest req,
			HttpServletResponse res) throws UnsupportedEncodingException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		ModelAndView ma = new ModelAndView("CRM/mainframe");
		return ma;
	}
	public ModelAndView confirmReservation(HttpServletRequest req,
			HttpServletResponse res) throws UnsupportedEncodingException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		ModelAndView ma = new ModelAndView("CRM/actionpage/_reservationstatu");
		init();
		ReservationMonitor rm = new ReservationMonitor();
		String xml = req.getParameter("data");// util.readXMLFromReq(req);
		String id = util.getXmlContent(xml, "id");
		String cid = util.getXmlContent(xml, "cid");
		String at = util.getXmlContent(xml, "currentATStr");
		try {
			rvm.confirmReservation(at, id, cid);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ma;
	}
	//get reservation list
	public ModelAndView getReservation(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		ModelAndView ma = new ModelAndView("CRM/actionpage/_reservationstatu");
		init();
		ReservationMonitor rm = new ReservationMonitor();
		String xml = req.getParameter("data");// util.readXMLFromReq(req);
		String at = util.getXmlContent(xml, "currentATStr");
		String buid = util.getXmlContent(xml, "currentUID");
		BizUser bu = bum.getBizUser(buid);
		RepostAndComment[] rac = rm.getReservationList(at, bu);
		Reservation[] rs, rsconf ;
		if(rac != null && rac.length >0){
			//store in db at first
			rvm.newReservation(rac, buid);
		}
		rs = rvm.getReservations(buid, 2);
		rsconf = rvm.getReservations(buid, 0);
		if(rs == null || rs.length == 0){
			rs = new Reservation[1];
			rs[0] = new Reservation();
			rs[0].setText("没有新的客户预定了，亲，咱发微博宣传去！");
			rs[0].setCreatedat(new Date());
			rs[0].setUsername("");
			rs[0].setResstatus(1);
			ma.addObject("msgs",rs);
		}
		ma.addObject("rss",rs);
		ma.addObject("rsconfs",rsconf);
		return ma;
	}
	public ModelAndView saveScoreRole(HttpServletRequest req,
			HttpServletResponse res) throws UnsupportedEncodingException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		ModelAndView ma = null;
		init();
		try {
			String xml = req.getParameter("data");// util.readXMLFromReq(req);
			String perrepostscore = util.getXmlContent(xml, "perrepostscore");
			String percommentscore = util.getXmlContent(xml, "percommentscore");
			String perdaymaxscore = util.getXmlContent(xml, "perdayhighscore");
			AccessToken atoken = (AccessToken) req.getSession().getAttribute(
					"sinaaccessToken");
			ScoreRole sr = new ScoreRole();
			sr.setPerrepostscore(Integer.parseInt(perrepostscore));
			sr.setPercommentscore(Integer.parseInt(percommentscore));
			sr.setPerdaymaxscore(Integer.parseInt(perdaymaxscore));
			sr.setUid(atoken.getUid());
			bum.updateScoreRole(sr);
			req.getSession().setAttribute("perrepostscore",
					sr.getPerrepostscore());
			req.getSession().setAttribute("percommentscore",
					sr.getPercommentscore());
			req.getSession().setAttribute("perdayhighscore",
					sr.getPerdaymaxscore());
			ma = new ModelAndView();
			PrintWriter out = res.getWriter();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView();
		}
		return ma;
	}
	/********************************************************************/
	/******************************VIP API*******************************/
	/********************************************************************/
	public ModelAndView getVIPUser(HttpServletRequest req,
			HttpServletResponse res) throws UnsupportedEncodingException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		ModelAndView ma = new ModelAndView("CRM/actionpage/_vipsearch");
		
		init();
		try {
			String xml = req.getParameter("data");
			String username = util.getXmlContent(xml, "username");
			String uid = util.getXmlContent(xml, "currentUID");
			VIPUser[] vipus = vum.getVUsersByName(username, uid);
			HashMap<String, VIPScore> vss = new HashMap<String, VIPScore>();
			VIPScore[] vssm = new VIPScore[vipus.length];
			int i =0;
			for(VIPUser vipu : vipus){
				vssm[i]  = vum.getVScore(vipu.getUid(), vipu.getBuid(), vipu.getSourcesite());
				i++;
			}
			String vusjson = "";
			if(vusjson == null){
				vusjson = "0"; 
			}else{
				ma.addObject("vus", vipus);
				ma.addObject("vssm",vssm);
				if(vipus.length == 1){
					ma.addObject("onlyone", vssm[0].getTotalscore());
				}else{
					ma.addObject("onlyone", "请点击选择VIP");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView();
		}
		return ma;
	}
	public ModelAndView getVIPUserInfo(HttpServletRequest req,
			HttpServletResponse res) throws UnsupportedEncodingException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		ModelAndView ma = new ModelAndView("CRM/actionpage/_vipnumber");
		init();
		String xml = req.getParameter("data");// util.readXMLFromReq(req);
		String buid = util.getXmlContent(xml, "currentUID");
		try {
			long vipNumer = vum.getNumber(buid);
			ma.addObject("vipnumber", "拥有："+ vipNumer+" 位VIP会员！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView();
		}
		return ma;
	}
	public ModelAndView getVIPUserList(HttpServletRequest req,
			HttpServletResponse res) throws UnsupportedEncodingException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		ModelAndView ma = new ModelAndView("CRM/actionpage/_viplist");
		init();
		String xml = req.getParameter("data");// util.readXMLFromReq(req);
		String buid = util.getXmlContent(xml, "currentUID");
		try {
			VIPUser[] vips = CacheFactory.getCache(buid).getVips();//vum.getVUsers(buid);
			ma.addObject("vips", vips);
			ma.addObject("lastitem", vips[vips.length-1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView();
		}
		return ma;
	}
	
	public ModelAndView vipScoreUpdate(HttpServletRequest req,
			HttpServletResponse res) throws UnsupportedEncodingException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		ModelAndView ma = new ModelAndView();
		init();
		try {
			String xml = req.getParameter("data");// util.readXMLFromReq(req);
			String userid = util.getXmlContent(xml, "userid");
			String username = util.getXmlContent(xml, "username");
			String uid = util.getXmlContent(xml, "currentUID");
			
			int isplus = Integer.parseInt(util.getXmlContent(xml, "isplus"));
			int perscore = Integer.parseInt(util.getXmlContent(xml, "perscore"));
			int totalscore = vum.getVScore(userid, uid, 0).getTotalscore();//Integer.parseInt(util.getXmlContent(xml,"totalscore"));
			if (isplus == 0) {
				totalscore = totalscore + perscore;
			} else {
				totalscore = totalscore - perscore;
			}
			VIPScore vs = new VIPScore();
			vs.setUid(userid);
			vs.setBuid(uid);
			vs.setUsername(username);
			vs.setPerscore(perscore);
			vs.setIsplus(isplus);
			vs.setTotalscore(totalscore);
			vs.setWeiboid(GlobalStaticData.vsChangebyBU_wbid);
			vum.newVIPScore(vs);
			ma.setViewName("CRM/actionpage/_vipscorechange");
			String result = "本次" + (vs.getIsplus()==0 ? "加了":"减了") + vs.getPerscore() + "积分";
			ma.addObject("result", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView();
		}
		return ma;
	}
	/********************************************************************/
	/******************************营销API********************************/
	/********************************************************************/
	/**
	 * 获取临时营销对象，但不保存到数据库
	 * @param req
	 * @param res
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public ModelAndView selectSalesTarget(HttpServletRequest req,
			HttpServletResponse res) throws UnsupportedEncodingException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		ModelAndView ma = new ModelAndView("CRM/actionpage/_salesusershow");
		init();
		String xml = req.getParameter("data");// util.readXMLFromReq(req);
		String at = util.getXmlContent(xml, "currentATStr");
		String buid = util.getXmlContent(xml, "currentUID");
		String location = util.getXmlContent(xml, "location");
		BizUser bu = new BizUser();
		bu.setAtoken(at);
		bu.setUid(buid);
		SalesCase sc = new SalesCase();
		try {
			sc = se.tempSalesCase(0, location, bu);
			String names = sc.getVipunamelist();
			String[] namess = names.split(",");
			String[] ids = sc.getVipuidlist().split(",");
			StringBuffer outnames = new StringBuffer();
			for(int i=0; i<namess.length; i++){
				outnames.append("<input type=checkbox checked value="+ids[i]+GlobalStaticData.spliter1+namess[i]+">" +
						"<span id=\"salesname"+i+"\"  href=\"http://weibo.com\" wb_screen_name="
						+ namess[i]
						+ " > "
						+ namess[i]
						+ " </span>&nbsp;&nbsp;"
						+ "<script type=\"text/javascript\">WB2.anyWhere(function(W) {W.widget.hoverCard({id : id=\"salesname"+i+"\" });});</script>"
						);
			}
			ma.addObject("names", outnames.toString());
			ma.addObject("ids", sc.getVipuidlist());
			req.getSession().setAttribute("salesnames", sc.getVipunamelist());
			req.getSession().setAttribute("salesids", sc.getVipuidlist());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView();
		}
		return ma;
	}
	
	/**
	 * 创建新的营销对象，保存到数据库
	 * @param req
	 * @param res
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public ModelAndView newSalesCase(HttpServletRequest req,
			HttpServletResponse res) throws UnsupportedEncodingException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		ModelAndView ma = new ModelAndView("CRM/actionpage/_successpage");
		init();
		String xml = req.getParameter("data");// util.readXMLFromReq(req);
		String buid = util.getXmlContent(xml, "currentUID");
		String at = util.getXmlContent(xml, "currentATStr");
		String viplist = util.getXmlContent(xml, "viplist");
		String tuanenddate = util.getXmlContent(xml, "tuanenddate");
		String tuancode = util.getXmlContent(xml, "tuancode");
		String content = util.getXmlContent(xml, "content");
		//String imgurl = util.getXmlContent(xml, "imgurl"
		String productids = util.getXmlContent(xml, "productids");
		String oriprice = util.getXmlContent(xml, "oriprice");
		String disprice = util.getXmlContent(xml, "disprice");
		String[] vips = viplist.split(GlobalStaticData.spliter);
		StringBuffer ids = new StringBuffer();
		StringBuffer names = new StringBuffer();
		for(int i=0; i<vips.length;i++){
			if(vips[i].length()>4){
				ids.append(vips[i].split(GlobalStaticData.spliter1)[0] + GlobalStaticData.spliter);
				names.append(vips[i].split(GlobalStaticData.spliter1)[1] + GlobalStaticData.spliter);
			}
		}
		SalesCase sc = new SalesCase();
		sc.setBuid(buid);
		sc.setVipuidlist(ids.toString());
		sc.setVipunamelist(names.toString());
		sc.setContent(content);
		sc.setImgurl("");
		sc.setStatus(1);
		sc.setProductids(productids);
		sc.setOriprice(oriprice);
		sc.setDisprice(disprice);
		sc.setTuancode(tuancode);
		sc.setBeuid(buid);
		sc.setUsername(buid);
		sc.setSourcesite(1);
		String _date = tuanenddate.split("/")[2]
					+"-" + tuanenddate.split("/")[0]
					+"-" + tuanenddate.split("/")[1] + " 23:59:59";
		Timestamp t  = Timestamp.valueOf(_date);
		sc.setCaseend(t);
		try {
			se.newSalesCase(sc, at);
			SalesCase[] scs =se.getSalesCaseList(buid);
			CacheFactory.getCache(buid).setSalesCases(scs);
			ma.addObject("sc", "创建成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView();
		}
		return ma;
	}
	public ModelAndView getProdsForSales(HttpServletRequest req,
			HttpServletResponse res) throws UnsupportedEncodingException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		ModelAndView ma = new ModelAndView("CRM/actionpage/_salesprodshow");
		init();
		String xml = req.getParameter("data");// util.readXMLFromReq(req);
		String buid = util.getXmlContent(xml, "currentUID");
		try {
			Product[] prods = CacheFactory.getCache(buid).getProducts();
			//PS 1 - 6: "主厨推荐","热菜","冷菜","汤/煲","主食","酒水/饮料"
			List<Product>[] ps = new ArrayList[GlobalStaticData.productCatNumber];
			for(int i=0; i<GlobalStaticData.productCatNumber;i++){
				ps[i] = new ArrayList<Product>();
			}
			if(prods.length >0 ){
				for(int i=0; i<prods.length; i++){
					switch(prods[i].getCategory()){
						case 1: ps[0].add(prods[i]); break;
						case 2: ps[1].add(prods[i]); break;
						case 3: ps[2].add(prods[i]); break;
						case 4: ps[3].add(prods[i]); break;
						case 5: ps[4].add(prods[i]); break;
						case 6: ps[5].add(prods[i]); break;
					}
				}
				//ma.addObject("prods", getProdsForSalesHTML(ps));
			}else{
				prods = new Product[1];
				prods[0] = new Product();
				prods[0].setProductname("您还没有添加菜品");
			}
			ma.addObject("prods", getProdsForSalesHTML(ps));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView();
		}
		return ma;
	}
	
	private String getProdsForSalesHTML(List<Product>[] prods){
		//PS 1 - 6: "主厨推荐","热菜","冷菜","汤/煲","主食","酒水/饮料"
		StringBuffer s = new StringBuffer();
		StringBuffer s_ = new StringBuffer();
		s.append("<select id=prodsalesel onChange=\"showProdsForSale()\">" +
				"<option value='_all' >全部</option>" +
				"<option value='prodforsales0' >主厨推荐</option>" +
				"<option value='prodforsales1' >热菜</option>" +
				"<option value='prodforsales2' >时蔬</option>" +
				"<option value='prodforsales3' >冷菜</option>" +
				"<option value='prodforsales4' >汤/煲</option>" +
				"<option value='prodforsales5' >主食</option>" +
				"<option value='prodforsales6' >酒水/饮料</option></select>");
		for(int i=0; i<GlobalStaticData.productCatNumber; i++){
			s_.append("<div id='prodforsales"+i+"' style='width:100%;'>");
			for(int j=0; j<prods[i].size(); j++){
				s_.append("<input onclick='checkProdForSales()' type=checkbox value='"
							+ prods[i].get(j).getId()+ ";"
							+ prods[i].get(j).getPrice()+"'>"
							+ prods[i].get(j).getProductname() + " " + prods[i].get(j).getPrice() + "元"
						);
			}
			s_.append("</div>");
			s.append(s_.toString());
			s_ = new StringBuffer();
		}
		return s.toString();
	}
	/**
	 * 获取营销列表
	 * @param req
	 * @param res
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public ModelAndView getSalesCases(HttpServletRequest req,
			HttpServletResponse res) throws UnsupportedEncodingException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		ModelAndView ma = new ModelAndView("CRM/actionpage/_salescaselist");
		init();
		String xml = req.getParameter("data");
		String buid = util.getXmlContent(xml, "currentUID");
		SalesCase[] scs = {};
		try {
			scs = CacheFactory.getCache(buid).getSalesCases();//se.getSalesCaseList(buid);
			if(scs[0].getId() != 0){
				Hashtable<String, Product> ps = CacheFactory.getCache(buid).getPht();
				for(int i=0 ;i <scs.length; i++){
					String[] vs = scs[i].getVipunamelist().split(GlobalStaticData.spliter);
					StringBuffer sb = new StringBuffer();
					for(int j=0; j<vs.length;j++){
						sb.append(vs[j]+", ");
					}
					scs[i].setVipunamelist(sb.toString());
					String[] pids = scs[i].getProductids().split(GlobalStaticData.spliter);
					scs[i].setProducts(new ArrayList());
					for(int n=0; n<pids.length; n++){
						if(!pids[n].equals("") && ps.containsKey(pids[n])){
							scs[i].getProducts().add(ps.get(pids[n]));
						}
					}
				}
			}
			ma.addObject("scs", scs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView();
		}
		return ma;
	}
	public ModelAndView deleteSalescase(HttpServletRequest req,
			HttpServletResponse res) throws UnsupportedEncodingException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		ModelAndView ma = new ModelAndView("CRM/actionpage/_productlist");
		init();
		String xml = req.getParameter("data");
		String buid = util.getXmlContent(xml, "currentUID");
		String sid = util.getXmlContent(xml, "sid");
		try {
			se.delete(buid, sid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView();
		}
		return ma;
	}
	public ModelAndView useTuan(HttpServletRequest req,
			HttpServletResponse res) throws UnsupportedEncodingException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		ModelAndView ma = new ModelAndView("CRM/actionpage/_successpage");
		init();
		String xml = req.getParameter("data");
		String buid = util.getXmlContent(xml, "currentUID");
		String tuancode = util.getXmlContent(xml, "tuancode");
		String tuanuser = util.getXmlContent(xml, "tuanuser");
		try {
			se.tuanUse(buid, tuancode, tuanuser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView();
		}
		return ma;
	}
	/********************************************************************/
	/******************************产品API********************************/
	/********************************************************************/
	public ModelAndView newProduct(HttpServletRequest req,
			HttpServletResponse res) throws UnsupportedEncodingException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		ModelAndView ma = new ModelAndView("CRM/actionpage/_successpage");
		init();
		String xml = req.getParameter("data");
		String buid = util.getXmlContent(xml, "currentUID");
		String category = util.getXmlContent(xml, "category");
		String productname = util.getXmlContent(xml, "productname");
		String productdesc = util.getXmlContent(xml, "productdesc");
		String imgurl = util.getXmlContent(xml, "imgurl");
		String active = util.getXmlContent(xml, "active");
		String price = util.getXmlContent(xml, "price");
		String incutoff = util.getXmlContent(xml, "incutoff");
		Product prod = new Product();
		prod.setBuid(buid);
		prod.setActive(Integer.parseInt(active));
		prod.setCategory(Integer.parseInt(category));
		prod.setImgurl(imgurl);
		prod.setIncutoff(incutoff);
		prod.setPrice(price);
		prod.setProductdesc(productdesc);
		prod.setProductname(productname);
		try {
			pe.newProduct(prod);
			Product[] prods = pe.getActiveProducts(buid);
			CacheFactory.getCache(buid).setProducts(prods);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView();
		}
		return ma;
	}

	public ModelAndView getAllProducts(HttpServletRequest req,
			HttpServletResponse res) throws UnsupportedEncodingException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		ModelAndView ma = new ModelAndView("CRM/actionpage/_productlist");
		init();
		String xml = req.getParameter("data");
		String buid = util.getXmlContent(xml, "currentUID");
		Product[] prods = {};
		try {
			prods = CacheFactory.getCache(buid).getProducts();//pe.getActiveProducts(buid);
			ma.addObject("prods", prods);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView();
		}
		return ma;
	}
	
	public ModelAndView getProductsShow(HttpServletRequest req,
			HttpServletResponse res) throws UnsupportedEncodingException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		ModelAndView ma = new ModelAndView("CRM/product/show");
		init();
		String xml = req.getParameter("data");
		String buname = req.getAttribute("bu").toString();
		String buid = util.getXmlContent(xml, "currentUID");
		Product[] prods = {};
		try {
			prods = pe.getShowProducts(buname, 0);
			ma.addObject("prods", prods);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView();
		}
		return ma;
	}
	public ModelAndView publishProduct(HttpServletRequest req,
			HttpServletResponse res) throws UnsupportedEncodingException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		ModelAndView ma = new ModelAndView("CRM/actionpage/_successpage");
		init();
		String xml = req.getParameter("data");
		String buid = util.getXmlContent(xml, "currentUID");
		String at = util.getXmlContent(xml, "currentATStr");
		String pid = util.getXmlContent(xml, "pid");
		try {
			Product p = pe.getProduct(Integer.valueOf(pid).intValue());
			SaeStorage storage = new SaeStorage();
			String path="img/prod/"+buid;
			byte[] pic = storage.read(path, p.getImgurl());
			pe.productPublish(at, p.getProductdesc(), pic);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView();
		}
		return ma;
	}
	public ModelAndView deleteProduct(HttpServletRequest req,
			HttpServletResponse res) throws UnsupportedEncodingException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		ModelAndView ma = new ModelAndView("CRM/actionpage/_productlist");
		init();
		String xml = req.getParameter("data");
		String buid = util.getXmlContent(xml, "currentUID");
		String pid = util.getXmlContent(xml, "pid");
		try {
			pe.delete(buid ,Integer.parseInt(pid));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView();
		}
		return ma;
	}
	
	public ModelAndView discountSwitch(HttpServletRequest req,
			HttpServletResponse res) throws UnsupportedEncodingException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		ModelAndView ma = new ModelAndView("CRM/actionpage/_producdiscount");
		init();
		String xml = req.getParameter("data");
		String id = util.getXmlContent(xml, "id");
		try {
			pe.discountSwitch(Integer.parseInt(id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView();
		}
		return ma;
	}

	/********************************************************************/
	/******************************general API***************************/
	/********************************************************************/
	/**
	 * product picture upload
	 * @return
	 * @throws IOException 
	 */
    public ModelAndView fileUpload2(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
        // 设置上下方文  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(  
                req.getSession().getServletContext());  
        ModelAndView ma = new ModelAndView("CRM/actionpage/_fileuploadresult");
        String uid = req.getParameter("imgbuid");
        String subfolder = req.getParameter("subfolder");
        if (multipartResolver.isMultipart(req)) {  
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) req;  
            Iterator<String> iter = multiRequest.getFileNames();  
            SaeStorage storage = new SaeStorage();
            while (iter.hasNext()) {            	
                // 由CommonsMultipartFile继承而来,拥有上面的方法.  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if (file != null) { 
                   // file.transferTo(localFile); http://100train-img.stor.sinaapp.com/8.jpg
                	String filename = file.getOriginalFilename() ;
                	String path="img/"+uid +"/" + subfolder;
                	storage.write(path, filename, file.getBytes());
                	//storage.read(arg0, arg1)
                	req.getSession().setAttribute("currentfile", filename);
                	ma.addObject("result",file.getOriginalFilename());
                } 
            }  
        }
        return ma;
    }  
	/********************************************************************/
	/******************************系统管理API********************************/
	/********************************************************************/
	public ModelAndView newBizUserInit(HttpServletRequest req,
			HttpServletResponse res) throws UnsupportedEncodingException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String xml = req.getParameter("data");// util.readXMLFromReq(req);
		String uid = util.getXmlContent(xml, "currentUID");
		String at = util.getXmlContent(xml, "currentATStr");
		
		ModelAndView ma = new ModelAndView("CRM/actionpage/_intialresult");
		init();
		try {
			BUInitTask bt = new BUInitTask();
			BizUser bu = new BizUser();
			bu.setUid(uid);
			bu.setAtoken(at);
			bu.setRepsinceid("1");
			bu.setCmssinceid("1");
			String re = bt.run(bu);
			long vipnumber = vum.getNumber(uid.toString());
			ma.addObject("result",re);
			ma.addObject("total",vipnumber);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ma;
		}
		return ma;
	}
	
	public String getVIPUsersJson(VIPUser[] vus, HashMap<String, VIPScore> vss) {
		StringBuffer sb = new StringBuffer();
		sb.append(jason_lbig);
		sb.append("number");
		sb.append(jason_split0);
		sb.append(vus.length);
		sb.append(jason_split1);		
		sb.append("vipuser");
		sb.append(jason_lmid);
		sb.append(jason_lbig);
		for (int i = 0; i < vus.length; i++) {
			sb.append("id");
			sb.append(jason_split0);
			sb.append(vus[i].getId());
			sb.append(jason_split1);
			sb.append("userid");
			sb.append(jason_split0);
			sb.append(vus[i].getUid());
			sb.append(jason_split1);
			if(vss != null){
				sb.append("score");
				sb.append(jason_split0);
				sb.append(vss.get(vus[i].getUid()).getTotalscore());
				sb.append(jason_split1);
			}
			sb.append("username");
			sb.append(jason_split0);
			sb.append(vus[i].getUsername());
			if((i+1) < vus.length)
				sb.append(jason_split2);
			else
				sb.append(jason_rbig0);
		}
		sb.append(jason_rbig1);
		return sb.toString();
	}
	public static void main(String[] str){
		System.out.print(new java.util.Date());
		
	}
}