package com.example.service;

import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Employee;
import com.example.model.HistoryResponse;
import com.example.model.LeaveManagement;
import com.example.model.LeaveResponse;
import com.example.model.Status;
import com.example.repository.EmployeeRepository;
import com.example.repository.HistoryResponseRepository;
import com.example.repository.LeaveManagementRepository;
import com.example.repository.LeaveResponseRepository;

import org.slf4j.Logger;

@Service
public class LeaveManagementServiceImpl implements LeaveManagementService{

	private static Logger logger = LoggerFactory.getLogger(LeaveManagementServiceImpl.class);
	
	@Autowired
	LeaveManagementRepository leaveManagementRepository;
	
	@Autowired
	EmployeeRepository employeeRepository; 
	
	@Autowired
	LeaveResponseRepository leaveResponseRepository;
	
	@Autowired
	HistoryResponseRepository historyResponseRepository;
	
	@Override
	public Status getRequestStatus(int empId, Date reqDate, int reqLeaves, String reason) {
		@SuppressWarnings("deprecation")
		int year = reqDate.getYear();
		List<LeaveManagement> leaves = leaveManagementRepository.findAllByEmpIdAndLeaveMonth(empId, year);
		int totalLeaves = 0;
		for(LeaveManagement leave: leaves) {
			if(Status.CONFORMED.equals(leave.getConformStatus())) {
				totalLeaves += leave.getNoOfDays();
			}
		}
		
		Employee emp = employeeRepository.findById(empId).orElseThrow(); 
		int empRemainingLeaves = emp.getLeavesRemaining();
		
		LeaveManagement leave = new LeaveManagement();
		if(totalLeaves+reqLeaves > empRemainingLeaves) {
			return Status.REJECTED;
		}else {
			leave.setEmpId(empId);
			leave.setRequestedDate(reqDate);
			leave.setNoOfDays(reqLeaves);
			leave.setReason(reason);
			leave.setConformStatus(Status.PENDING);
			leaveManagementRepository.save(leave);
			
			emp.setLeavesRemaining(emp.getLeavesRemaining()-reqLeaves);
			employeeRepository.save(emp);
			return Status.PENDING;
		}
	}
	
	@Override
	public List<LeaveResponse> getPendingLeaves(){
		List<LeaveResponse> leaves= leaveResponseRepository.findByStatus();
		logger.info(""+leaves);
		return leaves;
	}

	@Override
	public List<HistoryResponse> getAllEmployeeLeaves(int empId) {
		List<HistoryResponse> allLeaves = historyResponseRepository.employeeHistory(empId);
		logger.info(""+allLeaves);
		return allLeaves;
	}

}