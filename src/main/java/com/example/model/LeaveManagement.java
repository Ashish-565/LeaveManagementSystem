package com.example.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class LeaveManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int leaveId;
	int bossId;
	int empId;
	String reason;
	int noOfDays;
	Date requestedDate; 
	@Enumerated(EnumType.STRING)
	Status conformStatus;
	
}
