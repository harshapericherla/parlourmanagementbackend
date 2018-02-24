package com.servicereport.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.serivcereport.beans.ClientBean;
import com.servicereport.dao.ClientDao;

@Service
public class ClientService {

	@Autowired
	private ClientDao clientDao;
	
	public ClientBean getClientByName(ClientBean localClientBeanArg){
		
		String localClientName = localClientBeanArg.getFullName();
		ClientBean bean = null;
		bean = clientDao.getClientByName(localClientName);	
		if(bean == null){
			 bean = clientDao.insertClient(localClientBeanArg);
		}
		return bean;
	}

	public ClientBean checkIfExistsOrNot(ClientBean localClientBeanArg) {
		
		  String localFullName = localClientBeanArg.getFullName();
		  ClientBean localbean = getClientByName(localClientBeanArg);
		  return localbean;
	}
	
	public ClientBean updateClient(ClientBean clientBeanArg){
		 return clientDao.updateClient(clientBeanArg);
	}
	
	public ClientBean getClientById(int id){
		return clientDao.getByID(id);
	}
	
	public List<ClientBean> getClients(){
		return clientDao.getAllClients();
	}
	
}
