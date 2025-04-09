package com.example.cricscorer_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cricscorer_api.dto.BatterDTO;
import com.example.cricscorer_api.entity.Batter;
import com.example.cricscorer_api.repository.BatterRepo;

@Service
public class BatterService {

 @Autowired
 private BatterRepo batterRepo;

 public String createBatter(BatterDTO batterDTO) {
  Batter batter = new Batter(batterDTO);
  batterRepo.save(batter);
  return batter.getId();
 }
}
