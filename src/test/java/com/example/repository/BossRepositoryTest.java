package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.model.Boss;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class BossRepositoryTest {

	@Autowired
	private BossRepository bossRepository;
	
	@BeforeEach
	public void start() {
		Boss boss = new Boss();
		boss.setBossId(12345);
		boss.setBossName("Rahul");
		bossRepository.save(boss);
	}
	
//	@Test
//	public void getByNameSuccess() {
//		Boss boss = bossRepository.findByBossName("Rahul");
//		assertEquals(12345, boss.getBossId());
//	}
//	
//	@Test
//	public void getByNameFailure() {
//		Boss boss = bossRepository.findByBossName("Kishore");
//		assertNull(boss);
//	}
	
	@AfterEach
	public void end() {
		bossRepository.deleteAll();
	}
}
