package com.servicereport.dao;

import java.util.List;
import com.serivcereport.beans.ServiceReportBean;

public interface ServiceReportDao {

	public ServiceReportBean saveServiceReport(ServiceReportBean serviceReportBeanArg);
	public List<ServiceReportBean> getAllServiceReports(String sortFieldArg,String sortDirectionArg,
			                                             int startLength,int endLength) throws Exception;
	public ServiceReportBean delete(ServiceReportBean serviceReportBeanArg);
	public ServiceReportBean edit(ServiceReportBean serviceReportBeanArg);
}
