package com.accountmicroservice.accounts.register.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestRegisterResponse {
    private String responseMessage;
    private String responseCode;
    private String responseStatus;
}
