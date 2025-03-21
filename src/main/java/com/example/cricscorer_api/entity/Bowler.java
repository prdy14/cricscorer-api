package com.example.cricscorer_api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
class Bowler {
 private long playerId;
 private String name;
 private int overs;
 private int maidens;
 private int runsConceded;
 private int wickets;
 private double economy;
}
