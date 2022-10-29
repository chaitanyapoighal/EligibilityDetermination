package com.example.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer> {

	
	public Plan findByPlanname(String cname);
}
