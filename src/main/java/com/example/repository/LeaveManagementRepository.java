package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.LeaveManagement;

@Repository
public interface LeaveManagementRepository extends JpaRepository<LeaveManagement, Integer>{
	
	@Query(value="select * from LeaveManagement where LeaveManagement.empId=?1 "
			+ "and year(LeaveManagement.requestedDate)=?2", nativeQuery = true)
	List<LeaveManagement> findAllByEmpIdAndLeaveYear(int empId, int year);

	@Query(value="select * from LeaveManagement where LeaveManagement.empId=?1", nativeQuery = true)
	List<LeaveManagement> findEmployeeLeaves(int empId);
	
}
