package com.accountmicroservice.accounts.register.requests;

import jakarta.persistence.Column;
import lombok.Getter;


@Getter
public class RegisterRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private String role;
    private int dateOfBirth;
    private String securityQuestion;
    private String securityAnswer;
    private String nationalId;
    private int creationDate;
    private int creationTime;
    private int failedLoginAttempts;
    private int lastLoginDate;

}
