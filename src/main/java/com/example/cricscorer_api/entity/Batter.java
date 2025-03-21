package com.example.cricscorer_api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
class Batter {
 private int spot;
 private long playerId;
 private String name;
 private int runs;
 private int balls;
 private int fours;
 private int sixes;
 private boolean out;
 private long bowledBy;
 private long catchBy;
 private long runOutBy;
 private double strikeRate;
}