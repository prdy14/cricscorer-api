package com.example.cricscorer_api.controllers;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cricscorer_api.dto.LoginRequest;
import com.example.cricscorer_api.dto.SignupRequest;

import com.example.cricscorer_api.entity.User;
import com.example.cricscorer_api.services.AuthService;
import org.springframework.web.bind.annotation.GetMapping;

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

}