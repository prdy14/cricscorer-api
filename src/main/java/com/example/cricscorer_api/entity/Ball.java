package com.example.cricscorer_api.entity;

import lombok.Data;

@Data

public class Ball {
 private String id;
 private int runs;
 private int ballNumber;
 private boolean isWide;
 private boolean isNoBall;
}
