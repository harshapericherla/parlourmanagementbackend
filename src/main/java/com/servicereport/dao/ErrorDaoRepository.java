package com.servicereport.dao;


import org.springframework.data.repository.CrudRepository;

import com.serivcereport.beans.RestError;

public interface ErrorDaoRepository extends CrudRepository<RestError,Long>{
	
	RestError findByKey(String key);
}
