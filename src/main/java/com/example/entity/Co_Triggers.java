package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "CorrespondenceTrigger")
public class Co_Triggers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer trg_id;
	
	private Integer case_no;
	
	private String trg_status;
	
	private String emailid;
	
	@Lob
	private String notice;

}
