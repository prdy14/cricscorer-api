package com.example.cricscorer_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "matches")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Match {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "team_matches", joinColumns = {
      @JoinColumn(name = "team1_id")
  }, inverseJoinColumns = {
      @JoinColumn(name = "team2_id")
  })
  @JsonIgnore
  private List<Team> teams;

  private String venue;
  private Date date;

  @Enumerated(EnumType.STRING)
  private Status status;
  private String result;
  private int overs;
  private long tossWon;

  private String optTO;

  @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  @JsonIgnore
  private List<Inning> innings;

}
