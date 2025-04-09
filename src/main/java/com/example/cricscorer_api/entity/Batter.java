package com.example.cricscorer_api.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.cricscorer_api.dto.BatterDTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Batter {
 @Id
 private String id;
 private String playerId;

 private int runs;
 private int balls;
 private int fours;
 private int sixes;
 private boolean isOut;
 private String bowledBy;
 private String catchBy;
 private String runOutBy;
 private double strikeRate;
 private boolean isStriker;
 private String name;

 public Batter(BatterDTO batterDTO) {
  this.playerId = batterDTO.getPlayerId();
  this.name = batterDTO.getName();
  this.isStriker = batterDTO.isStriker();
 }
}