package com.serivcereport.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="client")
public class ClientBean {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int clientId;
	
	private String firstName;
	private String lastName;
	private String fullName;
	private String phoneNumber;
	
}
