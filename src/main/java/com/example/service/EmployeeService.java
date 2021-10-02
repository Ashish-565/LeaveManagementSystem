package com.example.service;

import java.util.List;

import com.example.model.Employee;
import com.example.model.HistoryResponse;

public interface EmployeeService{

	Employee getEmployee(int id);
	
	List<HistoryResponse> getEmployeeHistory(int empId);
	
}
