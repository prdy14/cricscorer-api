package com.example.cricscorer_api.services;

import java.util.Date;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cricscorer_api.dto.BatterDTO;
import com.example.cricscorer_api.dto.BowlerDTO;
import com.example.cricscorer_api.dto.CreateMatchRequest;
import com.example.cricscorer_api.dto.LiveMatchDetails;
import com.example.cricscorer_api.dto.MatchDetails;
import com.example.cricscorer_api.dto.UpdateBalls;
import com.example.cricscorer_api.dto.UpdateBatter;
import com.example.cricscorer_api.dto.UpdateBowler;
import com.example.cricscorer_api.dto.UpdateMatchInfo;
import com.example.cricscorer_api.dto.UpdateScore;
import com.example.cricscorer_api.entity.Batter;
import com.example.cricscorer_api.entity.Bowler;
import com.example.cricscorer_api.entity.Innings;
import com.example.cricscorer_api.entity.Match;
import com.example.cricscorer_api.entity.Over;
import com.example.cricscorer_api.entity.Status;
import com.example.cricscorer_api.entity.Team;
import com.example.cricscorer_api.entity.User;
import com.example.cricscorer_api.repository.BatterRepo;
import com.example.cricscorer_api.repository.BowlerRepo;
import com.example.cricscorer_api.repository.InningsRepo;

