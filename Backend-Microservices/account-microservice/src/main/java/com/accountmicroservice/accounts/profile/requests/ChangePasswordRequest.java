package com.accountmicroservice.accounts.profile.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequest {

    @NotNull
    private String oldPassword;
    @NotNull
    private String newPassword;
}
