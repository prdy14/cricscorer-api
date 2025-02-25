package com.example.cricscorer_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {

 private String username;

 private String password;

}
