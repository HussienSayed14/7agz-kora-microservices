package com.accountmicroservice.accounts.login.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {
    @NotBlank(message = "Email is mandatory")
    //@Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Password is mandatory")
    private String password;
}
