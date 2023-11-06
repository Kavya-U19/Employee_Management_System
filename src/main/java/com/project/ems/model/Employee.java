package com.project.ems.model;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Employee_Table")
@Data
@NoArgsConstructor
public class Employee {
	
	@Id 
	@Column(name = "Employee_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eid;

	@Column(name = "First_Name")
	@Length(min = 4, max = 20, message = "Length should be 4 to 20 characters!")
	private String firstName;
	
	@Column(name = "Last_Name")
	@Length(min = 4, max = 20, message = "Length should be 4 to 20 characters!")
	private String lastName;
	
	@Column(name = "DOB")
	private Date DOB;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Sex")
	private String sex;
	
	@Column(name="Organization")
	private String organization;
	
	@Column(name="Address")
	private String address;

	@Column(name = "Date_Of_Joining")
	private Date dateOfJoining;
	
	@Column(name = "Department")
	private String department;
	
	@OneToOne
	@JoinColumn(name = "Role")
	private Salary role;
	
	@Column(name = "Appraisal")
	private Integer appraisal;
	
	@Column(name = "Salary")
	private Double salary;
	
	@ManyToOne
	@JoinColumn(name = "HR_Id")
	private HR HRId;


}
