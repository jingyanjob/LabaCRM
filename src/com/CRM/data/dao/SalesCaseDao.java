package com.CRM.data.dao;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.CRM.data.SalesCase;

public class SalesCaseDao {
	private HibernateTemplate hibernateTemplate;
	public SalesCaseDao() {
		// TODO Auto-generated constructor stub
	}
	public SalesCaseDao(SessionFactory s){
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
	public void insert(SalesCase sc){
		this.runInsert(sc);
	}
	
	/**
	 * 
	 */
	public SalesCase getSalesCase(String id, int sourcesite){
		SalesCase[] sc = null;
		sc = this.runGetHQL("from SalesCase sc " +
				" where (  sc.id=" + id +
				")" , 0, 1);
		if(sc != null && sc.length >0){
			return sc[0];
		}else
			return null;
	}
	
	public SalesCase[] getSalesCases(String buid, int sourcesite, int number){
		SalesCase[] ws = null;
		ws = this.runGetHQL("from SalesCase sc " +
				" where ( sc.buid='" + buid + "'" +
				")  order by sc.updatetime desc" , 0, number);
		return ws;
	}
	public SalesCase[] getActiveSalesCases(String buid, int sourcesite, int number){
		SalesCase[] ws = null;
		Timestamp current = new Timestamp(System.currentTimeMillis()); 
		ws = this.runGetHQL("from SalesCase sc " +
				" where ( sc.buid='" + buid + "' and sc.caseend > '" + current + 
				"')  order by sc.updatetime desc" , 0, number);
		return ws;
	}
	/**
	 */
	public SalesCase getSalesCaseByName(String username, int sourcesite){
		SalesCase sc = null;
		sc = this.runGetHQL("from SalesCase sc " +
				" where ( sc.username='" + username + "' and sc.sourcesite=" + sourcesite + 
				")  order by sc.updatetime desc" , 0, 1)[0];
		return sc;
	}
	
	public void delete(String id, int sourcesite){
		this.runDelete(this.getSalesCase(id,sourcesite));
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
		if (session.isOpen()){
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
	private SalesCase[] runGetHQL(String hql, int first, int max){
		List<SalesCase> list = null;
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
		SalesCase[] ws = null;
		if(list != null && list.size() > 0 ){
			ws = new SalesCase[list.size()];
			Iterator<SalesCase> it= list.iterator();
			int i =0;
			while(it.hasNext()){
				ws[i] =  it.next();
				i++;
			}
		}
		return ws;
	}
}
