package com.CRM.data.dao;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.CRM.data.BizUser;
import com.CRM.data.VIPUser;

public class VIPUserDao {
	private HibernateTemplate hibernateTemplate;
	public VIPUserDao() {
		// TODO Auto-generated constructor stub
	}
	public VIPUserDao(SessionFactory s){
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
	public void insert(VIPUser vu){
		this.runInsert(vu);
	}
	/**
	 * 
	 */
	public VIPUser getVIPUser(String userid, String buid, int sourcesite){
		VIPUser[] vu = null;
		vu = this.runGetHQL("from VIPUser vu " +
				" where ( vu.uid='" + userid + "' and vu.sourcesite=" + sourcesite + 
					" and vu.buid='" + buid + "')" , 0, 1);
		if(vu != null && vu.length >0){
			return vu[0];
		}else
			return null;
	}
	
	public VIPUser[] getVIPUser(String buid){
		VIPUser[] ws = null;
		ws = this.runGetHQL("from VIPUser vu " +
				" where ( vu.buid='" + buid + "'" +
				")" , 0, 0);
		return ws;
	}
	/**
	 */
	public VIPUser getVIPUserByName(String username,  String buid, int sourcesite){
		VIPUser[] vu = null;
		vu = this.runGetHQL("from VIPUser vu " +
				" where ( vu.username='" + username + "' and vu.sourcesite=" + sourcesite + 
				" and vu.buid='" + buid + "')" , 0, 1);
		if(vu != null)
			return vu[0];
		else
			return null;
	}
	/**
	 */
	public VIPUser[] getVIPUsersByName(String username,  String buid, int sourcesite){
		VIPUser[] vu = null;
		vu = this.runGetHQL("from VIPUser vu " +
				" where ( vu.username like '%" + username + "%' and vu.sourcesite=" + sourcesite + 
				" and vu.buid='" + buid + "')" , 0, 0);
		if(vu != null)
			return vu;
		else
			return null;
	}
	public VIPUser[] getVIPUsersByLocation(String location,  String buid, int sourcesite, int number){
		VIPUser[] vu = null;
		String[] ls = location.split(" ");
		if(ls.length == 2){
			String hql = "from VIPUser vu " +
					" where ( vu.location like '%" + ls[0] + "%' " +
					" or vu.location like '%" +  ls[1] + "%' )" +
					" and vu.sourcesite=" + sourcesite + 
					" and vu.buid='" + buid + "'" ;
			vu = this.runGetHQL(hql , 0, number);
		}else if(ls.length == 1){
			vu = this.runGetHQL("from VIPUser vu " +
					" where ( vu.location like'%" + location + "%' and vu.sourcesite=" + sourcesite + 
					" and vu.buid='" + buid + "')" , 0, number);
		}
		return vu;
	}
	public VIPUser getVIPUser(int id){
		VIPUser[] vu = null;
		vu = this.runGetHQL("from VIPUser vu " +
				" where ( vu.userid='" + id + "'" +
				")" , 0, 1);
		
		if(vu != null && vu.length >0){
			return vu[0];
		}else
			return null;
	}
	public void delete(int id){
		this.runDelete(this.getVIPUser(id));
	}
	/**
	 * get table row number
	 */	
	public List getNubmer(String buid){
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List l = session.createQuery("select count(vu.id) from VIPUser vu where vu.buid='"+buid+"'").list();
		t.commit();
		if (session.isOpen()) {
			session.close();
		}
		return l;
	}
	
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
	@SuppressWarnings("unchecked")
	private VIPUser[] runGetHQL(String hql, int first, int max){
		List<VIPUser> list = null;
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
		VIPUser[] ws = null;
		if(list != null && list.size() > 0 ){
			ws = new VIPUser[list.size()];
			Iterator<VIPUser> it= list.iterator();
			int i =0;
			while(it.hasNext()){
				ws[i] =  it.next();
				i++;
			}
		}
		return ws;
	}
}
