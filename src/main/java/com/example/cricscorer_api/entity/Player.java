package com.example.cricscorer_api.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {

 @Id
 @Column(name = "player_id")
 private long id;

 private String name;

 private int matchesPlayed;

 private int inningsPlayed;

 private int runsScored;

 private int runsConceded;

 private float overs;

 private int wickets;

 private double economy;

 @ManyToMany(mappedBy = "players", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
 @JsonIgnore
 private Set<Team> teams;

}
