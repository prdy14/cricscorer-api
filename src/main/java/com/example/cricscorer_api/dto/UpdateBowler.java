package com.example.cricscorer_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBowler {
 private String id;
 private int runs;
 private int wickets;
 private double overs;
 private int madiens;
}
