package com.example.cricscorer_api.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.example.cricscorer_api.entity.Player;
import com.example.cricscorer_api.entity.Team;

import com.example.cricscorer_api.repository.PlayerRepo;
import com.example.cricscorer_api.repository.TeamRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TeamService {

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private PlayerRepo playerRepo;

  @Transactional
  public Team addTeam(String teamName, Set<Long> playersId) {
    // Fetch or create a team
    Team team = new Team();
    team.setTeamName(teamName);
    team.setPlayers(new HashSet<>());

    Set<Player> existingUsers = new HashSet<>();

    for (long id : playersId) {
      // Check if the user exists in the database
      playerRepo.findById(id).ifPresent(existingUsers::add);
    }

    // Associate only existing users with the team
    for (Player player : existingUsers) {
      team.addPlayer(player);
      ;
    }

    return teamRepository.save(team);
  }

  public ResponseEntity<?> deleteTeam(long id) {
    try {
      teamRepository.deleteById(id);
      return ResponseEntity.ok().body("team deleted sucessfully");
    } catch (Exception ex) {
      return ResponseEntity.badRequest().body("team not found");
    }

  }

  public ResponseEntity<?> updateTeam(int id, Set<Long> playerIds) {
    try {
      Team team = teamRepository.findByTeamId(id)
          .orElseThrow(() -> new RuntimeException("Team not found"));

      Set<Player> existingUsers = new HashSet<>();

      // Fetch only existing users from the database
      for (long playerId : playerIds) {
        playerRepo.findById(playerId).ifPresent(existingUsers::add);
      }

      team.setPlayers(existingUsers);

      teamRepository.save(team);
      // Save the updated team
      return ResponseEntity.ok().body(
          "created");
    } catch (Exception ex) {
      System.out.println(ex);
      return ResponseEntity.badRequest().body("team not found");
    }
  }

  public ResponseEntity<?> getTeams() {
    List<Team> teams = teamRepository.findAll();
    return ResponseEntity.ok().body(teams);
  }
}
