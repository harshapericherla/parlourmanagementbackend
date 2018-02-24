package com.servicereport.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.serivcereport.beans.ClientBean;
import com.serivcereport.beans.ServicesBean;

@Transactional
@Repository
public class ServicesDaoImpl implements ServicesDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public ServicesBean saveService(ServicesBean servicesBeanArg){
		
		int localServiceId = 0;
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		localServiceId = (Integer)session.save(servicesBeanArg);
		session.getTransaction().commit();
		servicesBeanArg.setServiceId(localServiceId);
		return servicesBeanArg;
	}
	
	public ServicesBean getServiceByName(String serviceNameArg){
		
		ServicesBean serviceBean = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ServicesBean s where s.serviceName = :serviceName");
		query.setParameter("serviceName",serviceNameArg);
		List services = query.list();
		
		if(services.size() > 0){
			serviceBean = (ServicesBean)services.get(0);
		}
		return serviceBean;
	}

	public List<ServicesBean> getAllServices() {
		
		List<ServicesBean> localServiceBeans = null; 
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
	
		Criteria criteria = session.createCriteria(ServicesBean.class);
		criteria.addOrder(Order.asc("serviceName"));
		localServiceBeans = criteria.list();
		
		session.getTransaction().commit();
		return localServiceBeans;
	}
}
