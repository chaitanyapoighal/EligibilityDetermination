package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Dc_Education;

public interface Dc_EducationRepo extends JpaRepository<Dc_Education, Integer>{
          
	
	public Dc_Education findByCaseno(Integer caseno);
}
