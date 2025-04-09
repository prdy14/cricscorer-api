package com.example.cricscorer_api.repository;

import com.example.cricscorer_api.entity.Batter;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatterRepo extends MongoRepository<Batter, String> {

}
