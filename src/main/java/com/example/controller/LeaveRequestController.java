package com.example.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Employee;
import com.example.model.LeaveResponse;
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

		Employee employee = leaveManagementService.getEmployeeAfterRequest(empId, date, reqLeaves, reason);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}
	
	@PostMapping("/grant")
	public ResponseEntity<?> grantLeave(@RequestBody RequestWithLeaveIdAndBossId requestWithLeaveIdAndBossId){
		int leaveId = requestWithLeaveIdAndBossId.getId();
		int bossId = requestWithLeaveIdAndBossId.getBossId();
		leaveManagementService.grantLeave(leaveId, bossId);
		List<LeaveResponse> leaves = leaveManagementService.getPendingLeaves();
		return ResponseEntity.status(HttpStatus.OK).body(leaves);
	}
	
	@PostMapping("/reject")
	public ResponseEntity<?> rejectLeave(@RequestBody RequestWithLeaveIdAndBossId requestWithLeaveIdAndBossId){
		int leaveId = requestWithLeaveIdAndBossId.getId();
		int bossId = requestWithLeaveIdAndBossId.getBossId();
		leaveManagementService.rejectLeave(leaveId, bossId);
		List<LeaveResponse> leaves = leaveManagementService.getPendingLeaves();
		return ResponseEntity.status(HttpStatus.OK).body(leaves);
	}
	
}
