package com.example.cricscorer_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatterDTO {
 private String playerId;
 private String name;
 private boolean isStriker;
}