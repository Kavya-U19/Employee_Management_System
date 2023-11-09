package com.project.ems.model;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Attendance_Table")
@Data
@NoArgsConstructor
public class Attendance {
	
	 @Id
	 @Column(name = "Employee_Id")
	 private Integer Employee_id;
	
	@Column(name = "Login_Time")
	private String loginTime;
	
	@Column(name = "Logout_Time")
	private String logoutTime;
	
	@Column(name="Date")
	private String date;
	
	@Column(name = "No. of days attended")
	private Integer days;
	
	

	public Attendance(Integer employee_id, Integer days) {
		super();
		Employee_id = employee_id;
		this.days = days;
	}

	public Attendance() {
		super();
	}

	public Integer getEmployee_id() {
		return Employee_id;
	}

	public void setEmployee_id(Integer employee_id) {
		Employee_id = employee_id;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}
	
	

}
