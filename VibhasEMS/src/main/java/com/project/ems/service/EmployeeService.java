package com.project.ems.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ems.model.Employee;
import com.project.ems.model.Leave;
import com.project.ems.repository.EmployeeRepository;
import com.project.ems.repository.LeaveRepository;
import com.project.ems.repository.SalaryRepository;

import jakarta.validation.Valid;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository empRepository;
	
	@Autowired
	SalaryRepository salaryRepo;
	
	@Autowired
	LeaveRepository leaveRepo;
	
	public boolean findEmployeeExist(Integer eid,String password) {//create controller 
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
	
	
	 public void deleteEmployee(Integer eid) {
	        empRepository.deleteById(eid);
	    }
	 
	 

	 public boolean updateEmployee(Employee updatedEmployee) {
	        Optional<Employee> existingEmployee = empRepository.findById(updatedEmployee.getEid());
	        if (existingEmployee.isPresent()) {
	            Employee employee = existingEmployee.get();
	            employee.setFirstName(updatedEmployee.getFirstName());
	            employee.setLastName(updatedEmployee.getLastName());
	            
	            empRepository.save(employee);
	            return true;
	        }
	        return false;
	    }
	 

	    public boolean isEmployeeExist(Integer eid) {
	        return empRepository.existsById(eid);
	    }  
	    
	    
	    
	    
	    
	    public Leave applyLeave(Integer empid, String leaveType, Integer numDays, List<Date> leaveDate) {
	        Optional<Leave> employee = leaveRepo.findById(empid);

	        if (employee.isPresent()) {
	           Leave empLeave = employee.get();
	           empLeave.setLeaveType(leaveType);
	           empLeave.setNumDays(numDays);
	           empLeave.setLeaveDate(leaveDate);
	           empLeave.setLeaveStatus("Pending");
	            return leaveRepo.save(empLeave);
	        }

	        return null;
	    }

	    
	    
	    
	    
	    public List<Employee> getAllEmployees() {
	        return empRepository.findAll();
	    }

		public Employee addEmployee(@Valid Employee employee) {
			// TODO Auto-generated method stub
			 return empRepository.save(employee);
		}


		public Employee getEmployeeById(Integer eid) {
			// TODO Auto-generated method stub
	        Optional<Employee> employee = empRepository.findById(eid);
	        
	        if (employee.isPresent()) {
	            return employee.get();
	        } else {
	            
	            return null;
	        }
	    }
	    
	    
	    	    
}


	
	
//addleave=choosing date 


//addattendance==


//getleavebalancebyempid
//-----
//getattendancebypercenatagebyempid
//-----
//employeeupdatepassword


	
	


