package com.accountmicroservice.accounts.register.requests;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class RegisterRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private int dateOfBirth;
    private String securityQuestion;
    private String securityAnswer;
    private String nationalId;
}
