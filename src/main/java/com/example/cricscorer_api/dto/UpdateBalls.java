package com.example.cricscorer_api.dto;

import java.util.List;

import com.example.cricscorer_api.entity.Ball;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBalls {
 private String id;
 private List<Ball> balls;
 private int ballNo;
}
