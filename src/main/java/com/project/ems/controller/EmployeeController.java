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
import com.project.ems.model.Leave;
import com.project.ems.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("employee")
@CrossOrigin(origins = "*")
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
            ResponseEntity<Employee> response = new ResponseEntity<Employee>(employee, headers, status);
            return response;
        }
        HttpStatus status = HttpStatus.NOT_FOUND;
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Employee not found.");
        ResponseEntity<Employee> response = new ResponseEntity<Employee>(headers, status);
        return response;
    }

//    @RequestMapping(path = "get-all-employees", method = RequestMethod.GET, produces = "application/json")
//    public ResponseEntity<List<Employee>> getAllEmployees() {
//        List<Employee> employees = employeeService.getAllEmployees();
//        HttpStatus status = HttpStatus.OK;
//        ResponseEntity<List<Employee>> response = new ResponseEntity<List<Employee>(employees, status);
//        LOG.info("Total employees found: " + employees.size());
//        return response;
//    }

//    @RequestMapping(path = "add-employee", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
//    public ResponseEntity<Employee> addEmployee(@RequestBody @Valid Employee employee) {
//        Employee createdEmployee = employeeService.addEmployee(employee);
//        HttpStatus status = HttpStatus.CREATED;
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("message", "Employee created successfully.");
//        ResponseEntity<Employee> response = new ResponseEntity<Employee>(createdEmployee, headers, status);
//        LOG.info("Employee created: " + createdEmployee.toString());
//        return response;
//    }

//    @RequestMapping(path = "add-leave/{eid}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
//    public ResponseEntity<String> addLeave(@PathVariable Integer eid, @RequestBody Leave leave) {
//        Leave createdLeave = employeeService.addLeave(eid, leave);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("message", "Leave request created successfully. Status: pending");
//        ResponseEntity<String> response = new ResponseEntity<>("Request pending", headers, HttpStatus.CREATED);
//        LOG.info("Leave request created for employee with eid " + eid + ": " + createdLeave.toString());
//        return response;

    
    
//    @RequestMapping(path = "delete-employee/{eid}", method = RequestMethod.DELETE, produces = "application/json")
//    public ResponseEntity<Employee> deleteEmployee(@PathVariable(name = "eid") Integer eid) {
//        employeeService.deleteEmployee(eid);
//        HttpStatus status = HttpStatus.OK;
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("message", "Employee deleted successfully.");
//        ResponseEntity<Employee> response = new ResponseEntity<Employee>(headers, status);
//        return response;
//    }
//    

    
    @RequestMapping(path = "update-employee", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Employee> updateEmployee(@RequestBody @Valid Employee employee) {
    	employeeService.updateEmployee(employee);
    	HttpStatus status = HttpStatus.OK;
    	 HttpHeaders headers = new HttpHeaders();
         headers.add("message", "Employee updated successfully.");
         ResponseEntity<Employee> response = new ResponseEntity<Employee>(headers, status);
         return response;
    }
  @RequestMapping(path = "apply-leave/{eid}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
  public ResponseEntity<Leave> addLeave(@PathVariable Integer eid, @RequestBody Leave leave) {
      Leave createdLeave = employeeService.applyLeave(eid, leave.getLeaveType(),leave.getNumDays(),leave.getLeaveDate());
      HttpStatus status = HttpStatus.OK;
      HttpHeaders headers = new HttpHeaders();
      headers.add("message", "Leave request created successfully. Status: pending");
      ResponseEntity<Leave> response = new ResponseEntity<>(createdLeave, headers, status);
      LOG.info("Leave request created for employee with eid " + eid + ": " + createdLeave.toString());
      return response;
  }
}
