package com.jsp.ets.utility;

import com.jsp.ets.user.User;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CacheHelper {

    @CachePut(cacheNames = {"non_verified_users"}, key = "#user.email")
    public User userCache(User user) {
        return user;
    }
    @Cacheable(cacheNames = {"otps"}, key = "#otp")
    public Integer otpCache(Integer otp) {
        return otp;
    }
}
