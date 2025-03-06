package com.example.cricscorer_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cricscorer_api.entity.Batter;
import com.example.cricscorer_api.services.InningService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/innings")
public class InningsController {

 @Autowired
 private InningService inningsService;

 @PutMapping("addBatter/{id}")
 public ResponseEntity<?> addBatter(@PathVariable Long id, @RequestBody Batter batter) {

  return inningsService.addBatter(id, batter);
 }
}
