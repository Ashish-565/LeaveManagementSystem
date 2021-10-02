package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.HistoryResponse;
import com.example.service.LeaveManagementService;

@CrossOrigin("*")
@RestController
@RequestMapping("/history")
public class LeaveHistoryController {

	@Autowired
	LeaveManagementService leaveManagementService;
	
	@GetMapping(params = {"empId"})
	public ResponseEntity<?> getLeaves(
			@RequestParam("empId") int empId){ 
		
		List<HistoryResponse> allLeaves = leaveManagementService.getAllEmployeeLeaves(empId);
		return ResponseEntity.status(HttpStatus.OK).body(allLeaves);
	}
	
}
