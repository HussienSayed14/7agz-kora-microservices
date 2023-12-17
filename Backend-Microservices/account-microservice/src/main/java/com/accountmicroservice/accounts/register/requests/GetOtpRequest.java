package com.accountmicroservice.accounts.register.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class GetOtpRequest {
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    private String email;
}
