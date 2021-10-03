package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Boss;

@Repository
public interface BossRepository extends JpaRepository<Boss, Integer>{
	
}
