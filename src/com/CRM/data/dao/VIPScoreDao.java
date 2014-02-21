package com.CRM.data.dao;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.CRM.data.BizUser;
import com.CRM.data.VIPScore;
import com.CRM.data.VIPUser;

public class VIPScoreDao {
	private HibernateTemplate hibernateTemplate;
	public VIPScoreDao() {
		// TODO Auto-generated constructor stub
	}
	public VIPScoreDao(SessionFactory s){
		this.setSessionFactory(s);
	}
	
	public void setSessionFactory(SessionFactory sf){
		hibernateTemplate = new HibernateTemplate(sf);
	}
 
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	/**
	 * new or update
	 */
	public void insert(VIPScore vs){
		if(vs.getWeiboid().equals("1") || vs.getWeiboid().equals(com.CRM.GlobalStaticData.vsChangebyBU_wbid)){
			this.runInsert(vs);
		}else{
			VIPScore _ = this.getVIPScoreByWBID(vs.getWeiboid());
			if(_ == null)
				this.runInsert(vs);
		}
	}
	/**
	 * 
	 */
	public VIPScore getVIPScore(String userid, String buid, int sourcesite){
		VIPScore[] vs = null;
		vs = this.runGetHQL("from VIPScore vs " +
				" where ( vs.uid='" + userid + "' and vs.sourcesite=" + 
				sourcesite + " and vs.buid='" + buid +
				"') order by vs.updatetime desc" , 0, 1);
		if(vs != null && vs.length >0){
			return vs[0];
		}else
			return null;
	}
	/**
	 * 
	 */
	public VIPScore getVIPScoreByWBID(String wbid){
		VIPScore[] vs = null;
		vs = this.runGetHQL("from VIPScore vs " +
				" where ( vs.weiboid='" + wbid + "') order by vs.updatetime desc" , 0, 1);
		if(vs != null && vs.length >0){
			return vs[0];
		}else
			return null;
	}
	public VIPScore[] getVIPScore(String uid){
		VIPScore[] ws = null;
		ws = this.runGetHQL("from VIPScore vs " +
				" where ( vs.uid='" + uid + "'" +
				")  order by vs.updatetime desc" , 0, 0);
		return ws;
	}
	/**
	 */
	public VIPScore getVIPScoreByName(String username, int sourcesite){
		VIPScore vs = null;
		vs = this.runGetHQL("from VIPScore vs " +
				" where ( vs.username='" + username + "' and vs.sourcesite=" + sourcesite + 
				")  order by vs.updatetime desc" , 0, 1)[0];
		return vs;
	}
	
	public void delete(String uid){
		this.runDelete(this.getVIPScore(uid));
	}
	/**
	 * search message by time
	 */
//	public TalkMessage[] getTalkMessage(String start, String end){
//		TalkMessage[] ps = null;
//		ps = this.runGetHQL("from TalkMessage tm " +
//				" where tm.createTime >='" + start +"' and " +
//				" tm.createTime <='" + end + "' order by tm.createTime desc" , 0, 0);
//		return ps;
//	}	
	private void runInsert(Object obj){
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.saveOrUpdate(obj);
		t.commit();
		if (session.isOpen()) {
			session.close();
		}
	}
	
	private void runUpdate(Object obj){
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(obj);
		t.commit();
		if (session.isOpen()) {
			session.close();
		}
	}
	private void runDelete(Object obj){
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(obj);
		t.commit();
		if (session.isOpen()) {
			session.close();
		}
	}
	private VIPScore[] runGetHQL(String hql, int first, int max){
		List<VIPScore> list = null;
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		if(max > 0)
			list = session.createQuery(hql).setFirstResult(first).setMaxResults(max).list();
		else
			list = session.createQuery(hql).setFirstResult(first).list();
		t.commit();
		if (session.isOpen()) {
			session.close();
		}
		VIPScore[] ws = null;
		if(list != null && list.size() > 0 ){
			ws = new VIPScore[list.size()];
			Iterator<VIPScore> it= list.iterator();
			int i =0;
			while(it.hasNext()){
				ws[i] =  it.next();
				i++;
			}
		}
		return ws;
	}
}
