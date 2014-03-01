package com.CRM.data.dao;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.CRM.data.TuanUse;

public class TuanUseDao {
	private HibernateTemplate hibernateTemplate;
	public TuanUseDao() {
		// TODO Auto-generated constructor stub
	}
	public TuanUseDao(SessionFactory s){
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
	public void insert(TuanUse tu) throws HibernateException{
		this.runInsert(tu);
	}
	
	public void update(TuanUse tu){
		this.runUpdate(tu);
	}
	/**
	 * 
	 */
	public TuanUse getTuanUser(String id){
		TuanUse[] tu = null;
		tu = this.runGetHQL("from TuanUse tu " +
				" where ( tu.id=" + id + ")" , 0, 1);
		if(tu != null && tu.length >0){
			return tu[0];
		}else
			return null;
	}
	
	public TuanUse[] getTuanUsers(String buid){
		TuanUse[] ws = null;
		ws = this.runGetHQL("from TuanUse tu " +
				" where ( tu.buid='" + buid + "'" +
				")" , 0, 0);
		return ws;
	}
	public TuanUse[] getTuanUsersByCode(String buid , String tuancode){
		TuanUse[] ts = null;
		ts = this.runGetHQL("from TuanUse tu " +
				" where ( tu.buid='" + buid + "' and tu.tuancode='" + tuancode + 
				"')" , 0, 0);
		return ts;
	}
	
	public TuanUse[] getTuanUsersByUser(String buid , String TuanUse){
		TuanUse[] ts = null;
		ts = this.runGetHQL("from TuanUse tu " +
				" where ( tu.buid='" + buid + "' and tu.TuanUse='" + TuanUse + 
				"')" , 0, 0);
		return ts;
	}
//	public TuanUse[] getActiveTuanUsers(String buid ){
//		TuanUse[] ts = null;
//		Timestamp current = new Timestamp(System.currentTimeMillis()); 
//		ts = this.runGetHQL("from TuanUse tu " +
//				" where ( tu.buid='" + buid + "' and tu.updatetime>='" + current + 
//				"')" , 0, 0);
//		return ts;
//	}
	public void delete(String id){
		TuanUse tu = this.getTuanUser(id);
		if(tu != null){
			this.runDelete(tu);
		}
	}
	public void delete(TuanUse tu){
		this.runDelete(tu);
	}
	/**
	 * search message by time
	 */
	private void runInsert(Object obj) throws HibernateException{
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
	private TuanUse[] runGetHQL(String hql, int first, int max){
		List<TuanUse> list = null;
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
		TuanUse[] ts = null;
		if(list != null && list.size() > 0 ){
			ts = new TuanUse[list.size()];
			Iterator<TuanUse> it= list.iterator();
			int i =0;
			while(it.hasNext()){
				ts[i] =  it.next();
				i++;
			}
		}
		return ts;
	}
}
