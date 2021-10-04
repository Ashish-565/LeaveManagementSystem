package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.HistoryResponse;

@Repository
public interface HistoryResponseRepository extends JpaRepository<HistoryResponse, Integer>{

	@Query(value="select LeaveManagement.leaveId as sNo, "
			+ "b.bossName, "
			+ "LeaveManagement.noOfDays, "
			+ "LeaveManagement.requestedDate, "
			+ "LeaveManagement.reason, "
			+ "LeaveManagement.conformStatus "
			+ "from LeaveManagement left join (select * from Boss)b on LeaveManagement.bossId=b.bossId "
			+ "where LeaveManagement.empId=?1"
			, nativeQuery = true)
	List<HistoryResponse> employeeHistory(int empId);
	
}
