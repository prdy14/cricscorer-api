package com.example.cricscorer_api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InningsInfo {
 private String id;
 private Long matchId;
 private int wickets;
 private int runs;
 private List<BatterDTO> battingTeam;
 private List<BowlerDTO> bowlingTeam;
}
