package com.example.cricscorer_api.controllers;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cricscorer_api.dto.AddPlayerDto;
import com.example.cricscorer_api.dto.LoginRequest;
import com.example.cricscorer_api.dto.SignupRequest;
import com.example.cricscorer_api.dto.ValidateTokenRequest;
import com.example.cricscorer_api.entity.Innings;
import com.example.cricscorer_api.entity.User;
import com.example.cricscorer_api.services.AuthService;
import com.example.cricscorer_api.services.Innings1Serivce;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

 @Autowired
 private AuthService authService;

 @PostMapping("/signup")
 public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
  return authService.registerUser(signupRequest);
 }

 @PostMapping("/login")
 public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
  return authService.authenticateUser(loginRequest);
 }

 @GetMapping("/users")
 public List<User> getMethodName() {
  return authService.getAllUsers();
 }

 @PostMapping("/validatetoken")
 public ResponseEntity<?> validateToken(@RequestBody ValidateTokenRequest validteTokenRequest) {
  return authService.validateToken(validteTokenRequest.getToken());
 }

 @PostMapping("/addPlayer")
 public ResponseEntity<?> addPlayer(@RequestBody AddPlayerDto addPlayerDto) {

  return authService.addPlayer(addPlayerDto);
 }

 @GetMapping("/getUser")
 public ResponseEntity<?> getUser(@RequestParam String email) {
  return authService.findByEmail(email);
 }

}