package com.project.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.ems.model.Attendance;
import com.project.ems.service.AttendanceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @GetMapping("/{employeeId}")
    public ResponseEntity<Attendance> getAttendance(@PathVariable(name = "employeeId") Integer employeeId) {
        Attendance attendance = attendanceService.getAttendanceByEmployeeId(employeeId);
        if (attendance != null) {
            return new ResponseEntity<>(attendance, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<Attendance> addAttendance(@RequestBody @Valid Attendance attendance) {
        Attendance addedAttendance = attendanceService.addAttendance(attendance);
        return new ResponseEntity<>(addedAttendance, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Attendance> updateAttendance(@RequestBody @Valid Attendance attendance) {
        Attendance updatedAttendance = attendanceService.updateAttendance(attendance);
        if (updatedAttendance != null) {
            return new ResponseEntity<>(updatedAttendance, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
//login logout empid login time and logout time give()