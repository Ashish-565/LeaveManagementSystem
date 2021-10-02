package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Employee;
import com.example.service.EmployeeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/check")
public class CheckLeavesController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping(params = {"empId"})
	public ResponseEntity<?> getLeaves(@RequestParam("empId") int empId){
		
		Employee employee = employeeService.getEmployee(empId);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	
	}
	
}
