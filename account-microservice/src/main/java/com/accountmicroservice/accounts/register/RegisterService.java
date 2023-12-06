package com.accountmicroservice.accounts.register;


import com.accountmicroservice.accounts.register.requests.EmailVerificationRequest;

import com.accountmicroservice.accounts.register.requests.GetOtpRequest;
import com.accountmicroservice.accounts.register.responses.RequestRegisterResponse;
import com.accountmicroservice.entities.OTP;
import com.accountmicroservice.entities.User;
import com.accountmicroservice.repositories.OtpRepository;
import com.accountmicroservice.repositories.UserRepository;
import com.accountmicroservice.util.DateTimeFormatter;
import com.accountmicroservice.util.emailSender.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class RegisterService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OtpRepository otpRepository;
    @Autowired
    EmailService emailService;


    public ResponseEntity registerRequest(GetOtpRequest otpRequest) {
        RequestRegisterResponse responseToClient = new RequestRegisterResponse();
        User existingUser = userRepository.findByEmail(otpRequest.getEmail());

        if (existingUser != null) {
            responseToClient.setResponseCode("1");
            responseToClient.setResponseStatus("Failed");
            responseToClient.setResponseMessage("This e-mail already exist");
            return ResponseEntity.badRequest().body(responseToClient);
        }

        String otp = generateOTP(6);

        if(createRegisterRequestOtp(otpRequest.getEmail(), otp)){
            responseToClient.setSuccessful();
            String body = "Please do not Share this OTP with anyone: " + otp + "\nThis OTP will expire in 10 minutes";
            emailService.sendEmail(otpRequest.getEmail(),"7agz Kora EMAIL VERIFICATION", body);
            return ResponseEntity.status(responseToClient.getHttpStatus()).body(responseToClient);
        }
        return ResponseEntity.badRequest().body(responseToClient);
    }

    private boolean createRegisterRequestOtp(String email, String otp) {
        OTP otpRecord = OTP.builder()
                .email(email)
                .otpType("Register")
                .otp(otp)
                .verificationType("Email")
                .creationDate(DateTimeFormatter.getCurrentDate())
                .creationTime(DateTimeFormatter.getCurrentTime())
                .expiryTime(DateTimeFormatter.tenMinutesFromNow())
                .expiryDate(DateTimeFormatter.getCurrentDate())
                .build();
        try {

            otpRepository.save(otpRecord);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static String generateOTP(int length) {
        String numbers = "0123456789";
        Random rndm_method = new Random();
        char[] otp = new char[length];
        for (int i = 0; i < length; i++) {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return new String(otp);
    }
}


