package com.example.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Dc_Income;

public interface Dc_incomeRepo extends JpaRepository<Dc_Income, Integer> {

	public Dc_Income findByCaseno(Integer cnum);

}
