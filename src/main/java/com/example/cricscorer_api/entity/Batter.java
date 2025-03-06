package com.example.cricscorer_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "batters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Batter {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "inning_id", referencedColumnName = "id")
 @JsonIgnore
 private Inning inning;

 private String name;
 private int runs;
 private int balls;
 private int fours;
 private int sixes;
 private double strikeRate;

 // Getters & Setters
}
