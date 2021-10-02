package com.example.service;

import java.util.Date;
import java.util.List;

import com.example.model.HistoryResponse;
import com.example.model.LeaveResponse;
import com.example.model.Status;

public interface LeaveManagementService {

	Status getRequestStatus(int empId, Date reqDate, int reqLeaves, String reason); 
	
	List<LeaveResponse> getPendingLeaves();
	
	List<HistoryResponse> getAllEmployeeLeaves(int empId);
	
}
