package com.example.cricscorer_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bowlers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bowler {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "inning_id", referencedColumnName = "id")
 @JsonIgnore
 private Inning inning;

 private String name;
 private int overs;
 private int maidens;
 private int runsConceded;
 private int wickets;
 private double economy;

}
