package com.servicereport.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.serivcereport.beans.ClientBean;
import com.serivcereport.beans.ServicesBean;
import com.serivcereport.beans.StaffBean;
import com.servicereport.services.StaffService;

@CrossOrigin
@RestController
public class StaffController {

	@Autowired
	private StaffService staffService;
	
	@RequestMapping(value="staff/getAll",method=RequestMethod.GET)
	public ResponseEntity<?> getAllStaff(){
		
		List<StaffBean> localServiceBeans = staffService.getStaff();
		return new ResponseEntity<List<StaffBean>>(localServiceBeans, HttpStatus.OK);
	}
}
