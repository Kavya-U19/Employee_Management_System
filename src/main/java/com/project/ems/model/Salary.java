package com.project.ems.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Salary_Table")
@Data
@NoArgsConstructor
public class Salary {
	
	@Id 
	@Column(name = "Role")
	private String role;

	@Column(name = "Basic_Pay")
	private Double basicPay;
	
	@Column(name = "PF")
	private Double PF;
	
	@Column(name = "Gratuity")
	private Double gratuity;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Double getBasicPay() {
		return basicPay;
	}

	public void setBasicPay(Double basicPay) {
		this.basicPay = basicPay;
	}

	public Double getPF() {
		return PF;
	}

	public void setPF(Double pF) {
		PF = pF;
	}

	public Double getGratuity() {
		return gratuity;
	}

	public void setGratuity(Double gratuity) {
		this.gratuity = gratuity;
	}

	

	public Salary(String role, Double basicPay, Double pF, Double gratuity) {
		super();
		this.role = role;
		this.basicPay = basicPay;
		PF = pF;
		this.gratuity = gratuity;
	}

	public Salary() {
		super();
	}
	
	

}
