package com.CRM.data.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.CRM.data.BizUser;

public class BizUserDao {
	private HibernateTemplate hibernateTemplate;
	public BizUserDao() {
		// TODO Auto-generated constructor stub
	}
	public BizUserDao(SessionFactory s){
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
	public void insert(BizUser bu) throws HibernateException{
		this.runInsert(bu);
	}
	
	public void update(BizUser bu){
		this.runUpdate(bu);
	}
	/**
	 * 
	 */
	public BizUser getBizUser(String uid, int sourcesite){
		BizUser[] bu = null;
		bu = this.runGetHQL("from BizUser bu " +
				" where ( bu.uid='" + uid + "' and bu.sourcesite=" + sourcesite + 
				")" , 0, 1);
		if(bu != null && bu.length >0){
			return bu[0];
		}else
			return null;
	}
	
	public BizUser[] getBizUsers(String uid){
		BizUser[] ws = null;
		ws = this.runGetHQL("from BizUser bu " +
				" where ( bu.uid='" + uid + "'" +
				")" , 0, 0);
		return ws;
	}
	public BizUser[] getBizUsers(){
		BizUser[] ws = null;
		ws = this.runGetHQL("from BizUser bu " , 0, 0);
		return ws;
	}
	/**
	 */
	public BizUser getBizUserByName(String username, int sourcesite){
		BizUser bu = null;
		bu = this.runGetHQL("from BizUser bu " +
				" where ( bu.username='" + username + "' and bu.sourcesite=" + sourcesite + 
				")" , 0, 1)[0];
		return bu;
	}
	
	public BizUser getBizUser(String uid){
		BizUser[] bu = null;
		bu = this.runGetHQL("from BizUser bu " +
				" where ( bu.uid='" + uid + "'" +
				")" , 0, 1);
		
		if(bu != null && bu.length >0){
			return bu[0];
		}else
			return null;
	}
	public void delete(String id){
		BizUser bu = this.getBizUser(id);
		if(bu != null){
			this.runDelete(bu);
		}
	}
	public void delete(BizUser bu){
		this.runDelete(bu);
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
	private BizUser[] runGetHQL(String hql, int first, int max){
		List<BizUser> list = null;
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
		BizUser[] ws = null;
		if(list != null && list.size() > 0 ){
			ws = new BizUser[list.size()];
			Iterator<BizUser> it= list.iterator();
			int i =0;
			while(it.hasNext()){
				ws[i] =  it.next();
				i++;
			}
		}
		return ws;
	}
}
