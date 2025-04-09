package com.example.cricscorer_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.cricscorer_api.dto.BatterDTO;
import com.example.cricscorer_api.dto.BowlerDTO;
import com.example.cricscorer_api.dto.CreateMatchRequest;
import com.example.cricscorer_api.dto.MatchDetails;
import com.example.cricscorer_api.dto.OutDto;
import com.example.cricscorer_api.dto.UpdateBalls;
import com.example.cricscorer_api.dto.UpdateBatter;
import com.example.cricscorer_api.dto.UpdateBowler;
import com.example.cricscorer_api.dto.UpdateMatchInfo;
import com.example.cricscorer_api.dto.UpdateScore;

import com.example.cricscorer_api.repository.MatchRepo;
import com.example.cricscorer_api.services.MatchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

  private final MatchRepo matchRepo;

  @Autowired
  private MatchService matchService;

  MatchController(MatchRepo matchRepo) {
    this.matchRepo = matchRepo;
  }

  @PostMapping("/creatematch")
  public ResponseEntity<?> postMethodName(@RequestBody CreateMatchRequest entity) {
    return matchService.createMatch(entity);
  }

  @GetMapping("/allmatches/{offset}")
  public ResponseEntity<?> getAllMatches(@PathVariable("offset") int id) {
    return matchService.getAllMatches(id);
  }

  @DeleteMapping("/deletematch/{id}")
  public ResponseEntity<?> deleteMatch(@PathVariable String id) {
    return matchService.deleteMatch(id);
  }

  @GetMapping("/getmatch/{id}")
  public UpdateMatchInfo getMethodNam(@PathVariable String id) {
    return matchService.getMatch(id);
  }

  @GetMapping("/{id}/innings1")
  public ResponseEntity<?> getInnings1(@PathVariable String id) {
    return matchService.getInnings(id);
  }

  @GetMapping("/{id}/innings2")
  public ResponseEntity<?> getInnings2(@PathVariable String id) {
    return matchService.getInnings2(id);
  }

  @PostMapping("/{id}/setStriker")
  public ResponseEntity<?> postMethodName(@PathVariable String id, @RequestBody BatterDTO batterDTO) {

    return matchService.addStriker(id, batterDTO);
  }

  @PostMapping("/{id}/setbowler")
  public ResponseEntity<?> setBowler(@PathVariable String id, @RequestBody BowlerDTO bowlerDTO) {
    return matchService.addBowler(id, bowlerDTO);
  }

  @GetMapping("/{id}/getstriker")
  public ResponseEntity<?> getMethodName(@PathVariable String id) {
    return matchService.getStrinker(id);
  }

  @GetMapping("/{id}/getnonstriker")
  public ResponseEntity<?> getnonstriker(@PathVariable String id) {
    return matchService.getNonStrinker(id);
  }

  @GetMapping("/{id}/getbowler")
  public ResponseEntity<?> getBowlerName(@PathVariable String id) {
    return matchService.getBowler(id);
  }

  @PostMapping("/{id}/setnonStriker")
  public ResponseEntity<?> setnonStriker(@PathVariable String id, @RequestBody BatterDTO batterDTO) {

    return matchService.addNonStriker(id, batterDTO);
  }

  @PostMapping("/{inningsId}/startOver/{bowlerId}")
  public ResponseEntity<?> postMethodName(@PathVariable("inningsId") String inningsId,
      @PathVariable("bowlerId") String bowlerId) {
    return matchService.startOver(inningsId, bowlerId);
  }

  @PostMapping("/updateScore")
  public ResponseEntity<?> updateScore(@RequestBody UpdateScore updateScore) {
    return matchService.updateScore(updateScore);
  }

  @PostMapping("/updateBatter")
  public ResponseEntity<?> updateBatter(@RequestBody UpdateBatter batter) {
    return matchService.updatebatter(batter);
  }

  @PostMapping("/startsecondInnings")
  public ResponseEntity<?> postMethodName(@RequestBody MatchDetails matchDetails) {
    return matchService.startSecondInnings(matchDetails);
  }

  @PutMapping("/overcomplete/{id}")
  public ResponseEntity<?> overComplete(@PathVariable String id) {
    return matchService.overComplete(id);
  }

  @PostMapping("/updateBowler")
  public ResponseEntity<?> updateBowler(@RequestBody UpdateBowler bowler) {
    return matchService.updateBowler(bowler);
  }

  @PostMapping("/updatescore/addball")
  public ResponseEntity<?> updateBalls(@RequestBody UpdateBalls updateBalls) {
    return matchService.updateOver(updateBalls);
  }

  @PatchMapping("/out")
  public ResponseEntity<?> batterOut(@RequestBody OutDto outDto) {
    return matchService.batterout(outDto.getInningsId(), outDto.getBatterId(), outDto.getBowlerId());
  }

}
