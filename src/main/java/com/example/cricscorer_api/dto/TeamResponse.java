package com.example.cricscorer_api.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.example.cricscorer_api.entity.Player;
import com.example.cricscorer_api.entity.Team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class TeamResponse {
 private long id;
 private String name;
 private String location;

 public TeamResponse(Team team) {
  this.id = team.getTeamId();
  this.name = team.getTeamName();

  this.location = team.getLocation();
 }

 public TeamResponse(int id) {
  this.id = id;
 }
}
