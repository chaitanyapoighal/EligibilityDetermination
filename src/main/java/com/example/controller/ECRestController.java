package com.example.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.binding.ApplicantDisplay;
import com.example.service.EcServiceImpl;

@RestController
public class ECRestController {

	@Autowired
	private EcServiceImpl simpl;
	
	@GetMapping("/result/{cnum}")
	public ResponseEntity<ApplicantDisplay> getresult(@PathParam("cnum") Integer cnum){
		ApplicantDisplay appd=simpl.searchapplicant(cnum);
		return new ResponseEntity<>(appd, HttpStatus.OK);
		
	}
}
