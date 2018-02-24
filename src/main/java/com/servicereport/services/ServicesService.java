package com.servicereport.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serivcereport.beans.ServicesBean;
import com.servicereport.dao.ServicesDao;

@Service
public class ServicesService {

	@Autowired
	private ServicesDao servicesDao;
	
	public ServicesBean checkIfExistsOrNot(ServicesBean servicesBeanArg){
	
		ServicesBean localServicesBean = null;
		String localServiceName = servicesBeanArg.getServiceName();
		
		localServicesBean = servicesDao.getServiceByName(localServiceName);
		if(localServicesBean == null){
			
			localServicesBean = servicesDao.saveService(servicesBeanArg);
		}
		return localServicesBean;
	}
	
	public List<ServicesBean> getServices(){
		return servicesDao.getAllServices();
	}
}
