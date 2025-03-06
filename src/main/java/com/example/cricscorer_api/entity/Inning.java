package com.example.cricscorer_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "innings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inning {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "match_id", referencedColumnName = "id")
 @JsonIgnore
 private Match match;

 private int totalRuns;
 private int totalWickets;
 private int wicketsGone;
 private double totalovers;
 private double completedOvers;
 private boolean completed;
 @OneToMany(mappedBy = "inning", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
 @JsonIgnore
 private List<Batter> batters;

 @OneToMany(mappedBy = "inning", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
 @JsonIgnore
 private List<Bowler> bowlers;

 // Getters & Setters
}
