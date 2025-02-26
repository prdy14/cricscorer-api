package com.example.cricscorer_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cricscorer_api.dto.TeamRequest;
import com.example.cricscorer_api.entity.Team;
import com.example.cricscorer_api.services.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

 @Autowired
 private TeamService teamService;

 @PostMapping("/createteam")
 public Team postMethodName(@RequestBody TeamRequest teamRequest) {
  System.out.println(teamRequest);
  return teamService.addTeam(teamRequest.getTeamName(), teamRequest.getPlayers());
 }

}
