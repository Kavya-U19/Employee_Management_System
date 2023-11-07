package com.project.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ems.model.Salary;
import com.project.ems.repository.SalaryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SalaryService {
	
	@Autowired
	SalaryRepository salaryRepo;
	
	public List<Salary> findAllSalary() {
		return salaryRepo.findAll();
	}
	
	public List<Salary> findSalaryByRole(String role) {
		List<Salary> s=salaryRepo.findByRole(role);
		if(s.size()>0) {
			return s;
		}
		return null;
	}
	
	public Salary addSalary(Salary s) {
		return salaryRepo.save(s);
	}
	
	public boolean updateSalary(Salary salary) {
		List<Salary> s=salaryRepo.findByRole(salary.getRole());
		if(s.size()>0) {
			Salary salData=s.get(0);
			salData.setBasicPay(salary.getBasicPay());
			salData.setPF(0.12*(salData.getBasicPay()));
			salData.setGratuity(0.05*(salData.getBasicPay()));
			salaryRepo.save(salData);
			return true;
		}
		return false;
	}
	
//	public boolean checkUpdateSalary(Salary salary) {
//		salaryRepo.save(salary);
//		return true;
//	}
	
	public void deleteSalary(String role) {
		salaryRepo.deleteByRole(role);
	}

}
