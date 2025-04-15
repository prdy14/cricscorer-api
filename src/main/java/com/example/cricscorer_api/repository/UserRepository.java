package com.example.cricscorer_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cricscorer_api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<com.example.cricscorer_api.entity.User, String> {

 Optional<User> findByEmail(String user);

 Optional<User> findByName(String name);

 Boolean existsByName(String username);

 Boolean existsByEmail(String email);

}
