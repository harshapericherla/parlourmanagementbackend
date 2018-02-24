package com.servicereport.dao;

import java.util.List;

import com.serivcereport.beans.StaffBean;

public interface StaffDao {

	public StaffBean saveStaff(StaffBean staffBeanArg);
	public StaffBean getStaffByName(String staffNameArg);
	public List<StaffBean> getAllStaff();
}
