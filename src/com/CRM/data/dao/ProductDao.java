package com.CRM.data.dao;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.CRM.data.Product;
import com.CRM.data.SalesCase;

public class ProductDao {
	private HibernateTemplate hibernateTemplate;
	public ProductDao() {
		// TODO Auto-generated constructor stub
	}
	public ProductDao(SessionFactory s){
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
	public void insert(Product prod){
		this.runInsert(prod);
	}
	
	/**
	 * 
	 */
	public Product getProduct(int id){
		Product[] prod = null;
		prod = this.runGetHQL("from Product prod " +
				" where prod.id='" + id +
				"'" , 0, 1);
		if(prod != null && prod.length >0){
			return prod[0];
		}else
			return null;
	}
	
	public Product[] getProducts(String buid, int number){
		Product[] ws = null;
		ws = this.runGetHQL("from Product prod " +
				" where ( prod.buid='" + buid + "'" +
				")  order by prod.category asc" , 0, number);
		return ws;
	}
	/**
	 */
	public Product getProductByName(String name){
		Product prod = null;
		prod = this.runGetHQL("from Product prod " +
				" where prod.productname='" + name + "' order by prod.updatetime desc" , 0, 1)[0];
		return prod;
	}
	public void inactive(int id){
		Product prod = this.getProduct(id);
		prod.setActive(1);
		this.runUpdate(prod);
	}
	public void cutOffSwitch(int id){
		Product prod = this.getProduct(id);
//		if(prod.getIncutoff() == 0){
//			prod.setIncutoff(1);
//		}else{
//			prod.setIncutoff(0);
//		}
//		this.runUpdate(prod);
	}
	
	public void delete(int id){
		this.runDelete(this.getProduct(id));
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
	private Product[] runGetHQL(String hql, int first, int max){
		List<Product> list = null;
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
		Product[] ws = null;
		if(list != null && list.size() > 0 ){
			ws = new Product[list.size()];
			Iterator<Product> it= list.iterator();
			int i =0;
			while(it.hasNext()){
				ws[i] =  it.next();
				i++;
			}
		}
		return ws;
	}
}
