package com.project.ems.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ems.model.Employee;
import com.project.ems.model.HR;
import com.project.ems.model.Leave;
import com.project.ems.model.Salary;
import com.project.ems.model.Attendance;
import com.project.ems.repository.AttendanceRepository;
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
	
	@Autowired
	AttendanceRepository attendRepo;
	
	Integer paidLeave=20;
	Integer sickLeave=12;
	Integer leaveBal=32;
	
//    public Object[] getHRIdAndPasswordByEmail(String email) {
//        return hrRepo.findHRIdAndPasswordByEmail(email);
//    }
	
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
	
	 public HR addHR(HR hr) {
	        return hrRepo.save(hr);
	 }
	
	public Employee addEmp(Employee e) {
		empRepo.save(e);
		addLeaveInitial(e.getEid());
		e.setSalary(totalSalary(e.getEid()));
		addAttendanceInitial(e.getEid());
		return empRepo.save(e);
	}
	
	public void addLeaveInitial(Integer eid) {
		Leave leave=new Leave(eid,leaveBal,paidLeave,sickLeave);
		leaveRepo.save(leave);
	}
	
	public void addAttendanceInitial(Integer eid) {
		Attendance attendance=new Attendance(eid,0);
		attendRepo.save(attendance);
	}
	
	public Double totalSalary(Integer eid) {
		Double salary;
		Optional<Employee> e=empRepo.findById(eid);
		if(e.isPresent()) {
			Employee emp=e.get();
			Salary role=emp.getRole();
			Double appraisal=emp.getAppraisal();
			List<Salary> salaryComp=salaryRepo.findByRole(role.getRole());
			Double basicPay = salaryComp.get(0).getBasicPay();
			salary=(double) (basicPay + ((double)((double)(appraisal/100))*basicPay));
			return salary;
		}
		return null;
	}
	
	public void deleteEmployee(Integer eid) {
		empRepo.deleteById(eid);
		leaveRepo.deleteById(eid);
		attendRepo.deleteById(eid);
	}
	
	public List<Leave> viewAllLeaveRequests() {
        List<Leave> leaveRequests = leaveRepo.findAll();
        List<Leave> leaveBal=new ArrayList<Leave>();
        for(Leave leave:leaveRequests) {
        	if(leave.getLeaveStatus()!=null && leave.getLeaveStatus().equalsIgnoreCase("Pending")) {
        		leaveBal.add(leave);
        	}
        }
        return leaveBal;
	}
	
	public HR getHRDetails(Integer eid) {
	    Optional<HR> h = hrRepo.findById(eid);
	    if (h.isPresent()) {
	        return h.get();
	    } else {
	        return null;
	    }
	}
	
	//already present in employee service
//	public Employee getEmployeeById(Integer empid) {
//	    Optional<Employee> emp = empRepo.findById(empid);
//	    if (emp.isPresent()) {
//	        return emp.get();
//	    } else {
//	        return null;
//	    }
//	}
	
	//no controller
	public Double getSalaryDetailsByEmpId(Integer empid) {
	    Optional<Employee> employee = empRepo.findById(empid);
	    if (employee.isPresent()) {
	        return employee.get().getSalary();
	    } else {
	        return null;
	    }
	}
	
	public boolean changeLeaveStatus(Integer eid, String status) {
		Optional<Leave> e=leaveRepo.findById(eid);
		if(e.isPresent()) {
			Leave leave=e.get();
			if(leave.getLeaveStatus().equalsIgnoreCase("Pending")) {
				leave.setLeaveStatus(status);
				Integer days=leave.getNumDays();
				if(status.equalsIgnoreCase("Accept")) {
					if(leave.getLeaveType().equalsIgnoreCase("Paid Leave")) {
						leave.setPaidLeave(leave.getPaidLeave()-days);
						leave.setLeaveBalance(leave.getLeaveBalance()-days);
					}
					else
						leave.setSickLeave(leave.getSickLeave()-days);
				}
				leaveRepo.save(leave);
				return true;
			}
			
		}
		return false;
	}
	
	public boolean giveAppraisalToEmp(Integer eid,Double appraisal) {
		Optional<Employee> e=empRepo.findById(eid);
		if(e.isPresent()) {
			Employee emp=e.get();
			emp.setAppraisal(appraisal);
			empRepo.save(emp);
			emp.setSalary(totalSalary(eid));
			empRepo.save(emp);
			return true;
		}
		return false;
	}
	
	public Employee updateEmployee(Employee e) {
		empRepo.save(e);
		Optional<Employee> emp=empRepo.findById(e.getEid());
		Employee employee=emp.get();
		employee.setSalary(totalSalary(e.getEid()));
		return empRepo.save(employee);
	}
	
//	public Map<String, Object> getLoginDetailsByEId(String eid) {
//	Object[] h = hrRepo.findHRIdAndPasswordByEmail(eid);
//	Optional<Employee> e = empRepo.findByHRId(eid);
//	Map<String, String> data = new HashMap<>();
//	if(h.isPresent()) {
//		HR hrData = h.get();
//		Employee hrInEmp = e.get();
//		HR eUsername = hrInEmp.getHRId();
//		HR ePassword = hrData.getPassword();
//		data.put("Username", eUsername);
//		data.put("Password", ePassword);
//		return data;
//	}
//	return null;
//}
	
	
	//WRT. HR
	//findallLeaves
	//findallAttendance
	
//	WRT. Employee
//	getsalarydetailsbyrole
//	gethr-detailsbyempId
	
	
	

	
	//getempdetailsbyempid add this
	
	//viewallleavereq
	 
	
	//gethrdetails
	//getempid
	//
	
	
	
	
	

	  
	
	
	
	
	
	
	
	
	

}
