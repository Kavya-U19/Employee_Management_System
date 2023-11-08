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
	
	public Salary() {
		super();
	}
	
	@Id 
	@Column(name = "Role")
	private String role;

	@Column(name = "Basic_Pay")
	private Double basicPay;
	
	@Column(name = "PF")
	private double PF;
	
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

	public double getPF() {
		return PF;
	}

	public void setPF(double d) {
		PF = d;
	}

	public double getGratuity() {
		return gratuity;
	}

	public void setGratuity(double d) {
		this.gratuity = d;
	}

	@Column(name = "Gratuity")
	private double gratuity;

	public Salary(String role, Double basicPay, Integer pF, Integer gratuity) {
		super();
		this.role = role;
		this.basicPay = basicPay;
		PF = pF;
		this.gratuity = gratuity;
	}

}
