package com.example.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Status;
import com.example.service.LeaveManagementService;

@CrossOrigin("*")
@RestController
@RequestMapping("/request")
public class LeaveRequestController {

	@Autowired
	LeaveManagementService leaveManagementService;
	
	@GetMapping(params = {"id", "noOfDays", "reason", "date"})
	public ResponseEntity<?> requestLeaves(
			@RequestParam("id") int empId, 
			@RequestParam("noOfDays") int reqLeaves, 
			@RequestParam("reason") String reason,
			@RequestParam("date") Date date){

		Status status = leaveManagementService.getRequestStatus(empId, date, reqLeaves, reason);
		return ResponseEntity.status(HttpStatus.OK).body(status);
	}
	
}
