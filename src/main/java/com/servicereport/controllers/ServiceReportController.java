package com.servicereport.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.serivcereport.beans.ClientBean;
import com.serivcereport.beans.ServiceReportBean;
import com.servicereport.services.ServiceReportService;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("servicereports")
public class ServiceReportController {

	@Autowired
	private ServiceReportService serviceReportService;
	
	@RequestMapping(value="/getAll",method=RequestMethod.GET)
	public ResponseEntity<?> getServiceReports(HttpServletRequest requestArg) throws Exception{
		
		serviceReportService.populateParameters(requestArg);
		List<ServiceReportBean> localServiceReportBeans = serviceReportService.getAllReports();
	    return new ResponseEntity<List<ServiceReportBean>>(localServiceReportBeans,HttpStatus.OK);
	}
	
	@RequestMapping(value="/addServiceReport",method = RequestMethod.POST)
	public ResponseEntity<?> addServiceReport(@RequestBody ServiceReportBean serviceReportBeanArg){
		
		ServiceReportBean localServiceReportBean = serviceReportService.performAction(serviceReportBeanArg);
		return new ResponseEntity<ServiceReportBean>(localServiceReportBean,HttpStatus.OK);
	}
    
	@RequestMapping(value="/editServiceReport",method = RequestMethod.POST)
	public ResponseEntity<?> editServiceReport(@RequestBody ServiceReportBean serviceReportBeanArg){
		
		ServiceReportBean localServiceReportBean = serviceReportService.edit(serviceReportBeanArg);
		return new ResponseEntity<ServiceReportBean>(localServiceReportBean,HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteServiceReport",method = RequestMethod.POST)
	public ResponseEntity<?> deleteServiceReport(@RequestBody ServiceReportBean serviceReportBeanArg){
		
		ServiceReportBean localServiceReportBean = serviceReportService.delete(serviceReportBeanArg);
		return new ResponseEntity<ServiceReportBean>(localServiceReportBean,HttpStatus.OK);
	}
}
