package com.example.cricscorer_api.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Over {
 @Id
 private String id;
 private int overNumber;
 private int runs;
 private int wickets;
 private int ballCount;
 private List<Ball> balls;
 private String bowerId;
}