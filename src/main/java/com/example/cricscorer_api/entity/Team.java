package com.example.cricscorer_api.entity;

import java.util.Set;

import org.springframework.data.mongodb.repository.CountQuery;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@Table(name = "team")
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "team_id")
  private int teamId;

  @NotNull
  private String teamName;

  @ManyToMany
  @JoinTable(name = "team_players", joinColumns = {
      @JoinColumn(name = "team_id")
  }, inverseJoinColumns = {
      @JoinColumn(name = "user_id")
  })
  private Set<User> players;

  public void addUser(User user) {
    this.players.add(user);
  }

  private int matchesPlayes;

  private int won;

  private int lost;

  private int draw;

}
