package com.example.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Dc_Children;

public interface Dc_childrenRepo extends JpaRepository<Dc_Children,Integer>{
 public Dc_Children findByCaseno(Integer caseno);
}
