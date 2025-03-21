package com.example.cricscorer_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.cricscorer_api.entity.Over;

public interface OverRepo extends MongoRepository<Over,String>{
 
}
