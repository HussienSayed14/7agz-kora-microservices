package com.accountmicroservice.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class GenericResponses {
    private String responseCode;
    private String responseMessage;
    private String  responseStatus;
    @JsonIgnore
    private HttpStatus httpStatus;
    private int tranDate;
    private int tranTime;


    public void setSuccessful(){
        this.responseCode = "0";
        this.responseMessage = "Successful";
        this.httpStatus = HttpStatus.OK;
        this.responseStatus = "Success";
        this.tranDate = DateTimeFormatter.getCurrentDate();
        this.tranTime = DateTimeFormatter.getCurrentTime();
    }
}
