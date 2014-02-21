package com.CRM.data.dao;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.CRM.data.SalesCaseMonthRep;

public class SalesCaseMRepDao {
	private HibernateTemplate hibernateTemplate;
	public SalesCaseMRepDao() {
		// TODO Auto-generated constructor stub
	}
	public SalesCaseMRepDao(SessionFactory s){
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
	public void insert(SalesCaseMonthRep scmr){
		this.runInsert(scmr);
	}
	/**
	 * 
	 */
	public SalesCaseMonthRep getSalesCaseMonthRep(String yyyymm, String buid){
		SalesCaseMonthRep[] sc = null;
		sc = this.runGetHQL("from SalesCaseMonthRep scmr " +
				" where ( scmr.buid='" + 
				buid + "' and scmr.yyyymm='" + yyyymm +
				"')" , 0, 1);
		if(sc != null && sc.length >0){
			return sc[0];
		}else
			return null;
	}
	
	public SalesCaseMonthRep[] getSalesCaseMonthReps(String buid, int number){
		SalesCaseMonthRep[] ws = null;
		ws = this.runGetHQL("from SalesCaseMonthRep sc " +
				" where sc.buid='" + buid + "'" +
				" order by sc.updatetime desc" , 0, number);
		return ws;
	}
	
	public void delete(){
		//this.runDelete(this.getSalesCaseMonthRep(id,sourcesite));
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
	private SalesCaseMonthRep[] runGetHQL(String hql, int first, int max){
		List<SalesCaseMonthRep> list = null;
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
		SalesCaseMonthRep[] ws = null;
		if(list != null && list.size() > 0 ){
			ws = new SalesCaseMonthRep[list.size()];
			Iterator<SalesCaseMonthRep> it= list.iterator();
			int i =0;
			while(it.hasNext()){
				ws[i] =  it.next();
				i++;
			}
		}
		return ws;
	}
}
