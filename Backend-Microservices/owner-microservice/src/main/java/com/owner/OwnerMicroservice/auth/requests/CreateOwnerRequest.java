package com.owner.OwnerMicroservice.auth.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOwnerRequest {
    private String userName;
    private String email;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String nationalID;

}
