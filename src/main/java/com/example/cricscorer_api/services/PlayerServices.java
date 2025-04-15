package com.example.cricscorer_api.services;

import org.springframework.http.ResponseEntity;

import com.example.cricscorer_api.dto.AddPlayerDto;

public interface PlayerServices {
 ResponseEntity<?> findByEmail(String eamil);

 ResponseEntity<?> addPlayer(AddPlayerDto addPlayerDto);
}
