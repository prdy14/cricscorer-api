package com.example.cricscorer_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cricscorer_api.dto.BowlerDTO;
import com.example.cricscorer_api.entity.Bowler;
import com.example.cricscorer_api.repository.BowlerRepo;

@Service
public class BowlerService {
 @Autowired
 private BowlerRepo bowlerRepo;

 public String createBowler(BowlerDTO bowlerDTO) {
  Bowler bowler = new Bowler(bowlerDTO);
  bowlerRepo.save(bowler);
  return bowler.getId();
 }

}
