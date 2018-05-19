package com.serivcereport.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="staff")
public class StaffBean {
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int staffId;
	private String staffName;
	private String staffSalary;
	
}
