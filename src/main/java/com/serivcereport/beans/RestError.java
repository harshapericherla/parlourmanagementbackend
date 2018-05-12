package com.serivcereport.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class RestError {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String key;
    private String message;

}
