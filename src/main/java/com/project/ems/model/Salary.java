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
	private Integer basicPay;
	
	@Column(name = "PF")
	private Integer PF;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getBasicPay() {
		return basicPay;
	}

	public void setBasicPay(Integer basicPay) {
		this.basicPay = basicPay;
	}

	public Integer getPF() {
		return PF;
	}

	public void setPF(Integer pF) {
		PF = pF;
	}

	public Integer getGratuity() {
		return gratuity;
	}

	public void setGratuity(Integer gratuity) {
		this.gratuity = gratuity;
	}

	@Column(name = "Gratuity")
	private Integer gratuity;

}
