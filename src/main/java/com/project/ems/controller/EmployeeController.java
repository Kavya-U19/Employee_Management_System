package com.project.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.ems.model.Employee;
import com.project.ems.service.EmployeeService;

@Controller
public class EmployeeController {
	
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
	

}
