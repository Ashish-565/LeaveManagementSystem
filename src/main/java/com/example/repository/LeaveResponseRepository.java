package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.LeaveResponse;

@Repository
public interface LeaveResponseRepository extends JpaRepository<LeaveResponse, Integer>{

	@Query(value = "select LeaveManagement.leaveId as sNo, "
			+ "Employee.empName, "
			+ "LeaveManagement.reason, "
			+ "LeaveManagement.noOfDays, "
			+ "LeaveManagement.requestedDate "
			+ "from Employee, LeaveManagement "
			+ "where LeaveManagement.empId=Employee.empId and "
			+ "LeaveManagement.conformStatus='PENDING'"
			, nativeQuery = true)
	List<LeaveResponse> findByStatus();
	
}
