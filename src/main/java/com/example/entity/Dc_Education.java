package com.example.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Dc_Education {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eduid;
	
	private Integer caseno;
	
	private String highest_qualifcation;
	
	private Integer yearof_completion;

}
