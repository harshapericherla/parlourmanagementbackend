package com.servicereport.dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.serivcereport.beans.ClientBean;
import com.serivcereport.beans.ServiceReportBean;
import com.serivcereport.beans.ServicesBean;
import com.serivcereport.beans.StaffBean;

@Transactional
@Repository
public class ServiceReportImpl implements ServiceReportDao{

	@Autowired
	private SessionFactory sessionFactory;

	public ServiceReportBean saveServiceReport(ServiceReportBean serviceReportBeanArg) {
		
		int serviceReportId = 0;
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		serviceReportId = (Integer) session.save(serviceReportBeanArg);
		serviceReportBeanArg.setServiceReportId(serviceReportId);
		
		session.getTransaction().commit();
		
		return serviceReportBeanArg;
	}

	public ServiceReportBean delete(ServiceReportBean serviceReportBeanArg) {
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.delete(serviceReportBeanArg);
		session.getTransaction().commit();
		return serviceReportBeanArg;
	}
	

	public ServiceReportBean edit(ServiceReportBean serviceReportBeanArg) {

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(serviceReportBeanArg);
		session.getTransaction().commit();
		return serviceReportBeanArg;
	}
	


	public List<ServiceReportBean> getAllServiceReports(String sortFieldArg,String sortDirectionArg,
			                                            int startLengthArg,int endLengthArg) throws Exception {
		
		List<ServiceReportBean> localServiceReportBeans = new ArrayList<ServiceReportBean>();
		List<Object[]> localBeans = null;
		
		String localOrderByQuery = " Order By "+sortFieldArg+" "+sortDirectionArg+" ,servicereportid "+sortDirectionArg; 
		String localPaginationQuery = " between "+startLengthArg+" and "+endLengthArg;
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		int localTotalRecords = ((Long)session.createQuery("select count(*) from ServiceReportBean").uniqueResult()).intValue();

		SQLQuery query = session.createSQLQuery(prepareQuery(localOrderByQuery,localPaginationQuery));
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		localBeans = query.list();
		
		for(Object object : localBeans){
			
			Map localRow = (Map)object;
			
			ClientBean localClientBean = (ClientBean)getBean(localRow,ClientBean.class);
			ServicesBean localServicesBean = (ServicesBean)getBean(localRow,ServicesBean.class);
			StaffBean localStaffBean = (StaffBean)getBean(localRow,StaffBean.class);
			ServiceReportBean localServiceReportBean = (ServiceReportBean)getBean(localRow,ServiceReportBean.class);
			
			localServiceReportBean.setClientBean(localClientBean);
			localServiceReportBean.setServiceBean(localServicesBean);
			localServiceReportBean.setStaffBean(localStaffBean);
			localServiceReportBean.setTotalRecords(localTotalRecords);
			
			localServiceReportBeans.add(localServiceReportBean);
		}
		
		
		
		return localServiceReportBeans;
	}

	private Object getBean(Map rowArg,Class classArg) throws Exception{
		
		Class localClass = classArg;
		Constructor constructor = localClass.getConstructor();
		
		Object object = constructor.newInstance();
		String localFieldName = null;
		Object localFieldValue = null;
		Field[] fields = object.getClass().getDeclaredFields();
		Class localFieldType = null;
		
		for(Field localField:fields){
			
			   localField.setAccessible(true);
			   localFieldName = localField.getName().toUpperCase();
			   localFieldValue = rowArg.get(localFieldName);
			   localFieldType = localField.getType();
		  
			   localField.set(object, localFieldValue);
		}
		return object;
	}

	public String prepareQuery(String orderByQueryArg,String paginationQueryArg){
		
		StringBuffer localSb = new StringBuffer();
		
		localSb.append("SELECT * FROM( ");
		localSb.append("SELECT * ");
		localSb.append(" ,rownum num FROM( ");
		localSb.append("SELECT sr.servicereportid,sr.servicediscount,sr.servicereportdate,c.*,s.*,st.* ");
		localSb.append("FROM servicereport sr,client c,services s,staff st ");
	    localSb.append("WHERE sr.clientid = c.clientId AND ");
		localSb.append("              sr.servicesid = s.serviceid AND ");
		localSb.append("              sr.staffid = st.staffid ");
		localSb.append(orderByQueryArg);
	    localSb.append(" ) ) ");
	    localSb.append(" where num ");
		localSb.append(paginationQueryArg);
		
		return localSb.toString();
		
	}

	
}
