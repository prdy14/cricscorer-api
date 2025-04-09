package com.example.cricscorer_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cricscorer_api.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

 Optional<User> findByEmail(String email);

 Boolean existsByUsername(String username);

 Boolean existsByEmail(String email);
}
