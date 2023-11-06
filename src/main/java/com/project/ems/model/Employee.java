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
	
	
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Date getDOB() {
		return DOB;
	}


	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}


	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Salary getRole() {
		return role;
	}

	public void setRole(Salary role) {
		this.role = role;
	}

	public Integer getAppraisal() {
		return appraisal;
	}

	public void setAppraisal(Integer appraisal) {
		this.appraisal = appraisal;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}


	public HR getHRId() {
		return HRId;
	}

	public void setHRId(HR hRId) {
		HRId = hRId;
	}

	public Employee(@Length(min = 4, max = 20, message = "Length should be 4 to 20 characters!") String firstName,
			@Length(min = 4, max = 20, message = "Length should be 4 to 20 characters!") String lastName, Date dOB,
			String password, String email, String sex, String organization, String address, Date dateOfJoining,
			String department, Salary role, Integer appraisal, Double salary, HR hRId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		DOB = dOB;
		this.password = password;
		this.email = email;
		this.sex = sex;
		this.organization = organization;
		this.address = address;
		this.dateOfJoining = dateOfJoining;
		this.department = department;
		this.role = role;
		this.appraisal = appraisal;
		this.salary = salary;
		HRId = hRId;
	}


}
