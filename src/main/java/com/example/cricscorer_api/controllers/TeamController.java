package com.example.cricscorer_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cricscorer_api.dto.TeamRequest;
import com.example.cricscorer_api.dto.TeamResponse;
import com.example.cricscorer_api.dto.UpdateTeamRequest;

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
 public TeamResponse createTeam(@RequestBody TeamRequest teamRequest) {
  System.out.println(teamRequest);
  return teamService.addTeam(teamRequest.getTeamName(), teamRequest.getPlayersId(), teamRequest.getLocation());
 }

 @DeleteMapping("/deleteTeam/{id}")
 public ResponseEntity<?> deleteTeam(@PathVariable("id") String id) {
  return teamService.deleteTeam(id);
 }

 @PutMapping("/updateTeam/{id}")
 public ResponseEntity<?> updateTeam(@PathVariable String id, @RequestBody UpdateTeamRequest updateTeamRequest) {

  return teamService.updateTeam(id, updateTeamRequest.getPlayersId());
 }

 @PutMapping("/addPlayer/{id}/{pId}")
 public ResponseEntity<?> putMethodName(@PathVariable("id") String id, @PathVariable("pId") String pId) {
  return teamService.addPlayerToTeam(id, pId);

 }

 @GetMapping("/teams")
 public ResponseEntity<?> getAllteams() {
  return teamService.getTeams();
 }

 @GetMapping("/team/{id}")
 public ResponseEntity<?> getTeam(@PathVariable("id") String id) {
  return teamService.getTeam(id);
 }

}
