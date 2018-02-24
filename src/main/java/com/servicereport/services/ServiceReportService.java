package com.servicereport.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.serivcereport.beans.ClientBean;
import com.serivcereport.beans.ServiceReportBean;
import com.serivcereport.beans.ServicesBean;
import com.serivcereport.beans.StaffBean;
import com.servicereport.dao.ServiceReportDao;


@Service
public class ServiceReportService {

	@Autowired
	private ClientService clientService;
	@Autowired
	private ServicesService servicesService;
	@Autowired
	private StaffService staffService;
	@Autowired
	private ServiceReportDao serviceReportDao;
	
	private String sortField;
	private String sortDirection;
	private Integer startLength;
	private Integer endLength; 
	
	public ServiceReportBean performAction(ServiceReportBean serviceReportBeanArg){
		
		  ClientBean localClientBean = serviceReportBeanArg.getClientBean();
		  ServicesBean localServicesBean = serviceReportBeanArg.getServiceBean();
		  StaffBean localStaffBean = serviceReportBeanArg.getStaffBean();
		  ServiceReportBean localServiceReportBean = null;
		  
		  localClientBean = clientService.checkIfExistsOrNot(localClientBean);
		  localServicesBean = servicesService.checkIfExistsOrNot(localServicesBean);
		  localStaffBean = staffService.checkIfExistsOrNot(localStaffBean);
		  
		  serviceReportBeanArg.setClientBean(localClientBean);
		  serviceReportBeanArg.setServiceBean(localServicesBean);
		  serviceReportBeanArg.setStaffBean(localStaffBean);
		  
		  localServiceReportBean = serviceReportDao.saveServiceReport(serviceReportBeanArg);
		  
		  return localServiceReportBean;
	}
	
	public List<ServiceReportBean> getAllReports() throws Exception{
		return serviceReportDao.getAllServiceReports(sortField,sortDirection,startLength,endLength);
	}
	
	public void populateParameters(HttpServletRequest requestArg){
		  
		sortField = requestArg.getParameter("sortField");
		sortDirection = requestArg.getParameter("sortDirection");
		startLength = Integer.parseInt(requestArg.getParameter("startLength"));
		endLength = Integer.parseInt(requestArg.getParameter("endLength"));
	}
	
	public ServiceReportBean delete(ServiceReportBean serviceReportBeanArg){
		 return serviceReportDao.delete(serviceReportBeanArg);
	}
	
	private boolean isClientUpdated(ClientBean clientBeanArg){
		
		boolean isUpdated = false;
		ClientBean localBean = clientService.getClientById(clientBeanArg.getClientId());
		
		String dBFullName = localBean.getFullName();
		String dBPhoneNumber = localBean.getPhoneNumber();
		String localFullName = clientBeanArg.getFullName();
		String localPhoneNumber = clientBeanArg.getPhoneNumber();
		
		if(!dBFullName.equals(localFullName) || !dBPhoneNumber.equals(localPhoneNumber)){
			isUpdated = true;
		}
		return isUpdated;
	}
	
	public ServiceReportBean edit(ServiceReportBean serviceReportBeanArg){
		 
		 ClientBean localClientBean = serviceReportBeanArg.getClientBean();
		 if(isClientUpdated(localClientBean)){
			 clientService.updateClient(localClientBean);
		 }
		 return serviceReportDao.edit(serviceReportBeanArg);
	}

}
