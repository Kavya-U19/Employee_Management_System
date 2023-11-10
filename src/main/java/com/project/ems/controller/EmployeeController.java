package com.project.ems.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.ems.model.Attendance;
import com.project.ems.model.Employee;
import com.project.ems.model.Leave;
import com.project.ems.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("employee")
@CrossOrigin("*")
public class EmployeeController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EmployeeService employeeService;

	@RequestMapping(path = "get-employee/{eid}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Employee> getEmployee(@PathVariable(name = "eid") Integer eid) {
        Employee employee = employeeService.getEmployeeById(eid);
        if (employee != null) {
            HttpStatus status = HttpStatus.OK;
            HttpHeaders headers = new HttpHeaders();
            headers.add("message", "Employee found successfully.");
            LOG.info("Employee not found");
            ResponseEntity<Employee> response = new ResponseEntity<Employee>(employee, headers, status);
            return response;
        }
        HttpStatus status = HttpStatus.NOT_FOUND;
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Employee not found.");
        ResponseEntity<Employee> response = new ResponseEntity<Employee>(headers, status);
        LOG.info("Employee not found");
        return response;
    }

	    @RequestMapping(path = "update-employee", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Employee> updateEmployee(@RequestBody @Valid Employee employee) {
    	employeeService.updateEmployee(employee);
    	HttpStatus status = HttpStatus.OK;
    	 HttpHeaders headers = new HttpHeaders();
         headers.add("message", "Employee updated successfully.");
         ResponseEntity<Employee> response = new ResponseEntity<Employee>(headers, status);
         return response;
    }
	
	@RequestMapping(path = "check-emp-exist/{eid}/{password}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Employee>> getEmpByName(@PathVariable(name = "eid") Integer eid,@PathVariable(name = "password") String password) {
		boolean found = employeeService.findEmployeeExist(eid, password);
		if(found) {
			HttpStatus status = HttpStatus.OK;
			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "Employees found successfully.");
			ResponseEntity<List<Employee>> response = new ResponseEntity<List<Employee>>(headers, status);
			return response;
		}
		HttpStatus status = HttpStatus.NOT_FOUND;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Employee not found successfully.");
		ResponseEntity<List<Employee>> response = new ResponseEntity<List<Employee>>(headers, status);
		return response;
	}
	
//	@RequestMapping(path = "get-all-leaves/{eid}", method = RequestMethod.GET, produces = "application/json")
//	public ResponseEntity<Integer> getAllEmps(@PathVariable(name = "eid") Integer eid) {
//		Integer leave = employeeService.getleavebalancebyempid(eid);
//		HttpStatus status = HttpStatus.OK;
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("message", "Leaves found successfully");
//		ResponseEntity<Integer> response = new ResponseEntity<>(leave,headers,status);
//		return response;
//	}
	
	@RequestMapping(path = "get-all-leaves/{eid}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Leave> getAllEmps(@PathVariable(name = "eid") Integer eid) {
		Leave leave = employeeService.getleavebalancebyempid(eid);
		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Leaves found successfully");
		ResponseEntity<Leave> response = new ResponseEntity<>(leave,headers,status);
		return response;
	}

	@RequestMapping(path = "apply-leave/{eid}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Leave> addLeave(@PathVariable Integer eid, @RequestBody Leave leave) {
      Leave createdLeave = employeeService.applyLeave(eid, leave.getLeaveType(),leave.getNumDays(),leave.getLeaveDate());
      if(createdLeave!=null) {
    	  HttpStatus status = HttpStatus.OK;
          HttpHeaders headers = new HttpHeaders();
          headers.add("message", "Leave request created successfully. Status: pending");
          ResponseEntity<Leave> response = new ResponseEntity<>(createdLeave, headers, status);
          LOG.info("Leave request created for employee with eid " + eid + ": " + createdLeave.toString());
          return response;
      }else {
    	HttpStatus status = HttpStatus.NOT_FOUND;
  		HttpHeaders headers = new HttpHeaders();
  		headers.add("message", "Employee not found");
  		ResponseEntity<Leave> response = new ResponseEntity<>(createdLeave,headers, status);
  		return response;
      }
     
  }
	
	@RequestMapping(path = "update-password-by-empId/{eid}/{password}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> updatePassword(@PathVariable(name = "eid") Integer eid,@PathVariable(name = "password") String password) {
		boolean updated = employeeService.employeeUpdatePassword(eid, password);
		if(updated) {
			HttpStatus status = HttpStatus.OK;
			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "Employees password updated successfully.");
			ResponseEntity<Boolean> response = new ResponseEntity<>(updated,headers, status);
			return response;
		}
		else {
			HttpStatus status = HttpStatus.NOT_FOUND;
			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "Employee not found");
			ResponseEntity<Boolean> response = new ResponseEntity<>(updated,headers, status);
			return response;
		}
		
	}
	
	@RequestMapping(path = "add-attendance/{eid}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Attendance> addAttendance(@PathVariable(name = "eid") Integer eid, @RequestBody Attendance attendance) {
		Attendance attObj = employeeService.addAttendance(eid, attendance.getLoginTime(), attendance.getLogoutTime(), attendance.getDate());
		if(attObj!=null) {
			HttpStatus status = HttpStatus.OK;
			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "Attendance added successfully.");
			ResponseEntity<Attendance> response = new ResponseEntity<Attendance>(attObj, headers, status);
			LOG.info(attObj.toString());
			return response;
		}
		else {
			HttpStatus status = HttpStatus.NOT_FOUND;
			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "Employee not found");
			ResponseEntity<Attendance> response = new ResponseEntity<>(attObj,headers, status);
			return response;
		}
		
	}
	
