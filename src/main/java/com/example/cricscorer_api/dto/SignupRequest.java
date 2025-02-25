package com.example.cricscorer_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupRequest {

 private String username;

 private String email;

 private String password;

}
