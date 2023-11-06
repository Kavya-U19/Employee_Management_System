package com.project.ems.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ems.model.Employee;
import com.project.ems.model.Salary;
import com.project.ems.repository.EmployeeRepository;
import com.project.ems.repository.SalaryRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository empRepository;
	
	@Autowired
	SalaryRepository salaryRepo;
	
	public boolean findEmployeeExist(Integer eid,String password) {
		Optional<Employee> e=empRepository.findById(eid);
		if(e.isPresent()) {
			String empPassword=e.get().getPassword();
			if(empPassword.equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean setEmpAppraisal(Integer eid,Integer appraisal) {
		Optional<Employee> e=empRepository.findById(eid);
		if(e.isPresent()) {
			Employee emp=e.get();
			emp.setAppraisal(appraisal);
			return true;
		}
		return false;
	}
	
	
	
	

}
