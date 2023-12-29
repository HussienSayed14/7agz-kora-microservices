package com.accountmicroservice.accounts.forgotPassword;

import com.accountmicroservice.accounts.forgotPassword.requests.ForgotPasswordRequest;
import com.accountmicroservice.accounts.register.requests.GetOtpRequest;
import com.accountmicroservice.accounts.register.responses.RequestRegisterResponse;
import com.accountmicroservice.config.JwtService;
import com.accountmicroservice.entities.OTP;
import com.accountmicroservice.entities.User;
import com.accountmicroservice.repositories.OtpRepository;
import com.accountmicroservice.repositories.UserRepository;
import com.accountmicroservice.util.DateTimeFormatter;
import com.accountmicroservice.util.EmailService;
import com.accountmicroservice.util.GenericResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForgotPasswordService {

    private final UserRepository userRepository;
    private final OtpRepository otpRepository;
    private final EmailService emailService;
    private final JwtService jwtService;

    public ResponseEntity<RequestRegisterResponse> forgotPasswordRequest(GetOtpRequest otpRequest){
        RequestRegisterResponse responseToClient = new RequestRegisterResponse();
        User user = userRepository.findByEmail(otpRequest.getEmail());

        if(user == null){
            responseToClient.setEmailDoesNotExist();
            return ResponseEntity.badRequest().body(responseToClient);
        }
        String token = jwtService.generateForgotPasswordToken(user);
        String authLink = "http://localhost:8080/accounts/api/v1/secured/forgotPassword?token=" + token;
        if(createForgotPasswordRecord(otpRequest.getEmail(), authLink)){
            responseToClient.setSuccessful();
            String body = "Please do not Share this link with anyone \n" + authLink + "\nThis link will expire in 10 minutes";
            emailService.sendEmail(otpRequest.getEmail(),"7agz Kora FORGOT PASSWORD REQUEST", body);
            return ResponseEntity.status(responseToClient.getHttpStatus()).body(responseToClient);
        }
        return ResponseEntity.status(responseToClient.getHttpStatus()).body(responseToClient);

    }


    public ResponseEntity<GenericResponses> validateAndChangePassword(ForgotPasswordRequest forgotPasswordRequest, String token){

        GenericResponses responseToClient = new GenericResponses();
        User user = userRepository.findByEmail(forgotPasswordRequest.getEmail());
        if(user == null){
            responseToClient.setEmailDoesNotExist();
            return ResponseEntity.badRequest().body(responseToClient);
        }
        OTP otpRecord = otpRepository.getForgotPasswordOtpByEmail(forgotPasswordRequest.getEmail());
        if(otpRecord == null){
            responseToClient.setOtpNotFound();
            return ResponseEntity.badRequest().body(responseToClient);
        }
        if(isTokenValid(otpRecord, responseToClient)
                && isTokenCorrect(token, otpRecord, responseToClient)){
            user.setPassword(forgotPasswordRequest.getPassword());
            userRepository.save(user);
            responseToClient.setSuccessful();
        }

        return ResponseEntity.status(responseToClient.getHttpStatus()).body(responseToClient);

    }

    private boolean isTokenValid(OTP otpRecord, GenericResponses responseToClient) {
        if (otpRecord.getExpiryDate() != DateTimeFormatter.getCurrentDate() ||
                otpRecord.getExpiryTime() <= DateTimeFormatter.getCurrentTime()) {
            responseToClient.setOtpExpired();
            return false;
        }
        return true;
    }

    private boolean isTokenCorrect(String userToken, OTP otpRecord, GenericResponses responseToClient) {
        if (otpRecord.getOtp().equals(userToken)){
            return true;
        }
        responseToClient.setWrongOtp();
        return false;
    }



    private boolean createForgotPasswordRecord(String email, String token) {
        OTP otpRecord = OTP.builder()
                .email(email)
                .otpType("ForgotPassword")
                .otp(token)
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
