package com.servicereport.dao;


import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.serivcereport.beans.ClientBean;

@Transactional
@Repository
public class ClientDaoImpl implements ClientDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	public ClientDaoImpl(){

	}
	
	public ClientBean getByID(int id) {
		
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		ClientBean client = (ClientBean)session.get(ClientBean.class, id);
		
		session.getTransaction().commit();
		return client;
	}

	public List<ClientBean> getAllClients(){
		
		List<ClientBean> localClientBeans = null; 
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(ClientBean.class);
		criteria.addOrder(Order.asc("fullName"));
		localClientBeans = criteria.list();
		
		session.getTransaction().commit();
		return localClientBeans;
	}
	
	public ClientBean insertClient(ClientBean clientBean) {
		
		int localClientId = 0;
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		localClientId = (Integer) session.save(clientBean);
		session.getTransaction().commit();
		clientBean.setClientId(localClientId);
		return clientBean;
	}

	public ClientBean updateClient(ClientBean clientBean){
		
		 session = sessionFactory.getCurrentSession();
		 session.beginTransaction();
		 
		 session.update(clientBean);
		 session.getTransaction().commit();
		 return clientBean;
	}
	
	public ClientBean getClientByName(String clientName) {
		
		ClientBean clientBean = null;
		session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from ClientBean c where c.fullName = :fullName");
		query.setParameter("fullName",clientName);
		List clients = query.list();
		
		if(clients.size() > 0){
			clientBean = (ClientBean)clients.get(0);
		}
		
		return clientBean;
	
	}
}
