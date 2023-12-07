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

    public void setEmailAlreadyExist(){
        this.responseCode = "1";
        this.responseMessage = "This E-mail already exists";
        this.httpStatus = HttpStatus.OK;
        this.responseStatus = "Falied";
        this.tranDate = DateTimeFormatter.getCurrentDate();
        this.tranTime = DateTimeFormatter.getCurrentTime();
    }

    public void setEmailNotFound(){
        this.responseCode = "2";
        this.responseMessage = "This e-mail is not found please try again";
        this.httpStatus = HttpStatus.OK;
        this.responseStatus = "Falied";
        this.tranDate = DateTimeFormatter.getCurrentDate();
        this.tranTime = DateTimeFormatter.getCurrentTime();
    }

    public void setOtpExpired(){
        this.responseCode = "3";
        this.responseMessage = "This OTP is expired";
        this.httpStatus = HttpStatus.OK;
        this.responseStatus = "Falied";
        this.tranDate = DateTimeFormatter.getCurrentDate();
        this.tranTime = DateTimeFormatter.getCurrentTime();
    }

    public void setWrongOtp(){
        this.responseCode = "4";
        this.responseMessage = "Wrong OTP";
        this.httpStatus = HttpStatus.OK;
        this.responseStatus = "Falied";
        this.tranDate = DateTimeFormatter.getCurrentDate();
        this.tranTime = DateTimeFormatter.getCurrentTime();
    }

}
