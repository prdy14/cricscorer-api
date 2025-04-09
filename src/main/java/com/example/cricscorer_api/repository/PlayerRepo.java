package com.example.cricscorer_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cricscorer_api.entity.Player;

public interface PlayerRepo extends JpaRepository<Player, String> {

}
