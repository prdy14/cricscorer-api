package com.example.cricscorer_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cricscorer_api.dto.LoginRequest;
import com.example.cricscorer_api.dto.SignupRequest;
import com.example.cricscorer_api.entity.User;
import com.example.cricscorer_api.repository.UserRepository;
import com.example.cricscorer_api.security.JwtUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtils jwtUtils;

  public ResponseEntity<?> registerUser(SignupRequest signupRequest) {
    // Check if username exists
    if (userRepository.existsByUsername(signupRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body("Error: Username is already taken!");
    }

    // Check if email exists
    if (userRepository.existsByEmail(signupRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body("Error: Email is already in use!");
    }

    // Create new user
    User user = new User(
        signupRequest.getUsername(),
        signupRequest.getEmail(),
        passwordEncoder.encode(signupRequest.getPassword()));

    userRepository.save(user);

    return ResponseEntity.ok("User registered successfully!");
  }

  public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
    System.out.println(loginRequest);
    try {

      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              loginRequest.getEmail(),
              loginRequest.getPassword()));
      System.out.println(loginRequest.getPassword());

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);

      // Update last login
      User user = userRepository.findByEmail(loginRequest.getEmail())
          .orElseThrow(() -> new RuntimeException("User not found"));
      user.setLastLogin(LocalDateTime.now());
      userRepository.save(user);

      // Prepare response
      Map<String, String> response = new HashMap<>();
      response.put("token", jwt);
      response.put("username", loginRequest.getEmail());

      return ResponseEntity.ok(response);
    } catch (Exception e) {
      System.out.println(e);
      return ResponseEntity.badRequest().body("Invalid username or password");
    }
  }
}