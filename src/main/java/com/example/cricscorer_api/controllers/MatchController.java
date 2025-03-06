package com.example.cricscorer_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.cricscorer_api.dto.CreateMatchRequest;
import com.example.cricscorer_api.services.MatchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

 @Autowired
 private MatchService matchService;

 @PostMapping("/creatematch")
 public ResponseEntity<?> postMethodName(@RequestBody CreateMatchRequest entity) {
  return matchService.createMatch(entity);
 }

 @GetMapping("/allmatches")
 public ResponseEntity<?> getAllMatches() {
  return matchService.getAllMatches();
 }

 @DeleteMapping("/deletematch/{id}")
 public ResponseEntity<?> deleteMatch(@PathVariable Long id) {
  return matchService.deleteMatch(id);
 }

}
