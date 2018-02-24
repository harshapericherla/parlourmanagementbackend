package com.servicereport.dao;

import java.util.List;

import com.serivcereport.beans.ClientBean;

public interface ClientDao {

	ClientBean getByID(int id);
	ClientBean insertClient(ClientBean clientBean);
	ClientBean getClientByName(String clientName);
	List<ClientBean> getAllClients();
	ClientBean updateClient(ClientBean clientBean);
}
