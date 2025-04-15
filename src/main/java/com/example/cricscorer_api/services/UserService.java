package com.example.cricscorer_api.services;

import org.springframework.http.ResponseEntity;

import com.example.cricscorer_api.dto.LoginRequest;
import com.example.cricscorer_api.dto.SignupRequest;

import jakarta.servlet.http.HttpSession;

public interface UserService {

 ResponseEntity<?> authenticateUser(LoginRequest loginRequest, HttpSession httpSession);

 ResponseEntity<?> registerUser(SignupRequest signupRequest);

}
