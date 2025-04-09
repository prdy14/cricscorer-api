package com.example.cricscorer_api.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamRequest {
 private String teamName;
 private Set<String> playersId;
 private String location;
}
