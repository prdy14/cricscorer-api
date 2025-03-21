package com.example.cricscorer_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.cricscorer_api.entity.Innings;

@Repository
public interface Innings1Repo extends MongoRepository<Innings, String> {
}