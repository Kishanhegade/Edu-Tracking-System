package com.jsp.ets.utility;

import com.jsp.ets.user.User;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CacheHelper {

    @CachePut(cacheNames ={"non_verified_users"}, key = "#user.email")
    public User userCache(User user){
        return user;
    }

    @CachePut(cacheNames = {"otps"},key = "#email")
    public Integer otpCache(Integer otp, String email){
        return otp;
    }

    @Cacheable(cacheNames = {"otps"}, key = "#email")
    public Integer getCachedOtp(String email) {
        return 0;
    }

    @Cacheable(cacheNames = {"non_verified_users"}, key = "#email")
    public User getRegisteringUser(String email) {
        return new User();
    }


}