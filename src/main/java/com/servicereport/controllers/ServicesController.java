package com.servicereport.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
}
