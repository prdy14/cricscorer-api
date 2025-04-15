package com.example.cricscorer_api.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.cricscorer_api.services.JwtServices;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAutenticationFilter extends OncePerRequestFilter {

  private final JwtServices jwtServices;
  private final UserDetailsService userDetailsService;

  @Override
  @SuppressWarnings("null")
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    HttpSession session = request.getSession(false);
    System.out.println(session);
    if (session != null && session.getAttribute("token") != null) {
      String jwt = (String) session.getAttribute("token");
      try {
        if (jwt != null && SecurityContextHolder.getContext().getAuthentication() == null) {
          String username = jwtServices.getUsernameFromJwtToken(jwt);
          UserDetails userDetails = userDetailsService.loadUserByUsername(username);
          System.out.println(username + " dfsd  " + userDetails.getUsername());
          if (userDetails != null && jwtServices.isValidToken(jwt, userDetails)) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());

            authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
          } else {

            System.out.println("username");
          }

        }
      } catch (Exception e) {
        System.out.println("error  " + e);
      }
    }
    filterChain.doFilter(request, response);

  }
}
