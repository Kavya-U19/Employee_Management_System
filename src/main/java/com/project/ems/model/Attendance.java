package com.project.ems.model;

=======
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
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss.SSSZ")
	private Date loginTime;
	
	@Column(name = "Logout_Time")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss.SSSZ")	
	private Date logoutTime;
	
	@Column(name="Date")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date date;
	
	@Column(name = "No. of days attended")
	private Integer days;
	
	

	public Attendance() {
		super();
	}

	public Integer getEmployee_id() {
		return Employee_id;
	}

	public void setEmployee_id(Integer employee_id) {
		Employee_id = employee_id;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}
	
	

}
