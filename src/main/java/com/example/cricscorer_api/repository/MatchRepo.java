package com.example.cricscorer_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cricscorer_api.entity.Match;

@Repository
public interface MatchRepo extends JpaRepository<Match, Long> {

}
