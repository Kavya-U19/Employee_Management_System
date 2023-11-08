package com.project.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ems.model.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, String>{
	public abstract List<Salary> findByRole(String role);

	public abstract void deleteByRole(String role);

}
