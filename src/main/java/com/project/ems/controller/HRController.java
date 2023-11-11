package com.project.ems.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.ems.model.Employee;
import com.project.ems.model.HR;
import com.project.ems.model.Leave;
import com.project.ems.model.Salary;
import com.project.ems.service.HRService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("hr")
@CrossOrigin("*")
public class HRController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	HRService hrService;
	//encrypt decrypt method for this
	
	@RequestMapping(path = "check-hr-exist/{eid}/{password}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Employee>> getEmpByName(@PathVariable(name = "eid") Integer eid,@PathVariable(name = "password") String password) {
		boolean found = hrService.findHRExist(eid, password);
		if(found) {
			HttpStatus status = HttpStatus.OK;
			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "HR found successfully.");
			ResponseEntity<List<Employee>> response = new ResponseEntity<List<Employee>>(headers, status);
			return response;
		}
		else {
			HttpStatus status = HttpStatus.NOT_FOUND;
			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "HR not found.");
			ResponseEntity<List<Employee>> response = new ResponseEntity<List<Employee>>(headers, status);
			return response;
		}
		
	}
	
	
	@RequestMapping(path = "get-all-emps", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Employee>> getAllEmps() {
		List<Employee> empList = hrService.findAllEmp();
		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Employees list successfully fetched");
		ResponseEntity<List<Employee>> response = new ResponseEntity<List<Employee>>(empList, headers,status);
		LOG.info("No. of records in database "+Integer.toString(empList.size()));
		return response;
	}
	
	@RequestMapping(path = "add-emp", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Employee> addEmp(@RequestBody Employee employee) {
	    Employee savedEmployee = hrService.addEmp(employee);
	    HttpStatus status = HttpStatus.CREATED;
	    HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Employees created successfully.");
	    ResponseEntity<Employee> response = new ResponseEntity<>(savedEmployee, headers,status);
	    LOG.info("Employee added to database successfull: " + savedEmployee.toString());
	    return response;
	}
	
	@RequestMapping(path = "update-emp", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Employee> addHR(@RequestBody Employee e) {
	    Employee emp= hrService.updateEmployee(e);	    
	    HttpStatus status = HttpStatus.OK;
	    HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Employee updated successfully.");
	    ResponseEntity<Employee> response = new ResponseEntity<>(emp, headers, status);
	    return response;
	}


	@RequestMapping(path = "delete-emp/{empid}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Employee> deleteEmployeeemployee(@PathVariable(name = "empid") Integer eid) {
		    hrService.deleteEmployee(eid);
		    HttpStatus status = HttpStatus.OK;
		    HttpHeaders headers = new HttpHeaders();
			headers.add("message", "Employees deleted successfully.");
		    ResponseEntity<Employee> response = new ResponseEntity<>(headers, status);
		    LOG.info("Employee deleted of EmpId "+eid);
		    return response;
	}
	
	@RequestMapping(path = "update-appraisal/{eid}", method = RequestMethod.PUT, consumes = "application/json",produces = "application/json")
	public ResponseEntity<Boolean> updateAppraisal(@PathVariable(name = "eid") Integer eid,@RequestBody Double appraisal) {
	    Boolean updated = hrService.giveAppraisalToEmp(eid, appraisal);
	    if(updated) {
	    	HttpStatus status =  HttpStatus.OK;
	 	    HttpHeaders headers = new HttpHeaders();
	 		headers.add("message", "Employee appraisal updated successfully.");
	 	    ResponseEntity<Boolean> response = new ResponseEntity<>(updated, headers, status);
	 	    LOG.info( "Employee appraisal updated successfully " );
	 	    return response;
	    }
	    else {
	    	HttpStatus status =  HttpStatus.NOT_FOUND;
	 	    HttpHeaders headers = new HttpHeaders();
	 		headers.add("message", "Employee not found");
	 	    ResponseEntity<Boolean> response = new ResponseEntity<>(updated, headers, status);
	 	    LOG.info("Employee not found");
	 	    return response;

	    }  
	}

	@RequestMapping(path = "get-hr-details/{eid}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<HR> getHRDetails(@PathVariable(name = "eid") Integer eid) {
	    HR hrDetails = hrService.getHRDetails(eid);
	    if(hrDetails!=null) {
	    	  HttpStatus status =  HttpStatus.OK;
	  	    HttpHeaders headers = new HttpHeaders();
	  		headers.add("message", "HR found successfully.");
	  	    ResponseEntity<HR> response = new ResponseEntity<>(hrDetails, headers, status);
	  	    LOG.info( "HR details retrieved for employee with eid " + eid );
	  	    return response;
	    }
	    else {
	    	HttpStatus status =  HttpStatus.NOT_FOUND;
	  	    HttpHeaders headers = new HttpHeaders();
	  		headers.add("message", "HR not found");
	  	    ResponseEntity<HR> response = new ResponseEntity<>(hrDetails, headers, status);
	  	    LOG.info( "HR not found with empId" + eid );
	  	    return response;
	    }
	  
	}

	@RequestMapping(path = "get-salary-details/{eid}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Double> getSalaryDetails(@PathVariable(name = "eid") Integer eid) {
	    Double salaryDetails = hrService.getSalaryDetailsByEmpId(eid);
	    HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Salary details found successfully.");
	    if (salaryDetails != null) {
	        HttpStatus status = HttpStatus.OK;
	        ResponseEntity<Double> response = new ResponseEntity<>(salaryDetails, status);
	        LOG.info("Salary details retrieved for employee with eid " + eid);
	        return response;
	    } else {
	        HttpStatus status = HttpStatus.NOT_FOUND;
	        ResponseEntity<Double> response = new ResponseEntity<>(null, status);
	        LOG.info("Salary details not found for employee with eid " + eid);
	        return response;
	    }
	}
	
	@RequestMapping(path = "change-leave-status/{eid}/{status}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> changeLeaveStatus(@PathVariable(name = "eid") Integer eid,@PathVariable(name="status") String status) {
	    Boolean updated= hrService.changeLeaveStatus(eid, status);
		if(updated) {
	    	HttpStatus httpStatus =  HttpStatus.OK;
	 	    HttpHeaders headers = new HttpHeaders();
	 		headers.add("message", "Employee leave status updated successfully.");
	 	    ResponseEntity<Boolean> response = new ResponseEntity<>(updated, headers, httpStatus);
	 	    LOG.info( "Employee leave status updated successfully." );
	 	    return response;
	    }
	    else {
	    	HttpStatus httpStatus =  HttpStatus.NOT_FOUND;
	 	    HttpHeaders headers = new HttpHeaders();
	 		headers.add("message", "Employee not found");
	 	    ResponseEntity<Boolean> response = new ResponseEntity<>(updated, headers, httpStatus);
	 	    LOG.info("Employee not found");
	 	    return response;

	    }  
		
	}
	
	@RequestMapping(path = "view-pending-leave-request", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Leave>> viewLeaveRequest() {
	    List<Leave> leaveList = hrService.viewAllLeaveRequests();	    
	    HttpStatus status = HttpStatus.OK;
	    HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Leaves fetched");
		ResponseEntity<List<Leave>> response = new ResponseEntity<List<Leave>>(leaveList, headers,status);
	    return response;
	}



	@RequestMapping(path = "add-hr", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<HR> addHR(@RequestBody HR hr) {
	    HR hrObj = hrService.addHR(hr);	    
	    HttpStatus status = HttpStatus.CREATED;
	    HttpHeaders headers = new HttpHeaders();
		headers.add("message", "HR added successfully.");
	    ResponseEntity<HR> response = new ResponseEntity<>(hrObj, headers, status);
	    return response;
	}

}

