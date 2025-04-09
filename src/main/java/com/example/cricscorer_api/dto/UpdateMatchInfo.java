package com.example.cricscorer_api.dto;

import com.example.cricscorer_api.entity.Match;
import com.example.cricscorer_api.entity.Status;

import lombok.Data;

@Data
public class UpdateMatchInfo {
 private String id;
 private int overs;
 private boolean innings2;
 private Status status;
 private int target;

 public UpdateMatchInfo(Match match) {
  this.id = match.getId();
  this.overs = match.getOvers();
  this.innings2 = match.isIsinnings2();
  this.status = match.getStatus();
  this.target = match.getTarget();

 }

}
