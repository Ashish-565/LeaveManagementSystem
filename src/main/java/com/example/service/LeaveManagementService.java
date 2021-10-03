package com.example.service;

import java.util.Date;
import java.util.List;

import com.example.model.Employee;
import com.example.model.HistoryResponse;
import com.example.model.LeaveResponse;

public interface LeaveManagementService {

	Employee getEmployeeAfterRequest(int empId, Date reqDate, int reqLeaves, String reason); 
	
	void grantLeave(int leaveId);
	
	void rejectLeave(int leaveId);
	
	List<LeaveResponse> getPendingLeaves();
	
	List<HistoryResponse> getAllEmployeeLeaves(int empId);
	
}
