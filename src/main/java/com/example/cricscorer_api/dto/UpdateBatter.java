package com.example.cricscorer_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBatter {
 private String id;
 private int runs;
 private int balls;
 private int fours;
 private int sixes;
 private boolean striker;
 private boolean out;
 private String bowledBy;

}
