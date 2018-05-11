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

import com.serivcereport.beans.ClientBean;
import com.serivcereport.beans.ServicesBean;
import com.servicereport.services.ClientService;

@CrossOrigin
@RestController
public class ClientController {

	@Autowired
	private ClientService clientService;

	@RequestMapping(value = "client/addClient", method = RequestMethod.POST)
	public ResponseEntity<?> addClient(@RequestBody ClientBean clientBeanArg) {

		ClientBean clientBean = clientService.getClientByName(clientBeanArg);
		return new ResponseEntity<ClientBean>(clientBean, HttpStatus.OK);
	}

	@RequestMapping(value = "client/getAllClients", method = RequestMethod.GET)
	public ResponseEntity<?> getAllClients() {

		List<ClientBean> localClientBeans = clientService.getClients();

		return new ResponseEntity<List<ClientBean>>(localClientBeans, HttpStatus.OK);
	}
}
