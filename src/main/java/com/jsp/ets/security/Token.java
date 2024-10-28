package com.jsp.ets.security;

import com.jsp.ets.cache.CacheService;
import com.jsp.ets.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class Token {
    private final JwtService jwtService;
    private final CacheService cacheService;
    @Value("${my_app.jwt.access_expiry}")
    private long accessExpiry;

    @Value("${my_app.jwt.refresh_expiry}")
    private long refreshExpiry;

    public Token(JwtService jwtService, CacheService cacheService) {
        this.jwtService = jwtService;
        this.cacheService = cacheService;
    }

    public void grantAccessAccessToken(User user, HttpHeaders httpHeaders) {
        String accessToken = jwtService.generateAccessToken(user.getUserId(), user.getEmail(), user.getRole().name());
        cacheService.putCache("accesstoken",accessToken,true);
        httpHeaders.add(HttpHeaders.SET_COOKIE, createCookie("at", accessToken, accessExpiry * 60));
    }

    public void grantAccessRefreshToken(User user, HttpHeaders httpHeaders) {
        String refreshToken = jwtService.generateRefreshToken(user.getUserId(), user.getEmail(), user.getRole().name());
        cacheService.putCache("refreshtoken",refreshToken,true);
        httpHeaders.add(HttpHeaders.SET_COOKIE, createCookie("rt", refreshToken, refreshExpiry * 60));
    }


    public String createCookie(String name, String value, long age) {
        return ResponseCookie.from(name, value)
                .domain("localhost")
                .path("/")
                .secure(false)
                .httpOnly(true)
                .sameSite("Lax")
                .maxAge(age)
                .build()
                .toString();
    }

}
