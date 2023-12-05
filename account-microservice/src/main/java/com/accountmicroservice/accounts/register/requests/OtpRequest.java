package com.accountmicroservice.accounts.register.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class OtpRequest {

    private String otp;
    @NotBlank(message = "Email is mandatory")
    private String email;
}
