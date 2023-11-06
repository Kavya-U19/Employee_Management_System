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
	
	@Column(name = "Gratuity")
	private Integer gratuity;

}
