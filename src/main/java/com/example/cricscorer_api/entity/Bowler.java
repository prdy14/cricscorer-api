package com.example.cricscorer_api.entity;

import com.example.cricscorer_api.dto.BowlerDTO;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bowler {

 @Id
 private String id;
 private String playerId;
 private String name;
 private double overs;
 private int madiens;
 private int runs;
 private int wickets;
 private double economy;
 private boolean isBowling;

 public Bowler(BowlerDTO bowlerDTO) {
  this.playerId = bowlerDTO.getPlayerId();
  this.name = bowlerDTO.getName();
 }
}
