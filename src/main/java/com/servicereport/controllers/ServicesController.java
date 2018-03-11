package com.servicereport.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.serivcereport.beans.ServicesBean;
import com.servicereport.services.ServicesService;

@CrossOrigin
@RestController
public class ServicesController {

	@Autowired
	private ServicesService servicesService;
	
	
	@RequestMapping(value="service/getServices",method=RequestMethod.GET)
	public ResponseEntity<List<ServicesBean>> getAllServices(){
		
		List<ServicesBean> localServiceBeans = servicesService.getServices();
		
		return new ResponseEntity<List<ServicesBean>>(localServiceBeans, HttpStatus.OK);
	}
	
	@RequestMapping(value="service/addService",method=RequestMethod.POST)
	public ResponseEntity<ServicesBean> addService(@RequestBody ServicesBean servicesBeanArg){
		
		ServicesBean localServiceBean =  servicesService.checkIfExistsOrNot(servicesBeanArg);
		return new ResponseEntity<ServicesBean>(localServiceBean, HttpStatus.OK);
	}
	
	@RequestMapping(value="service/editService",method=RequestMethod.POST)
	public ResponseEntity<ServicesBean> editService(@RequestBody ServicesBean servicesBeanArg){
		
		ServicesBean localServiceBean =  servicesService.editService(servicesBeanArg);
		return new ResponseEntity<ServicesBean>(localServiceBean, HttpStatus.OK);
	}
	
	@RequestMapping(value="service/deleteService",method=RequestMethod.POST)
	public ResponseEntity<ServicesBean> deleteService(@RequestBody ServicesBean servicesBeanArg){
		
		ServicesBean localServiceBean =  servicesService.deleteService(servicesBeanArg);
		return new ResponseEntity<ServicesBean>(localServiceBean, HttpStatus.OK);
	}
}
