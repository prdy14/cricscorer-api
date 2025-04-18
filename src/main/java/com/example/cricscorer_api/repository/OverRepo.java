package com.example.cricscorer_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.cricscorer_api.entity.Over;

@Repository
public interface OverRepo extends MongoRepository<Over, String> {

}
