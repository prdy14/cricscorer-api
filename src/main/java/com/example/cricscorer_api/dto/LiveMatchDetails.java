package com.example.cricscorer_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LiveMatchDetails {
 private String id;
 private String venue;
 private boolean secondInnings;
 private int target;
 private String score1;
 private String score2;
 private String teamA;
 private String teamB;
 private String tossWon;
 private String optTo;
}
