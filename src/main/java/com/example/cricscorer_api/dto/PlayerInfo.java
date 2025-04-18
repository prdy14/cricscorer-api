package com.example.cricscorer_api.dto;

import com.example.cricscorer_api.entity.Player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerInfo {
 private String id;
 private String name;

 public PlayerInfo(Player player) {
  this.id = player.getId();
  this.name = player.getName();
 }
}
