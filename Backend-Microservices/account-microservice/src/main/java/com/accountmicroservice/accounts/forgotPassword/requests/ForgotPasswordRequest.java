package com.accountmicroservice.accounts.forgotPassword.requests;

import lombok.Getter;

@Getter
public class ForgotPasswordRequest {
    private String email;
    private String password;

}
