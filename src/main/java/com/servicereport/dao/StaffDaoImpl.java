package com.servicereport.dao;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.serivcereport.beans.ClientBean;
import com.serivcereport.beans.ServicesBean;
import com.serivcereport.beans.StaffBean;

@Transactional
@Repository
public class StaffDaoImpl implements StaffDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public StaffBean saveStaff(StaffBean staffBeanArg) {
		
		int localStaffId = 0;
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		localStaffId = (Integer)session.save(staffBeanArg);
		session.getTransaction().commit();
		staffBeanArg.setStaffId(localStaffId);
		return staffBeanArg;
	
	}

	public StaffBean getStaffByName(String staffNameArg) {
		
		StaffBean staffBean = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from StaffBean s where s.staffName = :staffName");
		query.setParameter("staffName",staffNameArg);
		List staffs = query.list();
		
		if(staffs.size() > 0){
			staffBean = (StaffBean)staffs.get(0);
		}
		return staffBean;	
		
	}

	public List<StaffBean> getAllStaff() {
		
		List<StaffBean> localStaffBeans = null; 
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(StaffBean.class);
		criteria.addOrder(Order.asc("staffName"));
		localStaffBeans = criteria.list();
		
		session.getTransaction().commit();
		return localStaffBeans;
	}

}
