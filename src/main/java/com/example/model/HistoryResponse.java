package com.example.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class HistoryResponse {

	@Id
	int sNo;
	String bossName;
	int noOfDays;
	Date requestedDate;
	String reason;
	@Enumerated(EnumType.STRING)
	Status conformStatus;
	
}
