package com.project.ems.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.ems.model.Attendance;
import com.project.ems.model.Employee;
import com.project.ems.service.EmployeeService;

import jakarta.validation.Valid;

@Controller
public class EmployeeController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EmployeeService empService;
	
	@RequestMapping(path = "check-emp-exist/{eid}/{password}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Employee>> getEmpByName(@PathVariable(name = "eid") Integer eid,@PathVariable(name = "password") String password) {
		boolean found = empService.findEmployeeExist(eid, password);
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
	
	@RequestMapping(path = "get-all-leaves/{eid}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Integer> getAllEmps(@PathVariable(name = "eid") Integer eid) {
		Integer leave = empService.getleavebalancebyempid(eid);
		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Leaves found successfully");
		ResponseEntity<Integer> response = new ResponseEntity<>(leave,headers,status);
		return response;
	}
	
	@RequestMapping(path = "update-password-by-empId/{eid}/{password}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> updatePassword(@PathVariable(name = "eid") Integer eid,@PathVariable(name = "password") String password) {
		boolean updated = empService.employeeUpdatePassword(eid, password);
		if(updated) {
			HttpStatus status = HttpStatus.OK;
			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "Employees password updated successfully.");
			ResponseEntity<Boolean> response = new ResponseEntity<>(updated,headers, status);
			return response;
		}
		HttpStatus status = HttpStatus.NOT_FOUND;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Employee not found successfully.");
		ResponseEntity<Boolean> response = new ResponseEntity<>(updated,headers, status);
		return response;
	}
	
	@RequestMapping(path = "add-attendance/{eid}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Attendance> addAttendance(@RequestBody @Valid Integer eid, Date loginTime, Date logoutTime, Date date) {
		Attendance attObj = empService.addAttendance(eid, loginTime, logoutTime, date);
		HttpStatus status = HttpStatus.CREATED;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Attendance added successfully.");
		ResponseEntity<Attendance> response = new ResponseEntity<Attendance>(attObj, headers, status);
		LOG.info(attObj.toString());
		return response;
	}
	
	@RequestMapping(path = "get-percentage-attendance/{eid}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Attendance> getAttendancePercentageByEId(@PathVariable(name = "eid") Integer eid) {
		Object attObj = empService.getAttendancePercentageByEId(eid);
		HttpStatus status = HttpStatus.CREATED;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Attendance percentage retreived successfully.");
		ResponseEntity<Attendance> response = new ResponseEntity<Attendance>((Attendance) attObj, headers, status);
		LOG.info(attObj.toString());
		return response;
	}
	
}
