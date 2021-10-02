package com.example.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class LeaveResponse {

	@Id
	int sNo;
	String empName;
	String reason;
	int noOfDays;
	Date requestedDate;
	
}
