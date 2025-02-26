package com.example.cricscorer_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.cricscorer_api.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

 Optional<Team> findByTeamName(String teamName);

}
