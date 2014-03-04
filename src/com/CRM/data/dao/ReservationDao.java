package com.CRM.data.dao;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.CRM.data.BizUser;
import com.CRM.data.Reservation;
import com.CRM.data.Reservation;

public class ReservationDao {
	private HibernateTemplate hibernateTemplate;
	public ReservationDao() {
		// TODO Auto-generated constructor stub
	}
	public ReservationDao(SessionFactory s){
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
	public void insert(Reservation r){
		this.runInsert(r);
	}

	
	public Reservation[] getReservations(String buid, int resstatus){
		Reservation[] ws = null;
		ws = this.runGetHQL("from Reservation r " +
				" where ( r.buid='" + buid + "' and r.resstatus=" + resstatus+
				" ) order by createdat desc" , 0, 0);
		return ws;
	}
	/**
	 */
	public Reservation[] getReservationsByUserName(String username,  String buid){
		Reservation[] vu = null;
		vu = this.runGetHQL("from Reservation vu " +
				" where ( vu.username like '%" + username + "%' "+ 
				" and vu.buid='" + buid + "')" , 0, 0);
		if(vu != null)
			return vu;
		else
			return null;
	}
	public Reservation getReservation(String id, String cid){
		Reservation[] rs = null;
		rs = this.runGetHQL("from Reservation r " +
				" where ( r.id='" + id + "' and r.cid='" + cid +
				"')" , 0, 1);
		
		if(rs != null && rs.length >0){
			return rs[0];
		}else
			return null;
	}
	public void delete(String id, String cid){
		this.runDelete(this.getReservation(id, cid));
	}
	/**
	 * get table row number
	 */	
	public List getNubmer(String buid){
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List l = session.createQuery("select count(r.id) from Reservation r where r.buid='"+buid+"'").list();
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
	private Reservation[] runGetHQL(String hql, int first, int max){
		List<Reservation> list = null;
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
		Reservation[] ws = null;
		if(list != null && list.size() > 0 ){
			ws = new Reservation[list.size()];
			Iterator<Reservation> it= list.iterator();
			int i =0;
			while(it.hasNext()){
				ws[i] =  it.next();
				i++;
			}
		}
		return ws;
	}
}
