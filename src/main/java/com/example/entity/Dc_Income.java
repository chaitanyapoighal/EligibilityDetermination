package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
public class Dc_Income {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer incomeid;
	
	private Integer caseno;

	private Integer income;

	private Integer property_income;
}
