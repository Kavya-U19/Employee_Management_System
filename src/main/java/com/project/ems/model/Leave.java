package com.project.ems.model;


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

	public String getLeaveType() {
		return leaveType;
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

	public void setPaidLeave(Integer paidLeave) {
		this.paidLeave = paidLeave;
	}

	public Integer getSickLeave() {
		return sickLeave;
	}

	public void setSickLeave(Integer sickLeave) {
		this.sickLeave = sickLeave;
	}
	
	
	

}
