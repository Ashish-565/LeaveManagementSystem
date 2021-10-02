package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.InvalidIdException;
import com.example.exception.ResponseError;
import com.example.model.Boss;
import com.example.model.Employee;
import com.example.service.BossService;
import com.example.service.EmployeeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	EmployeeService employeeService;

	@Autowired
	BossService bossService;

	@PostMapping("/employee")
	public ResponseEntity<?> loginEmployee(@RequestBody LoginRequest loginRequest) {
		int id = loginRequest.getId();
		Employee employee = employeeService.getEmployee(id);
		logger.info(ResponseEntity.status(HttpStatus.OK).body(employee).toString());
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

	@PostMapping("/boss")
	public ResponseEntity<?> loginBoss(@RequestBody LoginRequest loginRequest) {
		int id = loginRequest.getId();
		Boss boss = bossService.getBoss(id);
		logger.info(ResponseEntity.status(HttpStatus.OK).body(boss).toString());
		return ResponseEntity.status(HttpStatus.OK).body(boss);
	}
	
	@ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = {InvalidIdException.class})
    public ResponseError exceptionHandler(Throwable t) {
        ResponseError responseError = new ResponseError();
        responseError.setError(true);
        responseError.setMessage(t.getMessage());
        return responseError;
    }
	
}
