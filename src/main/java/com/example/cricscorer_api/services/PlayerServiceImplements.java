package com.example.cricscorer_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cricscorer_api.dto.AddPlayerDto;
import com.example.cricscorer_api.dto.UserInfo;
import com.example.cricscorer_api.entity.Player;
import com.example.cricscorer_api.entity.User;
import com.example.cricscorer_api.repository.PlayerRepo;
import com.example.cricscorer_api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerServiceImplements implements PlayerServices {

 private final UserRepository userRepository;
 private final PlayerRepo playerRepo;

 public ResponseEntity<?> findByEmail(String eamil) {
  User user = userRepository.findByEmail(eamil).orElse(null);
  if (user != null) {
   return ResponseEntity.ok().body(new UserInfo(user));
  }
  return ResponseEntity.ok().body(null);
 }

 public ResponseEntity<?> addPlayer(AddPlayerDto addPlayerDto) {
  User user = new User(addPlayerDto.getUsername(), addPlayerDto.getEmail());
  userRepository.save(user);
  Player player = Player.builder().id(user.getUserId()).name(user.getName()).build();

  playerRepo.save(player);

  return ResponseEntity.ok().body(new UserInfo(user));

 }
}
