package com.example.cricscorer_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cricscorer_api.entity.Batter;
import com.example.cricscorer_api.entity.Inning;
import com.example.cricscorer_api.repository.BatterRepo;
import com.example.cricscorer_api.repository.InningsRepo;

@Service
public class InningService {

 @Autowired
 private InningsRepo inningsRepo;

 @Autowired
 private BatterRepo batterRepo;

 public ResponseEntity<?> addBatter(Long inningsId, Batter batter) {
  Inning inning = inningsRepo.findById(inningsId).orElse(null);
  batter.setStrikeRate(batter.getRuns() * 100 / batter.getBalls());
  batter.setInning(inning);

  batterRepo.save(batter);

  if (inning != null) {
   inning.getBatters().add(batter);
   inningsRepo.save(inning);
  }
  return ResponseEntity.ok(inning);
 }

}
