package com.example.cricscorer_api.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtServices {

  @Value("${jwt.secret}")
  private String jwtSecret;

  public String generateJwtToken(Authentication authentication, long time) {
    UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

    return Jwts.builder()
        .setSubject(userPrincipal.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + time))
        .signWith(getSigningKey(), SignatureAlgorithm.HS512)
        .compact();
  }

  private Key getSigningKey() {
    return Keys.hmacShaKeyFor(jwtSecret.getBytes());
  }

  public String getUsernameFromJwtToken(String token) {

    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public Claims extractClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public boolean isValidToken(String jwt, UserDetails userDetails) {
    final String username = extractUserName(jwt);
    return username.equals(userDetails.getUsername()) && !isTokenExpired(jwt);
  }

  public boolean isTokenExpired(String jwt) {
    return extractClaim(jwt, Claims::getExpiration).before(new Date());
  }

  public String extractUserName(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

    final Claims claims = extractClaims(token);
    return claimsResolver.apply(claims);

  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parserBuilder()
          .setSigningKey(getSigningKey())
          .build()
          .parseClaimsJws(authToken)
          .getBody();
      return true;

    } catch (JwtException e) {
      return false;
    }
  }

  public boolean validateToken(String authToken, String email) {
    try {
      return Jwts.parserBuilder()
          .setSigningKey(getSigningKey())
          .build()
          .parseClaimsJws(authToken)
          .getBody().getSubject().equals(email);

    } catch (JwtException e) {
      return false;
    }
  }
}