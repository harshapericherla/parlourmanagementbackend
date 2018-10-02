package com.servicereport.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serivcereport.beans.StaffBean;
import com.servicereport.dao.StaffDao;

@Service
public class StaffService {
	@Autowired
	private StaffDao staffDao;
	
	public StaffBean checkIfExistsOrNot(StaffBean staffBeanArg){
		
		StaffBean localStaffBean = null;
        String localStaffName = staffBeanArg.getStaffName();		
        
        localStaffBean = staffDao.getStaffByName(localStaffName);
		if(localStaffBean == null){
			
			localStaffBean = staffDao.saveStaff(staffBeanArg);
		}
		return localStaffBean;
	}
	
	public List<StaffBean> getStaff(){
		return staffDao.getAllStaff();
	}
}
