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

    public void setServerErrorHappened(){
        this.responseCode = "-1";
        this.responseMessage = "Server error happened";
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.responseStatus = "Falied";
        this.tranDate = DateTimeFormatter.getCurrentDate();
        this.tranTime = DateTimeFormatter.getCurrentTime();
    }
    public void setEmailAlreadyExist(){
        this.responseCode = "1";
        this.responseMessage = "This E-mail already exists";
        this.httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        this.responseStatus = "Falied";
        this.tranDate = DateTimeFormatter.getCurrentDate();
        this.tranTime = DateTimeFormatter.getCurrentTime();
    }

    public void setEmailNotFound(){
        this.responseCode = "2";
        this.responseMessage = "This e-mail is not found please try again";
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.responseStatus = "Falied";
        this.tranDate = DateTimeFormatter.getCurrentDate();
        this.tranTime = DateTimeFormatter.getCurrentTime();
    }

    public void setOtpExpired(){
        this.responseCode = "3";
        this.responseMessage = "This OTP is expired";
        this.httpStatus = HttpStatus.UNAUTHORIZED;
        this.responseStatus = "Falied";
        this.tranDate = DateTimeFormatter.getCurrentDate();
        this.tranTime = DateTimeFormatter.getCurrentTime();
    }

    public void setWrongOtp(){
        this.responseCode = "4";
        this.responseMessage = "Wrong OTP";
        this.httpStatus = HttpStatus.UNAUTHORIZED;
        this.responseStatus = "Falied";
        this.tranDate = DateTimeFormatter.getCurrentDate();
        this.tranTime = DateTimeFormatter.getCurrentTime();
    }

    public void setWrongPassword(){
        this.responseCode = "5";
        this.responseMessage = "Wrong Password";
        this.httpStatus = HttpStatus.UNAUTHORIZED;
        this.responseStatus = "Falied";
        this.tranDate = DateTimeFormatter.getCurrentDate();
        this.tranTime = DateTimeFormatter.getCurrentTime();
    }

    public void setEmailDoesNotExist(){
        this.responseCode = "6";
        this.responseMessage = "This E-mail does not exist";
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.responseStatus = "Falied";
        this.tranDate = DateTimeFormatter.getCurrentDate();
        this.tranTime = DateTimeFormatter.getCurrentTime();
    }

    public void setUserIsBlocked(){
        this.responseCode = "7";
        this.responseMessage = "This user is blocked";
        this.httpStatus = HttpStatus.UNAUTHORIZED;
        this.responseStatus = "Falied";
        this.tranDate = DateTimeFormatter.getCurrentDate();
        this.tranTime = DateTimeFormatter.getCurrentTime();
    }

    public void setUserNotActive(){
        this.responseCode = "8";
        this.responseMessage = "This user is not active";
        this.httpStatus = HttpStatus.UNAUTHORIZED;
        this.responseStatus = "Falied";
        this.tranDate = DateTimeFormatter.getCurrentDate();
        this.tranTime = DateTimeFormatter.getCurrentTime();
    }

    public void setUserIsLocked(){
        this.responseCode = "9";
        this.responseMessage = "You have entered too many wrong passwords, please try again after 1 hour";
        this.httpStatus = HttpStatus.UNAUTHORIZED;
        this.responseStatus = "Falied";
        this.tranDate = DateTimeFormatter.getCurrentDate();
        this.tranTime = DateTimeFormatter.getCurrentTime();
    }

    public void setOtpNotFound(){
        this.responseCode = "10";
        this.responseMessage = "Forgot Password request not found";
        this.httpStatus = HttpStatus.UNAUTHORIZED;
        this.responseStatus = "Falied";
        this.tranDate = DateTimeFormatter.getCurrentDate();
        this.tranTime = DateTimeFormatter.getCurrentTime();
    }


    public void setUserNotVerified() {
        this.responseCode = "11";
        this.responseMessage = "This Email is not verified yet, please verify your email first";
        this.httpStatus = HttpStatus.UNAUTHORIZED;
        this.responseStatus = "Falied";
        this.tranDate = DateTimeFormatter.getCurrentDate();
        this.tranTime = DateTimeFormatter.getCurrentTime();
    }

    public void setWrongOldPassword() {
        this.responseCode = "12";
        this.responseMessage = "You entered wrong old password";
        this.httpStatus = HttpStatus.UNAUTHORIZED;
        this.responseStatus = "Falied";
        this.tranDate = DateTimeFormatter.getCurrentDate();
        this.tranTime = DateTimeFormatter.getCurrentTime();
    }
}
