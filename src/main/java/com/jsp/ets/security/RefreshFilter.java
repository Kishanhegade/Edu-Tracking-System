package com.jsp.ets.security;

import com.jsp.ets.cache.CacheService;
import com.jsp.ets.exception.InvalidTokenException;
import com.jsp.ets.user.UserRole;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
@Slf4j
@AllArgsConstructor
public class RefreshFilter extends OncePerRequestFilter {
    private JwtService jwtService;
    private CacheService cacheService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       Cookie[] cookies= request.getCookies();
       if(cookies!=null){
           log.info("cookies are present");
           Optional<Cookie> refreshTokenCookie= Arrays.stream(cookies)
                   .filter(cookie -> "rt".equals(cookie.getName()))
                   .findFirst();
           if(refreshTokenCookie.isPresent()){
               log.info("Refresh token is present");
               String token=refreshTokenCookie.get().getValue();
                if(!token.isEmpty() && cacheService.getCache("refreshtoken",token,Boolean.class)){
                   log.info("token is not empty");
                   Claims claims=jwtService.parseJwt(token);
                   String email=claims.get("email",String.class);
                   String role=claims.get("role",String.class);
                   if(email!=null && role!=null){
                       log.info("email and role are not null");
                       UserRole userRole = UserRole.valueOf(role);
                       UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, null, userRole.getPrivileges()
                               .stream()
                               .map(privilage ->new SimpleGrantedAuthority(privilage.name()))
                               .toList());
                       authenticationToken.setDetails(new WebAuthenticationDetails(request));
                       SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                   }
               }
                else
                    throw new InvalidTokenException("the token in currently not in use");

           }
       }
       filterChain.doFilter(request,response);
    }
}
