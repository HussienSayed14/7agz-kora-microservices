package com.accountmicroservice.accounts.register;

import com.accountmicroservice.accounts.register.requests.EmailVerificationRequest;
import com.accountmicroservice.accounts.register.requests.RegisterRequest;
import com.accountmicroservice.accounts.register.responses.VerifyOtpResponse;
import com.accountmicroservice.aws.AwsService;
import com.accountmicroservice.entities.OTP;
import com.accountmicroservice.entities.Roles;
import com.accountmicroservice.entities.User;
import com.accountmicroservice.repositories.OtpRepository;
import com.accountmicroservice.repositories.UserRepository;
import com.accountmicroservice.util.DateTimeFormatter;
import com.accountmicroservice.util.EmailService;
import com.accountmicroservice.util.GenericResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final OtpRepository otpRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final AwsService awsService;


    public void createEmailVerificationOtp(String email) {
        System.out.println("createEmailVerificationOtp");

        User user = userRepository.findByEmail(email);
        if(user.isVerified()){
            System.out.println("User Is Already Verified");
            return;
        }
        String otp = EmailService.generateOTP(6);
        System.out.println("OTP generated");
        if(createRegisterRequestOtp(email, otp)){
            System.out.println("Email will be sent");
            String body = "Please do not Share this OTP with anyone: " + otp + "\nThis OTP will expire in 10 minutes";
            emailService.sendEmail(email,"EMAIL VERIFICATION", body);
        }
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
        OTP foundOtp = otpRepository.getRegisterationOtpByEmail(email);
        if (foundOtp != null) {
            otpRepository.delete(foundOtp);
        }

        try {
            otpRepository.save(otpRecord);
            System.out.println("OTP saved");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ResponseEntity<VerifyOtpResponse> verifyOtp(EmailVerificationRequest emailVerificationRequest) {
        VerifyOtpResponse responseToClient = new VerifyOtpResponse();
        OTP otpRecord = otpRepository.getRegisterationOtpByEmail(emailVerificationRequest.getEmail());
        if (otpRecord == null) {
            responseToClient.setEmailNotFound();
            return ResponseEntity.badRequest().body(responseToClient);
        }

        if (isOtpValid(otpRecord, responseToClient)
                && isOtpCorrect(emailVerificationRequest.getOtp(), otpRecord, responseToClient)) {
            otpRepository.delete(otpRecord);
            User user = userRepository.findByEmail(emailVerificationRequest.getEmail());
            user.setVerified(true);
            userRepository.save(user);
            responseToClient.setSuccessful();
            return ResponseEntity.status(responseToClient.getHttpStatus()).body(responseToClient);
        }
        return ResponseEntity.status(responseToClient.getHttpStatus()).body(responseToClient);
    }

    private boolean isOtpValid(OTP otpRecord, VerifyOtpResponse responseToClient) {
        if (otpRecord.getExpiryDate() != DateTimeFormatter.getCurrentDate() ||
                otpRecord.getExpiryTime() <= DateTimeFormatter.getCurrentTime()) {
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




    public ResponseEntity<GenericResponses> register(RegisterRequest registerRequest){
        GenericResponses responseToClient = new GenericResponses();
        User existingUser = userRepository.findByEmail(registerRequest.getEmail());

        if (existingUser != null) {
            responseToClient.setEmailAlreadyExist();
            return ResponseEntity.badRequest().body(responseToClient);
        }

        User user = User.builder()
                .email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .phoneNumber(registerRequest.getPhoneNumber())
                .role(Roles.USER)
                .dateOfBirth(123456)
                .securityQuestion(registerRequest.getSecurityQuestion())
                .securityAnswer(registerRequest.getSecurityAnswer())
                .isBlocked(false)
                .isVerified(false)
                .isActive(true)
                .nationalId(registerRequest.getNationalId())
                .creationDate(DateTimeFormatter.getCurrentDate())
                .creationTime(DateTimeFormatter.getCurrentTime())
                .failedLoginAttempts(0)
                .lastLoginDate(DateTimeFormatter.getCurrentDate())
                .lastLoginTime(DateTimeFormatter.getCurrentTime())
                .lockRemovalDate(0)
                .build();
        try {
            userRepository.save(user);
            createEmailVerificationOtp(user.getEmail());
            responseToClient.setSuccessful();
        } catch (Exception e) {
            responseToClient.setServerErrorHappened();
        }
        return ResponseEntity.status(responseToClient.getHttpStatus()).body(responseToClient);
    }
}


