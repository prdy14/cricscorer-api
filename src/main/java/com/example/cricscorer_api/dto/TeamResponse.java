package com.example.cricscorer_api.dto;

import java.util.List;
import java.util.stream.Collectors;

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
 private String id;
 private String name;
 private String location;
 private List<PlayerInfo> players;

 public TeamResponse(Team team) {
  this.id = team.getTeamId();
  this.name = team.getTeamName();
  this.players = team.getPlayers().stream().map(player -> new PlayerInfo(player)).collect(Collectors.toList());
  this.location = team.getLocation();
 }

 public TeamResponse(String id) {
  this.id = id;
 }
}