//	@RequestMapping(path = "get-percentage-attendance/{eid}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
//	public ResponseEntity<Double> getAttendancePercentageByEId(@PathVariable(name = "eid") Integer eid) {
//		Double attObj = employeeService.getAttendancePercentageByEId(eid);
//		if(attObj!=null) {
//			HttpStatus status = HttpStatus.OK;
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("message", "Attendance percentage retreived successfully.");
//			ResponseEntity<Double> response = new ResponseEntity<Double>(attObj, headers, status);
//			LOG.info(attObj.toString());
//			return response;
//		}
//		else {
//			HttpStatus status = HttpStatus.NOT_FOUND;
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("message", "Employee not found");
//			ResponseEntity<Double> response = new ResponseEntity<>(null,headers, status);
//			return response;
//		}
//		
//	}
//	
	
	@RequestMapping(path = "get-percentage-attendance/{eid}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Map<String, Double>> getAttendancePercentageByEId(@PathVariable(name = "eid") Integer eid) {
		Map<String, Double> attObj = employeeService.getAttendancePercentageByEId(eid);
		if(attObj!=null) {
			HttpStatus status = HttpStatus.OK;
			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "Attendance percentage retreived successfully.");
			ResponseEntity<Map<String, Double>> response = new ResponseEntity<Map<String, Double>>(attObj, headers, status);
			LOG.info(attObj.toString());
			return response;
		}
		else {
			HttpStatus status = HttpStatus.NOT_FOUND;
			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "Employee not found");
			ResponseEntity<Map<String, Double>> response = new ResponseEntity<>(null,headers, status);
			return response;
		}
		
	}
	
//	@RequestMapping(path = "getlogin/{eid}", method = RequestMethod.GET, produces = "application/json")
//	public ResponseEntity<Map<String, String>> getLoginDetailsByEId(@PathVariable(name = "eid") Integer eid) {
//		Map<String, String> empObj = employeeService.getLoginDetailsByEId(eid);
//		if(empObj!=null) {
//			HttpStatus status = HttpStatus.OK;
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("message", "Login details retreived successfully.");
//			ResponseEntity<Map<String, String>> response = new ResponseEntity<Map<String, String>>(empObj, headers, status);
//			LOG.info(empObj.toString());
//			return response;
//		}
//		else {
//			HttpStatus status = HttpStatus.NOT_FOUND;
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("message", "Employee not found");
//			ResponseEntity<Map<String, String>> response = new ResponseEntity<>(null,headers, status);
//			return response;
//		}
//		
//	}
}
