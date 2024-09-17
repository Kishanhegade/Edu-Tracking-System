package com.jsp.ets.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService  jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("authorization");
        if(token!=null) {
            token = token.substring(7);
            if(!token.isEmpty()) {
                Claims claims = jwtService.parseJwt(token);
                String email = claims.get("email", String.class);
                String role = claims.get("role", String.class);
            }
        }

    }
}
