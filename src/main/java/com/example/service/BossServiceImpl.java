package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.NotABossException;
import com.example.model.Boss;
import com.example.repository.BossRepository;

@Service
public class BossServiceImpl implements BossService{

	@Autowired
	private BossRepository bossRepository;
	
	@Override
	public Boss getBoss(int id) {
		Optional<Boss> optionalBoss = bossRepository.findById(id);
		Boss boss = optionalBoss.orElseThrow(()-> new NotABossException("You are not a boss"));
		return boss;
	}
	
}
