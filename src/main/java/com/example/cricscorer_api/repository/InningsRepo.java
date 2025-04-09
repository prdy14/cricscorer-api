package com.example.cricscorer_api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.cricscorer_api.entity.Innings;

@Repository
public interface InningsRepo extends MongoRepository<Innings, String> {

 public List<Innings> findAllByMatchId(String matchId);
}