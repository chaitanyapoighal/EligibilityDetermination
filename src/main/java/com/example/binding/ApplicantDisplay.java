package com.example.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ApplicantDisplay {

	private String applicantName;
	
	private Long applicantSsn;
	
	private String planname;

	private String planstatus;

	private LocalDate startDate;

	private LocalDate endDate;

	private Integer benefitAmt;

	private String denialreason;
}
