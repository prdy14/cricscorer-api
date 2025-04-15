package com.example.cricscorer_api.controllers;

import com.example.cricscorer_api.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cricscorer_api.dto.LoginRequest;
import com.example.cricscorer_api.dto.SignupRequest;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

 private final UserService authService;

 @PostMapping("/login")
 public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session) {
  return authService.authenticateUser(request, session);
 }

 @PostMapping("/signup")
 public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
  return authService.registerUser(signupRequest);
 }

 @PostMapping("/logout")
 public ResponseEntity<?> logout(HttpSession session) {
  session.invalidate();
  return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
 }

}