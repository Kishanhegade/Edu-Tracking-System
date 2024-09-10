package com.jsp.ets.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpRequest {
    @NotNull(message = "email cannot be null")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$")
    private String email;

    @Pattern(regexp = "^\\d{6}$", message = "OTP must be a 6-digit number")
    private int otp;
}
