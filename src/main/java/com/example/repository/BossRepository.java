package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Boss;

@Repository
public interface BossRepository extends JpaRepository<Boss, Integer>{

	@Query(value= "select bossName from boss where boss.bossId = ?1", nativeQuery=true)
	String getBossName(int bossId);
	
}
