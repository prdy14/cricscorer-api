package com.example.cricscorer_api.services;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cricscorer_api.dto.CreateMatchRequest;
import com.example.cricscorer_api.entity.Batter;
import com.example.cricscorer_api.entity.Bowler;
import com.example.cricscorer_api.entity.Inning;
import com.example.cricscorer_api.entity.Match;
import com.example.cricscorer_api.entity.Status;
import com.example.cricscorer_api.entity.Team;
import com.example.cricscorer_api.repository.InningsRepo;
import com.example.cricscorer_api.repository.MatchRepo;
import com.example.cricscorer_api.repository.TeamRepository;

import jakarta.transaction.Transactional;

@Service
public class MatchService {

  @Autowired
  private MatchRepo matchRepository;

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private InningsRepo inningsRepo;

  @Transactional
  public ResponseEntity<?> createMatch(CreateMatchRequest matchRequest) {

    List<Team> teams = matchRequest.getTeams().stream()
        .map(team -> teamRepository.findById(team.getId()).orElseThrow(() -> new RuntimeException("team not found")))
        .collect(Collectors.toList());

    Match match = Match.builder().date(new Date()).status(Status.Upcoming).teams(teams).innings(new ArrayList<Inning>())
        .tossWon(matchRequest.getTossWon()).overs(matchRequest.getOvers()).venue(matchRequest.getVenue())
        .optTO(matchRequest.getOptTo())
        .build();
    matchRepository.save(match);

    Inning inning1 = Inning.builder().batters(new ArrayList<Batter>()).bowlers(new ArrayList<Bowler>()).match(match)
        .build();
    Inning inning2 = Inning.builder().batters(new ArrayList<Batter>()).bowlers(new ArrayList<Bowler>()).match(match)
        .build();
    inningsRepo.save(inning1);
    inningsRepo.save(inning2);

    match.getInnings().add(inning2);
    match.getInnings().add(inning1);
    matchRepository.save(match);
    return ResponseEntity.ok(match.getTeams().stream().map(Team::getPlayers).collect(Collectors.toList()));

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
