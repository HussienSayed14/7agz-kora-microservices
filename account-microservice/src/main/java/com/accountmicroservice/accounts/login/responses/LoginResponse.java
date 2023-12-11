package com.accountmicroservice.accounts.login.responses;

import com.accountmicroservice.util.GenericResponses;
import lombok.Setter;

@Setter
public class LoginResponse extends GenericResponses {
    private String token;
    private String firstName;
    private String lastName;
    private String role;

}
