package com.example.cricscorer_api.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMatchRequest {
 private List<TeamResponse> teams;
}
