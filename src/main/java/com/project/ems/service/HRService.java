package com.project.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ems.model.Employee;
import com.project.ems.model.HR;
import com.project.ems.model.Leave;
import com.project.ems.model.Salary;
import com.project.ems.repository.EmployeeRepository;
import com.project.ems.repository.HRRepository;
import com.project.ems.repository.LeaveRepository;
import com.project.ems.repository.SalaryRepository;

@Service
public class HRService {
	
	@Autowired
	HRRepository hrRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	LeaveRepository leaveRepo;
	
	@Autowired
	SalaryRepository salaryRepo;
	
	Integer paidLeave=20;
	Integer sickLeave=12;
	Integer leaveBal=32;
	
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
	

	public List<Employee> findAllEmp(){
		List<Employee> emp=empRepo.findAll();
		return emp;	
	}
	
	public Employee addEmp(Employee e) {
		empRepo.save(e);
		addLeave(e.getEid());
		e.setSalary(totalSalary(e.getEid()));
		return empRepo.save(e);
	}
	
	public Double totalSalary(Integer eid) {
		Double salary;
		Optional<Employee> e=empRepo.findById(eid);
		if(e.isPresent()) {
			Employee emp=e.get();
			Salary role=emp.getRole();
			Integer appraisal=emp.getAppraisal();
			List<Salary> salaryComp=salaryRepo.findByRole(role.getRole());
			Double basicPay = salaryComp.get(0).getBasicPay();
			salary=(double) (basicPay + ((double)((double)(appraisal/100))*basicPay));
			return salary;
		}
		return null;
	}
	
	
	public void addLeave(Integer eid) {
		Leave leave=new Leave(eid,leaveBal,paidLeave,sickLeave);
		leaveRepo.save(leave);
	}
	
	public Employee updateEmployee(Employee e) {
		return empRepo.save(e);
	}
	
	
	public void deleteEmployee(Integer eid) {
		empRepo.deleteById(eid);
		leaveRepo.deleteById(eid);
	}
	
	public boolean giveAppraisalToEmp(Integer eid,Integer appraisal) {
		Optional<Employee> e=empRepo.findById(eid);
		if(e.isPresent()) {
			Employee emp=e.get();
			emp.setAppraisal(appraisal);
			emp.setSalary(totalSalary(eid));
			empRepo.save(emp);
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
