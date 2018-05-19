package com.serivcereport.beans;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="services")
public class ServicesBean {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer serviceId;
	private String serviceName;
	private String serviceCost;
	
}
