package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.ApplicantDetails;

public interface ApplicantDetailsRepo extends JpaRepository<ApplicantDetails,Integer>{

}