import com.example.cricscorer_api.repository.MatchRepo;
import com.example.cricscorer_api.repository.OverRepo;
import com.example.cricscorer_api.repository.TeamRepository;
import com.example.cricscorer_api.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class MatchService {

  private final BatterRepo batterRepo;

  @Autowired
  private MatchRepo matchRepository;

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private InningsRepo inningsRepo;

  @Autowired
  private BowlerRepo bowlerRepo;

  @Autowired
  private OverRepo overRepo;

  MatchService(BatterRepo batterRepo) {
    this.batterRepo = batterRepo;
  }

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

    if ((matchRequest.getTossWon().equals(teams.get(0).getTeamId()) && matchRequest.getOptTo().equals("Bat"))
        || (matchRequest
            .getTossWon().equals(teams.get(1).getTeamId()) && matchRequest.getOptTo().equals("Ball"))) {
      System.out.println("toss won by " + matchRequest.getOptTo());
      Innings innings1 = Innings.builder().batters(List.of()).bowlers(List.of())
          .battingTeamId(teams.get(0).getTeamId())
          .matchId(match.getId()).bowlingTeamId(teams.get(1).getTeamId()).isInnings1(true).overs(List.of()).build();
      Innings innings2 = Innings.builder().batters(List.of()).bowlers(List.of())
          .battingTeamId(teams.get(1).getTeamId())
          .bowlingTeamId(teams.get(0).getTeamId()).overs(List.of()).isInnings1(false).matchId(match.getId()).build();
      inningsRepo.save(innings1);
      inningsRepo.save(innings2);

    } else {
      System.out.println("toss loss by" + matchRequest.getTossWon());

      Innings innings1 = Innings.builder().batters(List.of()).bowlers(List.of())
          .battingTeamId(teams.get(1).getTeamId()).overs(List.of())
          .matchId(match.getId()).bowlingTeamId(teams.get(0).getTeamId()).isInnings1(true).overs(List.of()).build();
      Innings innings2 = Innings.builder().batters(List.of()).bowlers(List.of())
          .battingTeamId(teams.get(0).getTeamId()).overs(List.of())
          .bowlingTeamId(teams.get(1).getTeamId()).matchId(match.getId()).overs(List.of()).build();
      inningsRepo.save(innings1);
      inningsRepo.save(innings2);
    }

    return ResponseEntity.ok(match);
  }

  public ResponseEntity<?> startSecondInnings(MatchDetails matchdetails) {
    Match match = matchRepository.findById(matchdetails.getId()).orElse(
        null);
    if (match != null) {

      match.setIsinnings2(true);
      match.setTarget(matchdetails.getTarget());
      matchRepository.save(match);
    }
    ;
    return ResponseEntity.ok(match);
  }

  public ResponseEntity<?> deleteMatch(String id) {
    try {
      matchRepository.deleteById(id);
      return ResponseEntity.ok("Match deleted");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Match not found");
    }

  }

  public UpdateMatchInfo getMatch(String id) {
    Match match = matchRepository.findById(id).orElse(null);
    return new UpdateMatchInfo(match);
  }

  public String updateStatus(Status status, String id) {
    Match match = matchRepository.findById(id).orElse(null);
    if (match != null) {
      match.setStatus(status);
      return "Status updated";
    }
    return "Status updatation failed";
  }

  public ResponseEntity<?> getAllMatches(int i) {
    List<LiveMatchDetails> matches = matchRepository.ge10Matches(i * 10).stream().map(match -> getMatchDetails(match))
        .toList();
    return ResponseEntity.ok(matches);
  }

  public LiveMatchDetails getMatchDetails(Match match) {
    Innings innings1 = inningsRepo.findAllByMatchId(match.getId()).stream().filter((inning) -> inning.isInnings1())
        .collect(Collectors.toList()).get(0);
    Innings innings2 = inningsRepo.findAllByMatchId(match.getId()).stream().filter((inning) -> !inning.isInnings1())
        .collect(Collectors.toList()).get(0);
    String teamA = teamRepository.findByTeamId(innings1.getBattingTeamId()).get().getTeamName();
    String teamB = teamRepository.findByTeamId(innings1.getBowlingTeamId()).get().getTeamName();
    return LiveMatchDetails.builder().id(match.getId())
        .optTo(match.getOptTO()).secondInnings(match.isIsinnings2())
        .target(match.getTarget()).tossWon(match.getTossWon().equals(innings1.getBattingTeamId()) ? teamA : teamB)
        .teamB(teamB).teamA(teamA).score1(innings1.getRuns() + "-" + innings1.getWickets())
        .score2(innings2.getRuns() + "-" + innings2.getWickets()).venue(match.getVenue()).build();
  }

  public ResponseEntity<?> getInnings(String id) {
    Innings innings = inningsRepo.findAllByMatchId(id).stream().filter((inning) -> inning.isInnings1())
        .collect(Collectors.toList()).get(0);
    System.out.println(innings);
    return ResponseEntity.ok(innings);
  }

  public ResponseEntity<?> getInnings2(String id) {
    Innings innings = inningsRepo.findAllByMatchId(id).stream().filter((inning) -> !inning.isInnings1())
        .collect(Collectors.toList()).get(0);
    System.out.println(innings);
    return ResponseEntity.ok(innings);
  }

  public ResponseEntity<?> addStriker(String id, BatterDTO batterDTO) {

    batterDTO.setStriker(true);
    Innings innings = inningsRepo.findById(id).orElse(null);
    boolean flag = innings.getBatters().stream().anyMatch(bat -> bat.getPlayerId() == batterDTO.getPlayerId());
    if (flag) {
      return ResponseEntity.ok(
          innings.getBatters().stream().filter(bat -> bat.getPlayerId() == batterDTO.getPlayerId()).toList().get(0));
    }
    Batter batter = new Batter(batterDTO);
    System.out.println(batterDTO);
    batterRepo.save(batter);
    innings.getBatters().add(batter);
    inningsRepo.save(innings);
    return ResponseEntity.ok(batter);
  }

  public ResponseEntity<?> getStrinker(String id) {
    Batter batter = inningsRepo.findById(id).get().getBatters().stream().filter(str -> str.isStriker()).toList().get(0);
    return ResponseEntity.ok(batter);
  }

  public ResponseEntity<?> getNonStrinker(String id) {
    Batter batter = inningsRepo.findById(id).get().getBatters().stream().filter(str -> !str.isOut() && !str.isStriker())
        .toList().get(0);
    return ResponseEntity.ok(batter);
  }

  public ResponseEntity<?> startOver(String inningsId, String bowlerId) {
    Innings innings = inningsRepo.findById(inningsId).orElse(null);
    if (innings == null) {
      return ResponseEntity.ok().body("innings not fount");
    }
    Over over = Over.builder().bowlerId(bowlerId).balls(List.of()).build();
    overRepo.save(over);
    innings.getOvers().add(over);
    inningsRepo.save(innings);
    return ResponseEntity.ok(over);

  }

  public ResponseEntity<?> updatebatter(UpdateBatter updateBatter) {
    System.out.println(updateBatter);
    Batter batter = batterRepo.findById(updateBatter.getId()).orElse(null);
    batter.setBalls(updateBatter.getBalls());
    batter.setFours(updateBatter.getFours());
    batter.setSixes(updateBatter.getSixes());
    batter.setRuns(updateBatter.getRuns());
    batter.setStriker(updateBatter.isStriker());
    batter.setOut(updateBatter.isOut());
    batter.setBowledBy(updateBatter.getBowledBy());
    batterRepo.save(batter);
    return ResponseEntity.ok("success");
  }

  public ResponseEntity<?> updateScore(UpdateScore updateScore) {
    Innings innings = inningsRepo.findById(updateScore.getId()).orElse(null);
    if (innings != null) {

      innings.setRuns(updateScore.getScore());
      innings.setWickets(updateScore.getWickets());
      inningsRepo.save(innings);
    }
    return ResponseEntity.ok("sucess");
  }

  public ResponseEntity<?> updateBowler(UpdateBowler updateBowler) {
    Bowler bowler2 = bowlerRepo.findById(updateBowler.getId()).orElse(null);
    bowler2.setMadiens(updateBowler.getMadiens());
    bowler2.setOvers(updateBowler.getOvers());
    bowler2.setRuns(updateBowler.getRuns());
    bowler2.setWickets(updateBowler.getWickets());
    bowlerRepo.save(bowler2);
    return ResponseEntity.ok("success");
  }

  public ResponseEntity<?> updateOver(UpdateBalls updateBalls) {
    Over over = overRepo.findById(updateBalls.getId()).orElse(null);
    if (over != null) {
      over.setBallCount(updateBalls.getBallNo());
      over.setBalls(updateBalls.getBalls());
      overRepo.save(over);
      return ResponseEntity.ok(over.getId());
    }
    return ResponseEntity.badRequest().body("error");
  }

  public ResponseEntity<?> getBowler(String id) {
    Bowler bowler = inningsRepo.findById(id).get().getBowlers().get(0);
    return ResponseEntity.ok(bowler);
  }

  public ResponseEntity<?> addNonStriker(String id, BatterDTO batterDTO) {

    batterDTO.setStriker(false);
    Innings innings = inningsRepo.findById(id).orElse(null);
    boolean flag = innings.getBatters().stream().anyMatch(bat -> bat.getPlayerId().equals(batterDTO.getPlayerId()));
    if (flag) {
      return ResponseEntity.ok(
          innings.getBatters().stream().filter(bat -> bat.getPlayerId().equals(batterDTO.getPlayerId())).toList()
              .get(0));
    }
    Batter batter = new Batter(batterDTO);
    batterRepo.save(batter);
    innings.getBatters().add(batter);
    inningsRepo.save(innings);
    return ResponseEntity.ok(batter);
  }

  public ResponseEntity<?> overComplete(String id) {
    Over over = overRepo.findById(id).orElse(null);
    if (over != null) {
      over.setCompleted(true);
      Bowler bowler = bowlerRepo.findById(over.getBowlerId()).orElse(null);
      bowler.setBowling(false);
      bowlerRepo.save(bowler);
      overRepo.save(over);
    }
    return ResponseEntity.ok("sucess");
  }

  public ResponseEntity<?> addBowler(String id, BowlerDTO bowlerDTO) {
    Innings innings = inningsRepo.findById(id).orElse(null);
    boolean flag = innings.getBowlers().stream().anyMatch(bow -> bow.getPlayerId().equals(bowlerDTO.getPlayerId()));
    if (flag) {
      Bowler bowl = innings.getBowlers().stream().filter(bow -> bow.getPlayerId().equals(bowlerDTO
          .getPlayerId())).toList()
          .get(0);
      bowl = bowlerRepo.findById(bowl.getId()).orElse(null);
      bowl.setBowling(true);
      bowlerRepo.save(bowl);
      return ResponseEntity.ok(
          bowl);
    }
    Bowler bowler = new Bowler(bowlerDTO);
    bowler.setBowling(true);
    bowlerRepo.save(bowler);
    innings.getBowlers().add(bowler);
    inningsRepo.save(innings);
    return ResponseEntity.ok(bowler);
  }

  public ResponseEntity<?> batterout(String inningsId, String batterId, String bowlerId) {
    Batter batter = batterRepo.findById(batterId).orElse(null);
    Bowler bowler = bowlerRepo.findById(bowlerId).orElse(null);
    Innings innings = inningsRepo.findById(inningsId).orElse(null);
    if (batter != null && bowler != null) {
      innings.setWickets(innings.getWickets() + 1);
      batter.setOut(true);
      batter.setBowledBy(bowler.getName());
      bowler.setWickets(bowler.getWickets() + 1);
      batter.setBalls(batter.getBalls() + 1);
      bowler.setOvers(bowler.getOvers() + ((int) (bowler.getOvers() * 10) % 10 == 5 ? 0.5 : 0.1));
      batterRepo.save(batter);
      inningsRepo.save(innings);
      bowlerRepo.save(bowler);
      return ResponseEntity.ok("sucess");
    }
    return ResponseEntity.badRequest().body("bad request");
  }

}
