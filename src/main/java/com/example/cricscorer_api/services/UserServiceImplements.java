package com.example.cricscorer_api.services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.core.Authentication;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cricscorer_api.dto.LoginRequest;
import com.example.cricscorer_api.dto.SignupRequest;
import com.example.cricscorer_api.entity.Player;
import com.example.cricscorer_api.entity.Role;
import com.example.cricscorer_api.entity.User;
import com.example.cricscorer_api.repository.PlayerRepo;
import com.example.cricscorer_api.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImplements implements UserService {

  private final AuthenticationManager authenticationManager;
  private final JwtServices jwtUtils;
  private final UserRepository userRepo;
  private final PasswordEncoder passwordEncoder;
  private final PlayerRepo playerRepo;

  public ResponseEntity<?> authenticateUser(LoginRequest loginRequest, HttpSession httpSession) {
    try {

      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              loginRequest.getEmail(),
              loginRequest.getPassword()));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication, 15 * 60 * 1000);
      String refreshToken = jwtUtils.generateJwtToken(authentication, 7 * 60 * 60 * 1000);

      httpSession.setAttribute("token", jwt);
      httpSession.setAttribute("refresh token", refreshToken);

      User user = userRepo.findByEmail(loginRequest.getEmail())
          .orElseThrow(() -> new RuntimeException("User not found"));

      userRepo.save(user);

      httpSession.setAttribute("userId", user.getUserId());
      // Prepare response
      Map<String, String> response = new HashMap<>();
      response.put("token", jwt);
      response.put("username", loginRequest.getEmail());
      response.put("refresh token", refreshToken);

      return ResponseEntity.ok(response);
    } catch (Exception e) {
      System.out.println(e);
      return ResponseEntity.badRequest().body("Invalid username or password");
    }
  }

  @Override
  public ResponseEntity<?> registerUser(SignupRequest signupRequest) {
    User user = userRepo.findByEmail(signupRequest.getEmail()).orElse(null);
    if (user != null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email already existed");
    }
    user = userRepo.findByName(signupRequest.getUsername()).orElse(null);
    if (user != null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username already existed");
    }

    user = User.builder().email(signupRequest.getEmail()).name(signupRequest.getUsername())
        .password(passwordEncoder.encode(signupRequest.getPassword())).role(Role.ADMIN).build();

    Player player = new Player();

    userRepo.save(user);
    player.setId(user.getUserId());
    player.setName(user.getName());
    playerRepo.save(player);

    return ResponseEntity.status(HttpStatus.CREATED).body("user registered successfully");

  }
}
