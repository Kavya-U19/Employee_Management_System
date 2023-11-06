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
import com.project.ems.service.HRService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("hr")
@CrossOrigin(origins = "*")
public class HRController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	HRService hrService;
	
	@RequestMapping(path = "get-hr-exist/{eid}/{password}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Employee>> getEmpByName(@PathVariable(name = "eid") Integer eid,@PathVariable(name = "password") String password) {
		boolean found = hrService.findHRExist(eid, password);
		if(found) {
			HttpStatus status = HttpStatus.OK;
			HttpHeaders headers = new HttpHeaders();
			headers.add("message", "Employees found successfully.");
			ResponseEntity<List<Employee>> response = new ResponseEntity<List<Employee>>(headers, status);
			return response;
		}
		HttpStatus status = HttpStatus.NOT_FOUND;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "HR not found successfully.");
		ResponseEntity<List<Employee>> response = new ResponseEntity<List<Employee>>(headers, status);
		return response;
	}
	
	
	@RequestMapping(path = "get-all-emps", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Employee>> getAllEmps() {
		List<Employee> empList = hrService.findAllEmp();
		HttpStatus status = HttpStatus.OK;
		ResponseEntity<List<Employee>> response = new ResponseEntity<List<Employee>>(empList, /* headers, */ status);
		LOG.info(Integer.toString(empList.size()));
		return response;
	}
	
	@RequestMapping(path = "add-emp", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Employee> addEmp(@RequestBody @Valid Employee emp) {
		Employee empObj = hrService.addEmp(emp);
		HttpStatus status = HttpStatus.CREATED;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Employee created successfully.");
		ResponseEntity<Employee> response = new ResponseEntity<Employee>(empObj, headers, status);
		LOG.info(empObj.toString());
		return response;
	}
	
	@RequestMapping(path = "delete-emp/{empid}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Employee> deleteEmp(@PathVariable(name = "empid") Integer eid) {
		hrService.deleteEmployee(eid);
		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Employee deleted successfully.");
		ResponseEntity<Employee> response = new ResponseEntity<Employee>(headers, status);
		return response;
	}

}
