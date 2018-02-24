package com.serivcereport.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="staff")
public class StaffBean {
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int staffId;
	private String staffName;
	private String staffSalary;
	
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffSalary() {
		return staffSalary;
	}
	public void setStaffSalary(String staffSalary) {
		this.staffSalary = staffSalary;
	}
	
	
}
