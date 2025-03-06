package com.example.cricscorer_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cricscorer_api.dto.TeamRequest;
import com.example.cricscorer_api.dto.UpdateTeamRequest;
import com.example.cricscorer_api.entity.Team;
import com.example.cricscorer_api.services.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

 @Autowired
 private TeamService teamService;

 @PostMapping("/createteam")
 public Team postMethodName(@RequestBody TeamRequest teamRequest) {
  System.out.println(teamRequest);
  return teamService.addTeam(teamRequest.getTeamName(), teamRequest.getPlayersId());
 }

 @DeleteMapping("/deleteTeam/{id}")
 public ResponseEntity<?> deleteTeam(@PathVariable("id") int id) {
  return teamService.deleteTeam(id);
 }

 @PutMapping("/updateTeam/{id}")
 public ResponseEntity<?> updateTeam(@PathVariable int id, @RequestBody UpdateTeamRequest updateTeamRequest) {

  return teamService.updateTeam(id, updateTeamRequest.getPlayersId());
 }

 @GetMapping("/teams")
 public ResponseEntity<?> getAllteams() {
  return teamService.getTeams();
 }

}
