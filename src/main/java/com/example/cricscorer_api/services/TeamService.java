package com.example.cricscorer_api.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.example.cricscorer_api.dto.TeamResponse;
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
  public TeamResponse addTeam(String teamName, Set<String> playersId, String location) {
    // Fetch or create a team
    Team team = new Team();
    team.setTeamName(teamName);
    team.setPlayers(List.of());
    team.setLocation(location);

    Set<Player> existingUsers = new HashSet<>();

    for (String id : playersId) {
      // Check if the user exists in the database
      playerRepo.findById(id).ifPresent(existingUsers::add);
    }

    // Associate only existing users with the team
    for (Player player : existingUsers) {
      team.addPlayer(player);
      ;
    }

    teamRepository.save(team);
    return new TeamResponse(team);

  }

  public ResponseEntity<?> deleteTeam(String id) {
    try {
      teamRepository.deleteById(id);
      return ResponseEntity.ok().body("team deleted sucessfully");
    } catch (Exception ex) {
      return ResponseEntity.badRequest().body("team not found");
    }

  }

  public ResponseEntity<?> updateTeam(String id, Set<String> playerIds) {
    try {
      Team team = teamRepository.findByTeamId(id)
          .orElseThrow(() -> new RuntimeException("Team not found"));

      List<Player> existingUsers = List.of();

      // Fetch only existing users from the database
      for (String playerId : playerIds) {
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

  public ResponseEntity<?> addPlayerToTeam(String teamId, String playerId) {
    try {
      Team team = teamRepository.findByTeamId(teamId)
          .orElseThrow(() -> new RuntimeException("Team not found"));

      Player player = playerRepo.findById(playerId).orElse(null);

      if (player != null) {
        team.getPlayers().add(player);
      }

      teamRepository.save(team);
      return ResponseEntity.ok().body(
          "player added");
    } catch (Exception ex) {
      System.out.println(ex);
      return ResponseEntity.badRequest().body("error while adding player");
    }
  }

  public ResponseEntity<?> getTeams() {
    List<Team> teams = teamRepository.findAll();
    System.out.println(teams);
    return ResponseEntity.ok().body(teams);
  }

  public ResponseEntity<?> getTeam(String id) {
    Team team = teamRepository.findById(id).orElse(null);
    return ResponseEntity.ok().body(new TeamResponse(team));
  }
}
