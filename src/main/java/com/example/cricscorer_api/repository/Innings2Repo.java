package com.example.cricscorer_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.cricscorer_api.entity.Innings;

public interface Innings2Repo extends MongoRepository<Innings, String> {

}
