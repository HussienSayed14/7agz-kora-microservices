package com.accountmicroservice.accounts.register.requests;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegisterRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private String  dateOfBirth;
    private String securityQuestion;
    private String securityAnswer;
    private String nationalId;
}
