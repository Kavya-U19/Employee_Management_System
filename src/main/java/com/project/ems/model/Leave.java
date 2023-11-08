package com.project.ems.model;


import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Leave_Table")
@Data
@NoArgsConstructor
public class Leave {
	
	@Column(name = "Leave_Type")
	private String leaveType;
	
    @Id
    @Column(name = "Employee_Id")
	private Integer Employee_id;
	
	@Column(name = "Leave_Status")
	private String leaveStatus;
	
	@Column(name = "Leave_Balance")
	private Integer leaveBalance;
	
	@Column(name = "Paid_Leave")
	private Integer paidLeave;
	
	@Column(name = "Sick_Leave")
	private Integer sickLeave;
	
	@Column(name = "leave_date")
	private List<Date> leaveDate;//do this getter setter
	
	@Column(name = "num_days")
	private Integer numDays;

	public String getLeaveType() {
		return leaveType;
	}
	

	public Integer getEmployee_id() {
		return Employee_id;
	}


	public void setEmployee_id(Integer employee_id) {
		Employee_id = employee_id;
	}


	public List<Date> getLeaveDate() {
		return leaveDate;
	}


	public void setLeaveDate(List<Date> leaveDate) {
		this.leaveDate = leaveDate;
	}


	public Integer getNumDays() {
		return numDays;
	}


	public void setNumDays(Integer numDays) {
		this.numDays = numDays;
	}


	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public Integer getLeaveBalance() {
		return leaveBalance;
	}

	public void setLeaveBalance(Integer leaveBalance) {
		this.leaveBalance = leaveBalance;
	}

	public Integer getPaidLeave() {
		return paidLeave;
	}

	public Leave() {
		super();
	}

	public void setPaidLeave(Integer paidLeave) {
		this.paidLeave = paidLeave;
	}

	public Leave(Integer employee_id, Integer leaveBalance, Integer paidLeave, Integer sickLeave) {
		super();
		Employee_id = employee_id;
		this.leaveBalance = leaveBalance;
		this.paidLeave = paidLeave;
		this.sickLeave = sickLeave;
	}

	public Integer getSickLeave() {
		return sickLeave;
	}

	public void setSickLeave(Integer sickLeave) {
		this.sickLeave = sickLeave;
	}


}
//noofdaysleave