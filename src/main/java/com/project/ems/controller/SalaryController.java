package com.project.ems.controller;

import java.util.List;

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
import com.project.ems.model.Salary;
import com.project.ems.service.SalaryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("salary")
@CrossOrigin(origins = "*")
public class SalaryController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	SalaryService salaryService;
	
	@RequestMapping(path = "get-all-salary", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Salary>> getAllSalary() {
		List<Salary> salList =  salaryService.findAllSalary();
		HttpStatus status = HttpStatus.OK;
		ResponseEntity<List<Salary>> response = new ResponseEntity<List<Salary>>(salList, /* headers, */ status);
		LOG.info(Integer.toString(salList.size()));
		return response;
	}
	
	@RequestMapping(path = "get-salary-by-role/{role}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Salary>> getSalaryByRole(@PathVariable(name = "role") String role) {
		List<Salary> salList =  salaryService.findSalaryByRole(role);
		HttpStatus status = HttpStatus.OK;
		ResponseEntity<List<Salary>> response = new ResponseEntity<List<Salary>>(salList, /* headers, */ status);
		LOG.info(Integer.toString(salList.size()));
		return response;
	}
	
	@RequestMapping(path = "add-salary", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Salary> addEmp(@RequestBody @Valid Salary salary) {
		Salary salaryObj = salaryService.addSalary(salary);
		HttpStatus status = HttpStatus.CREATED;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Salary added successfully.");
		ResponseEntity<Salary> response = new ResponseEntity<Salary>(salaryObj, headers, status);
		LOG.info(salaryObj.toString());
		return response;
	}
	
	@RequestMapping(path = "delete-salary/{role}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Salary> deleteEmp(@PathVariable(name = "role") String role) {
		salaryService.deleteSalary(role);
		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Employee deleted successfully.");
		ResponseEntity<Salary> response = new ResponseEntity<Salary>(headers, status);
		return response;
	}
	
	@RequestMapping(path = "update-salary", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Boolean> updatePassword(@RequestBody Salary salary) {
		boolean updated = salaryService.updateSalary(salary);
		if(updated) {
			HttpStatus status = HttpStatus.OK;
			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "Salary updated successfully.");
			ResponseEntity<Boolean> response = new ResponseEntity<>(updated,headers, status);
			return response;
		}
		HttpStatus status = HttpStatus.NOT_FOUND;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Role not found ");
		ResponseEntity<Boolean> response = new ResponseEntity<>(updated,headers, status);
		return response;
	}

}
