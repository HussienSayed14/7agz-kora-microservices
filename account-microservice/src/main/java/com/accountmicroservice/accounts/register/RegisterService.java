package com.accountmicroservice.accounts.register;

import com.accountmicroservice.accounts.register.requests.EmailVerificationRequest;
import com.accountmicroservice.accounts.register.requests.GetOtpRequest;
import com.accountmicroservice.accounts.register.responses.RequestRegisterResponse;
import com.accountmicroservice.accounts.register.responses.VerifyOtpResponse;
import com.accountmicroservice.entities.OTP;
import com.accountmicroservice.entities.User;
import com.accountmicroservice.repositories.OtpRepository;
import com.accountmicroservice.repositories.UserRepository;
import com.accountmicroservice.util.DateTimeFormatter;
import com.accountmicroservice.util.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
           responseToClient.setEmailAlreadyExist();
            return ResponseEntity.badRequest().body(responseToClient);
        }

        String otp = EmailService.generateOTP(6);

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



    public ResponseEntity verifyOtp(EmailVerificationRequest emailVerificationRequest) {
        VerifyOtpResponse responseToClient = new VerifyOtpResponse();
        OTP otpRecord = otpRepository.getRegisterationOtpByEmail(emailVerificationRequest.getEmail());
        if (otpRecord == null) {
            responseToClient.setEmailNotFound();
            return ResponseEntity.badRequest().body(responseToClient);
        }

        if (isOtpValid(otpRecord, responseToClient)
                && isOtpCorrect(emailVerificationRequest.getOtp(), otpRecord, responseToClient)) {
            otpRepository.delete(otpRecord);
            responseToClient.setSuccessful();
            return ResponseEntity.status(responseToClient.getHttpStatus()).body(responseToClient);
        }
        return ResponseEntity.status(responseToClient.getHttpStatus()).body(responseToClient);
    }

    private boolean isOtpValid(OTP otpRecord, VerifyOtpResponse responseToClient) {
        if (otpRecord.getExpiryDate() != DateTimeFormatter.getCurrentDate() ||
                otpRecord.getExpiryTime() >= DateTimeFormatter.getCurrentTime()) {
            responseToClient.setOtpExpired();
            return false;
        }
        return true;
    }

    private boolean isOtpCorrect(String userOtp, OTP originalOtp, VerifyOtpResponse responseToClient) {
        if (originalOtp.getOtp().equals(userOtp)){
            return true;
        }
        responseToClient.setWrongOtp();
        return false;
    }


}


