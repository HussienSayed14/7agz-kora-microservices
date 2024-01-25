package com.owner.OwnerMicroservice.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class GenericResponse {

    private String responseCode;
    private String responseMessage;
    private String responseStatus;
    private HttpStatus httpStatus;
    private int tranDate;
    private int tranTime;
}
