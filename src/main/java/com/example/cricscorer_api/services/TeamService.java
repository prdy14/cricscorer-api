package com.example.cricscorer_api.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cricscorer_api.dto.TeamRequest;
import com.example.cricscorer_api.entity.Team;
import com.example.cricscorer_api.entity.User;
import com.example.cricscorer_api.repository.TeamRepository;
import com.example.cricscorer_api.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TeamService {

 @Autowired
 private TeamRepository teamRepository;

 @Autowired
 private UserRepository userRepository;

 @Transactional
 public Team addTeam(String teamName, Set<String> emails) {
  // Fetch or create a team
  Team team = new Team();
  team.setTeamName(teamName);
  team.setPlayers(new HashSet<>());

  Set<User> existingUsers = new HashSet<>();

  for (String userName : emails) {
   // Check if the user exists in the database
   userRepository.findByEmail(userName).ifPresent(existingUsers::add);
  }

  // Associate only existing users with the team
  for (User user : existingUsers) {
   team.addUser(user);
   ;
  }

  return teamRepository.save(team);
 }
}
