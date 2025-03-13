package com.example.cricscorer_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cricscorer_api.entity.Player;
import com.example.cricscorer_api.repository.PlayerRepo;

@Service
public class PlayerService {

 @Autowired
 private PlayerRepo playerRepo;

 public Player findPlayer(long id) {
  return playerRepo.findById(id).orElse(null);
 }
}
