package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.LeaveResponse;
import com.example.service.LeaveManagementService;

@CrossOrigin("*")
@RestController
@RequestMapping("/leaves")
public class LeavesController {

	@Autowired
	LeaveManagementService leaveManagementService;
	
	@GetMapping
	public ResponseEntity<?> getPendingLeaves(){
		
		List<LeaveResponse> leaves = leaveManagementService.getPendingLeaves();
		return ResponseEntity.status(HttpStatus.OK).body(leaves);
		
	}
	
}
