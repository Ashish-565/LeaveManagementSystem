package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.NotAnEmployeeException;
import com.example.model.Employee;
import com.example.model.HistoryResponse;
import com.example.repository.EmployeeRepository;
import com.example.repository.HistoryResponseRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	HistoryResponseRepository historyResponseRepository;
	
	@Override
	public Employee getEmployee(int empId) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(empId);
		Employee employee = optionalEmployee.orElseThrow(()-> new NotAnEmployeeException("You are not an employee"));
		return employee;
	}

	@Override
	public List<HistoryResponse> getEmployeeHistory(int empId) {
		List<HistoryResponse> employeeHistory = historyResponseRepository.employeeHistory(empId);
		return employeeHistory;
	}
	
}
