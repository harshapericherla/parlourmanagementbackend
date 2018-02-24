package com.serivcereport.beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="servicereport")
public class ServiceReportBean {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int serviceReportId;
	
	@ManyToOne(cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinColumn(name="clientId")
	private ClientBean clientBean;
	
	@ManyToOne(cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinColumn(name="servicesId")
	private ServicesBean serviceBean;
	
	@ManyToOne(cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinColumn(name="staffId")
	private StaffBean staffBean;

	@Transient
	private Integer totalRecords;
	
	private String serviceDiscount;
	private Date serviceReportDate;
	
	public int getServiceReportId() {
		return serviceReportId;
	}
	public void setServiceReportId(int serviceReportId) {
		this.serviceReportId = serviceReportId;
	}
	public ClientBean getClientBean() {
		return clientBean;
	}
	public void setClientBean(ClientBean clientBean) {
		this.clientBean = clientBean;
	}
	public ServicesBean getServiceBean() {
		return serviceBean;
	}
	public void setServiceBean(ServicesBean serviceBean) {
		this.serviceBean = serviceBean;
	}
	public StaffBean getStaffBean() {
		return staffBean;
	}
	public void setStaffBean(StaffBean staffBean) {
		this.staffBean = staffBean;
	}
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getServiceDiscount() {
		return serviceDiscount;
	}
	public void setServiceDiscount(String serviceDiscount) {
		this.serviceDiscount = serviceDiscount;
	}
	public Date getServiceReportDate() {
		return serviceReportDate;
	}
	public void setServiceReportDate(Date serviceReportDate) {
		this.serviceReportDate = serviceReportDate;
	}
	
	
}

