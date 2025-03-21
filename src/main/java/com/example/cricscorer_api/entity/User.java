package com.example.cricscorer_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

  public User(String username2, String email2, String encode) {
    this.username = username2;
    this.email = email2;
    this.password = encode;
    this.createdAt = LocalDateTime.now();
  }

  public User(String username2, String email2) {
    this.username = username2;
    this.email = email2;
    this.createdAt = LocalDateTime.now();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;

  @NotBlank
  @Size(max = 50)
  private String username;

  @NotBlank
  @Size(max = 100)
  @Email
  private String email;

  @Size(max = 120)
  private String password;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "last_login")
  private LocalDateTime lastLogin;

  @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonIgnore
  private List<Match> matchesCreated;

}