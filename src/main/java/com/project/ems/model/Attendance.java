package com.project.ems.model;

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
	private Integer loginTime;
	
	@Column(name = "Logout_Time")
	private Integer logoutTime;
	
	@Column(name = "Remaining_Days")
	private Integer remainingDays;
}
