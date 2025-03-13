package com.example.cricscorer_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cricscorer_api.entity.Player;
import com.example.cricscorer_api.services.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

 @Autowired
 private PlayerService playerService;

 @GetMapping("/{id}")
 public Player getMethodName(@PathVariable("id") long id) {
  return playerService.findPlayer(id);
 }

}
