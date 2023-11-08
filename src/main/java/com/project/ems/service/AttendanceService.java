package com.project.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ems.model.Attendance;
import com.project.ems.repository.AttendanceRepository;

import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;

    public Attendance getAttendanceByEmployeeId(Integer employeeId) {
        Optional<Attendance> attendance = attendanceRepository.findById(employeeId);
        return attendance.orElse(null);
    }

    public Attendance addAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public Attendance updateAttendance(Attendance attendance) {
        if (attendanceRepository.existsById(attendance.findById())) {
            return attendanceRepository.save(attendance);
        }
        return null;
    }

    public void deleteAttendance(Integer employeeId) {
        attendanceRepository.deleteById(employeeId);
    }
}
