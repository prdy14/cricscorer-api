package com.example.cricscorer_api.services;

import java.util.Date;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cricscorer_api.dto.CreateMatchRequest;
import com.example.cricscorer_api.entity.Innings;
import com.example.cricscorer_api.entity.Match;
import com.example.cricscorer_api.entity.Status;
import com.example.cricscorer_api.entity.Team;
import com.example.cricscorer_api.entity.User;
import com.example.cricscorer_api.repository.Innings1Repo;

import com.example.cricscorer_api.repository.MatchRepo;
import com.example.cricscorer_api.repository.TeamRepository;
import com.example.cricscorer_api.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class MatchService {

  @Autowired
  private MatchRepo matchRepository;

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private Innings1Repo innings1Repo;

  @Transactional
  public ResponseEntity<?> createMatch(CreateMatchRequest matchRequest) {

    List<Team> teams = matchRequest.getTeams().stream()
        .map(team -> teamRepository.findById(team.getId()).orElseThrow(() -> new RuntimeException("team not found")))
        .collect(Collectors.toList());

    User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
        .orElse(null);

    Match match = Match.builder().date(new Date()).status(Status.Upcoming).teams(teams)
        .tossWon(matchRequest.getTossWon()).overs(matchRequest.getOvers()).venue(matchRequest.getVenue())
        .optTO(matchRequest.getOptTo()).createdBy(user)
        .build();
    matchRepository.save(match);

    Innings innings1 = Innings.builder().batters(List.of()).bowlers(List.of()).battingTeamId(teams.get(0).getTeamId())
        .bowlingTeamId(teams.get(1).getTeamId()).build();
    Innings innings2 = Innings.builder().batters(List.of()).bowlers(List.of()).battingTeamId(teams.get(1).getTeamId())
        .bowlingTeamId(teams.get(0).getTeamId()).build();

    if ((matchRequest.getTossWon() == teams.get(0).getTeamId() && matchRequest.getOptTo() == "Bat") || (matchRequest
        .getTossWon() == teams.get(1).getTeamId() && matchRequest.getOptTo() == "Ball")) {
      innings1Repo.save(innings1);
    } else {
      innings1Repo.save(innings2);
    }

    return ResponseEntity.ok(match);

  }

  public ResponseEntity<?> deleteMatch(Long id) {
    try {
      matchRepository.deleteById(id);
      return ResponseEntity.ok("Match deleted");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Match not found");
    }

  }

  public String updateStatus(Status status, Long id) {
    Match match = matchRepository.findById(id).orElse(null);
    if (match != null) {
      match.setStatus(status);
      return "Status updated";
    }
    return "Status updatation failed";
  }

  public ResponseEntity<?> getAllMatches() {
    return ResponseEntity.ok(matchRepository.findAll());
  }

}
