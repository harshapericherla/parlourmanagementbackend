package com.servicereport.dao;

import java.util.List;

import com.serivcereport.beans.ServicesBean;

public interface ServicesDao {

	public ServicesBean saveService(ServicesBean servicesBeanArg);
	public ServicesBean editServie(ServicesBean servicesBeanArg);
	public ServicesBean deleteService(ServicesBean servicesBeanArg);
	public ServicesBean getServiceByName(String serviceNameArg);
	public List<ServicesBean> getAllServices();
}
