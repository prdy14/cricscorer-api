package com.example.cricscorer_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Innings {

 @Id
 private String id;

 private long matchId;
 private long battingTeamId;
 private long bowlingTeamId;
 private int runs;
 private int wickets;
 private double currentOver;
 private boolean completed;
 private List<Batter> batters;
 private List<Bowler> bowlers;

 @DBRef
 private List<Over> overs;
}
