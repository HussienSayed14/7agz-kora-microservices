package com.apigatewaymicroservice.apigatewaymicroservice.Login.Responses;

import com.apigatewaymicroservice.apigatewaymicroservice.util.GenericResponses;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse extends GenericResponses {

    private String token;
    private String firstName;
    private String lastName;
    private String role;
}
