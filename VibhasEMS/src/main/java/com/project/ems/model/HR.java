package com.project.ems.model;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HR_Table")
@Data
@NoArgsConstructor
public class HR {
	
	@Id 
	@Column(name = "HR_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer HRId;
	
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

}
