package com.accountmicroservice.accounts.register.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class EmailVerificationRequest {

    @NotBlank(message = "OTP is mandatory")
    @Pattern(regexp = "[0-9]{6}", message = "OTP must be 6 digits")
    private String otp;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    private String email;
}
