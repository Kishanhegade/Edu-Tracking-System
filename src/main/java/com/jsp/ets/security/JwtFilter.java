package com.jsp.ets.security;

import com.jsp.ets.user.UserRole;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService  jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("authorization");
        if(token!=null)
        {
            token = token.substring(7);
            if(!token.isEmpty()) {
                Claims claims = jwtService.parseJwt(token);
                String email = claims.get("email", String.class);
                String role = claims.get("role", String.class);
                if(role!=null && email!=null) {
                    UserRole userRole = UserRole.valueOf(role);
                    List<SimpleGrantedAuthority> simpleGrantedAuthorities = userRole.getPrivileges()
                            .stream()
                            .map(privilege-> new SimpleGrantedAuthority(privilege.name()))
                            .toList();
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, userRole, simpleGrantedAuthorities);
                    authenticationToken.setDetails(new WebAuthenticationDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
