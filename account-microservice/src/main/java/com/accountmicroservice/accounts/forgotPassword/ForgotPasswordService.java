package com.accountmicroservice.accounts.forgotPassword;

import com.accountmicroservice.accounts.forgotPassword.requests.ForgotPasswordRequest;
import com.accountmicroservice.accounts.register.requests.GetOtpRequest;
import com.accountmicroservice.accounts.register.responses.RequestRegisterResponse;
import com.accountmicroservice.entities.OTP;
import com.accountmicroservice.entities.User;
import com.accountmicroservice.repositories.OtpRepository;
import com.accountmicroservice.repositories.UserRepository;
import com.accountmicroservice.util.DateTimeFormatter;
import com.accountmicroservice.util.EmailService;
import com.accountmicroservice.util.GenericResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    OtpRepository otpRepository;
    @Autowired
    EmailService emailService;

    public ResponseEntity forgotPasswordRequest(GetOtpRequest otpRequest){
        String otp = EmailService.generateOTP(6);
        RequestRegisterResponse responseToClient = new RequestRegisterResponse();
        User user = userRepository.findByEmail(otpRequest.getEmail());

        if(user == null){
            responseToClient.setEmailDoesNotExist();
            return ResponseEntity.badRequest().body(responseToClient);
        }
        if(createRegisterRequestOtp(otpRequest.getEmail(), otp)){
            responseToClient.setSuccessful();
            String body = "Please do not Share this OTP with anyone: " + otp + "\nThis OTP will expire in 10 minutes";
            emailService.sendEmail(otpRequest.getEmail(),"7agz Kora EMAIL VERIFICATION", body);
            return ResponseEntity.status(responseToClient.getHttpStatus()).body(responseToClient);
        }
        return ResponseEntity.status(responseToClient.getHttpStatus()).body(responseToClient);

    }

    public ResponseEntity validateAndChangePassword(ForgotPasswordRequest forgotPasswordRequest, String token){
        //TODO: Validate using Token instead of OTP.

        GenericResponses responseToClient = new GenericResponses();
        User user = userRepository.findByEmail(forgotPasswordRequest.getEmail());
        user.setPassword(forgotPasswordRequest.getPassword());
        userRepository.save(user);
        responseToClient.setSuccessful();
        return ResponseEntity.status(responseToClient.getHttpStatus()).body(responseToClient);

    }


    private boolean createRegisterRequestOtp(String email, String otp) {
        OTP otpRecord = OTP.builder()
                .email(email)
                .otpType("ForgotPassword")
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

}
