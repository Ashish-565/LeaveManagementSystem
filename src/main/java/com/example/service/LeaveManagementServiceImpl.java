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
import com.example.repository.BossRepository;
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
	BossRepository bossRepository;
	
	@Autowired
	LeaveResponseRepository leaveResponseRepository;
	
	@Autowired
	HistoryResponseRepository historyResponseRepository;
	
	@Override
	public Employee getEmployeeAfterRequest(int empId, Date reqDate, int reqLeaves, String reason) {
		@SuppressWarnings("deprecation")
		int year = reqDate.getYear();
		List<LeaveManagement> leaves = leaveManagementRepository.findAllByEmpIdAndLeaveYear(empId, year);
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
			return emp;
		}else {
			leave.setEmpId(empId);
			leave.setRequestedDate(reqDate);
			leave.setNoOfDays(reqLeaves);
			leave.setReason(reason);
			leave.setConformStatus(Status.PENDING);
			leaveManagementRepository.save(leave);
			
			emp.setLeavesRemaining(emp.getLeavesRemaining()-reqLeaves);
			employeeRepository.save(emp);
			return emp;
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
		List<LeaveManagement> employeeLeaves = leaveManagementRepository.findEmployeeLeaves(empId);
		List<HistoryResponse> allLeaves = historyResponseRepository.employeeHistory(empId);
		logger.info(""+allLeaves+" "+employeeLeaves);
		for(int i=0; i<allLeaves.size(); i++) {
			if(allLeaves.get(i).getSNo()==employeeLeaves.get(i).getLeaveId()) {
				int bossId = employeeLeaves.get(i).getBossId();
				logger.info(""+bossId);
				if(bossId==0) {
					allLeaves.get(i).setBossName("-");
				}else {
					allLeaves.get(i).setBossName(bossRepository.findById(bossId).get().getBossName());
				}
			}
		}
		logger.info(""+allLeaves);
		return allLeaves;
	}

	@Override
	public void grantLeave(int leaveId, int bossId) {
		LeaveManagement leave = leaveManagementRepository.getById(leaveId);
		leave.setBossId(bossId);
		leave.setConformStatus(Status.CONFORMED);
		leaveManagementRepository.save(leave);
	}

	@Override
	public void rejectLeave(int leaveId, int bossId) {
		LeaveManagement leave = leaveManagementRepository.getById(leaveId);
		leave.setBossId(bossId);
		leave.setConformStatus(Status.REJECTED);
		leaveManagementRepository.save(leave);
		Employee employee = employeeRepository.getById(leave.getEmpId());
		employee.setLeavesRemaining(employee.getLeavesRemaining()+leave.getNoOfDays());
		employeeRepository.save(employee);
	}

}
