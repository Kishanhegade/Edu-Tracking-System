package com.jsp.ets.user;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpRequest {
    @NotNull(message = "email cannot be null")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$")
    private String email;

    @Min(value = 100000, message = "OTP must be at least 6 digits")
    @Max(value = 999999, message = "OTP must be at most 6 digits")
    private Integer otp;
}
