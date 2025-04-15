package com.example.cricscorer_api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cricscorer_api.dto.AddPlayerDto;
import com.example.cricscorer_api.services.PlayerServices;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/player")
@RequiredArgsConstructor
public class PlayerController {

 private final PlayerServices playerServices;

 @GetMapping("/getplayer")
 public ResponseEntity<?> getPlayer(@RequestParam("email") String email) {
  return playerServices.findByEmail(email);
 }

 @PostMapping("/addPlayer")
 public ResponseEntity<?> postMethodName(@RequestBody AddPlayerDto addPlayerDto) {
  return playerServices.addPlayer(addPlayerDto);

 }

}
