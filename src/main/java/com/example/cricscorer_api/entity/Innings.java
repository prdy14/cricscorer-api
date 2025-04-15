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
 private String matchId;
 private boolean isInnings1;
 private String battingTeamId;
 private String bowlingTeamId;
 private double over;
 private int runs;
 private int wickets;
 private boolean started;
 private boolean completed;

 @DBRef
 private List<Batter> batters;

 @DBRef
 private List<Bowler> bowlers;

 @DBRef
 private List<Over> overs;
}
