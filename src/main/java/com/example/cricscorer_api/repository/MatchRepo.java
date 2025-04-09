package com.example.cricscorer_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.cricscorer_api.entity.Match;

@Repository
public interface MatchRepo extends JpaRepository<Match, String> {

 @Query(nativeQuery = true, value = "select * from matches limit 10 offset :offse")
 public List<Match> ge10Matches(@Param("offse") int offse);
}
