package com.servicereport.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.config.ParlourCustomException;
import com.serivcereport.beans.RestError;
import com.servicereport.dao.ErrorDaoRepository;

@ControllerAdvice
public class ControllerAdviceController {

	@Autowired
	private ErrorDaoRepository errorDaoRepository;
	
	@ExceptionHandler(ParlourCustomException.class)
	public ResponseEntity notFoundException(Exception e){
		
		String localKey = null;
        RestError localError = null;
		// getting the error key
		localKey = e.getMessage();
		
		localError = errorDaoRepository.findByKey(localKey);
		return new ResponseEntity(localError,HttpStatus.NOT_FOUND);
	}
	
}
