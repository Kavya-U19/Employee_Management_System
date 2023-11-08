package com.project.ems.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ems.model.Attendance;
import com.project.ems.model.Employee;
import com.project.ems.model.Leave;
import com.project.ems.model.Salary;
import com.project.ems.repository.AttendanceRepository;
import com.project.ems.repository.EmployeeRepository;
import com.project.ems.repository.LeaveRepository;
import com.project.ems.repository.SalaryRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository empRepository;
	
	@Autowired
	SalaryRepository salaryRepo;
	
	@Autowired
	LeaveRepository leaveRepo;
	
	@Autowired
	AttendanceRepository attendanceRepo;
	
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
	
//	public boolean setEmpAppraisal(Integer eid,Integer appraisal) {
//		Optional<Employee> e=empRepository.findById(eid);
//		if(e.isPresent()) {
//			Employee emp=e.get();
//			emp.setAppraisal(appraisal);
//			return true;
//		}
//		return false;
//	}
	
	public Integer getleavebalancebyempid(Integer eid) {
		Optional<Leave> empLeave=leaveRepo.findById(eid);
		if(empLeave.isPresent()) {
			return empLeave.get().getLeaveBalance();
			//return empLeave.get().getLeaveBalance();)
		}
		return null;
	}
	
	public boolean employeeUpdatePassword(Integer eid,String password) {
		Optional<Employee> e=empRepository.findById(eid);
		if(e.isPresent()) {
			Employee emp=e.get();
			emp.setPassword(password);
			return true;
		}
		return false;
		 
	}
	
	public Attendance addAttendance(Integer eid, Date loginTime, Date logoutTime, Date date) {
		Optional<Attendance> a = attendanceRepo.findById(eid);
		if(a.isPresent()) {
			Attendance empAtt = a.get();
			empAtt.setLoginTime(loginTime);
			empAtt.setLogoutTime(logoutTime);
			empAtt.setDate(date);
			empAtt.setDays(empAtt.getDays() + 1);
			attendanceRepo.save(empAtt);
		}
		return null;
		// throw exception
		
	}
	
	public Object getAttendancePercentageByEId(Integer eid) {
		Optional<Attendance> a = attendanceRepo.findById(eid);
		if(a.isPresent()) {
			Attendance empAtt = a.get();
			double attPerc = empAtt.getDays() * (100/30);
			return attPerc;
		}
		return null;
	}
	
	
	
	//addleave
	//addattendance
	//getleavebalancebyempid
	//-----
	//getattendancebypercenatagebyempid
	//-----
	//employeeupdatepassword
	
	
	
	

}
