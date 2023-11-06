package com.project.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ems.model.Employee;
import com.project.ems.model.HR;
import com.project.ems.model.Leave;
import com.project.ems.repository.EmployeeRepository;
import com.project.ems.repository.HRRepository;
import com.project.ems.repository.LeaveRepository;

@Service
public class HRService {
	
	@Autowired
	HRRepository hrRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	LeaveRepository leaveRepo;
	
	public boolean findHRExist(Integer eid,String password) {
		Optional<HR> h=hrRepo.findById(eid);
		if(h.isPresent()) {
			String hPassword=h.get().getPassword();
			if(hPassword.equals(password)) {
				return true;
			}
		}
		return false;
	}
	
//	add emp
//	 set leaves
	
	public List<Employee> findAllEmp(){
		List<Employee> emp=empRepo.findAll();
		return emp;	
	}
	
	public boolean giveAppraisalToEmp(Integer eid,Integer appraisal) {
		Optional<Employee> e=empRepo.findById(eid);
		if(e.isPresent()) {
			Employee emp=e.get();
			emp.setAppraisal(appraisal);
			return true;
		}
		return false;
	}
	
	public boolean changeLeaveStatus(Integer eid,String leaveType,String status) {
		Optional<Leave> e=leaveRepo.findById(eid);
		if(e.isPresent()) {
			Leave leave=e.get();
			leave.setLeaveStatus(status);
			if(status.equalsIgnoreCase("Accept")) {
				if(leave.getLeaveType().equalsIgnoreCase("Paid Leave"))
					leave.setPaidLeave(leave.getPaidLeave()-1);
				else
					leave.setSickLeave(leave.getSickLeave()-1);
			}
			return true;
			
		}
		return false;
	}
	
	

}
