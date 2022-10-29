package com.example.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="ChildrenDetails")
public class Dc_Children {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer childid;
	
	private Integer caseno;
	
	private String child_name;
	
	private Integer age;
	
	private Integer ssn;
}
