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

@Entity
@Table(name = "matches")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Match {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

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
  private String tossWon;
  private int target;
  private boolean isinnings2;

  private String optTO;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private User createdBy;

}
