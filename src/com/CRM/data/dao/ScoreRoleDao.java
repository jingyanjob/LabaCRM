package com.CRM.data.dao;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.CRM.data.BizUser;
import com.CRM.data.ScoreRole;
import com.CRM.data.VIPScore;
import com.CRM.data.VIPUser;

public class ScoreRoleDao {
	private HibernateTemplate hibernateTemplate;
	public ScoreRoleDao() {
		// TODO Auto-generated constructor stub
	}
	public ScoreRoleDao(SessionFactory s){
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
	public void insert(ScoreRole sr){
		this.delete(sr.getUid());
		this.runInsert(sr);
	}
	/**
	 * 
	 */
	public ScoreRole getScoreRole(String uid){
		ScoreRole[] sr = null;
		sr = this.runGetHQL("from ScoreRole sr " +
				" where ( sr.uid='" + uid + "'" +
				")" , 0, 1);
		if(sr != null && sr.length >0){
			return sr[0];
		}else
			return null;
	}
	public ScoreRole[] getScoreRoles(String uid){
		ScoreRole[] ws = null;
		ws = this.runGetHQL("from ScoreRole sr " +
				" where ( sr.uid='" + uid + "'" +
				")" , 0, 0);
		return ws;
	}
	public void delete(String uid){
		ScoreRole sr = this.getScoreRole(uid);
		if(sr != null)
			this.runDelete(this.getScoreRole(uid));
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
	private ScoreRole[] runGetHQL(String hql, int first, int max){
		List<ScoreRole> list = null;
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
		ScoreRole[] ws = null;
		if(list != null && list.size() > 0 ){
			ws = new ScoreRole[list.size()];
			Iterator<ScoreRole> it= list.iterator();
			int i =0;
			while(it.hasNext()){
				ws[i] =  it.next();
				i++;
			}
		}
		return ws;
	}
}
