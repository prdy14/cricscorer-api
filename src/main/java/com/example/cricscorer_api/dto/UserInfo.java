package com.example.cricscorer_api.dto;

import com.example.cricscorer_api.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
 private String id;
 private String name;

 public UserInfo(User user) {
  this.id = user.getUserId();
  this.name = user.getUsername();
 }
}
