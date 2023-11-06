package com.project.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ems.model.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer>{

}
