package com.accountmicroservice.accounts.login;

import com.accountmicroservice.accounts.login.requests.LoginRequest;
import com.accountmicroservice.accounts.login.responses.LoginResponse;
import com.accountmicroservice.aws.AwsService;
import com.accountmicroservice.config.JwtService;
import com.accountmicroservice.entities.LoginAudit;
import com.accountmicroservice.entities.User;
import com.accountmicroservice.repositories.LoginAuditRepository;
import com.accountmicroservice.repositories.UserRepository;
import com.accountmicroservice.util.DateTimeFormatter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final LoginAuditRepository loginAuditRepository;
    private final AwsService awsService;

    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest,HttpServletRequest request) {
        LoginResponse responseToClient = new LoginResponse();
        User user = userRepository.findByEmail(loginRequest.getEmail());
        String userPhoto;

        if(user == null){
           responseToClient.setEmailDoesNotExist();
            return ResponseEntity.status(responseToClient.getHttpStatus()).body(responseToClient);
        }

        if(!isUserLocked(user, responseToClient)
                && !isUserBlocked(user, responseToClient)
                && isUserActive(user, responseToClient)
                && isUserVerified(user, responseToClient)){

            if(isPasswordCorrect(user, loginRequest, responseToClient)) {
                responseToClient.setFirstName(user.getFirstName());
                responseToClient.setLastName(user.getLastName());
                responseToClient.setRole(String.valueOf(user.getRole()));
                responseToClient.setToken(jwtService.generateToken(user));
                try {

                    if(awsService.doesObjectExist(user.getEmail())){
                        userPhoto = awsService.getUserPhoto(user.getEmail());
                    } else {
                        userPhoto = awsService.getUserPhoto("defaultUser");
                    }
                } catch (Exception e){
                    userPhoto = awsService.getUserPhoto("defaultUser");

                }
                responseToClient.setPhotoUrl(userPhoto);
                user.setFailedLoginAttempts(0);
                userRepository.save(user);
                responseToClient.setSuccessful();
            } else {
                if(reachedMaxFailAttempts(user, responseToClient)){
                    user.setLocked(true);
                    user.setLockRemovalDate(DateTimeFormatter.getCurrentDate());
                    user.setLockRemovalTime(DateTimeFormatter.hoursFromNow(1));
                    userRepository.save(user);
                }
            }
        }

        createLoginAudit(user, responseToClient,request.getRemoteAddr(),request.getHeader("User-Agent"));
        return ResponseEntity.status(responseToClient.getHttpStatus()).body(responseToClient);
    }

    private boolean isUserActive(User user, LoginResponse responseToClient) {
        if(!user.isActive()){
            responseToClient.setUserNotActive();
            return false;
        }
        return true;
    }

    private boolean isUserVerified(User user, LoginResponse responseToClient) {
        if(!user.isVerified()){
            responseToClient.setUserNotVerified();
            return false;
        }
        return true;
    }


    private boolean isUserBlocked(User user, LoginResponse responseToClient) {
        if(user.isBlocked()){
            responseToClient.setUserIsBlocked();
            return true;
        }
        return false;
    }

    private boolean isUserLocked(User user, LoginResponse responseToClient) {
        if(user.isLocked()){
            if(user.getLockRemovalDate() > DateTimeFormatter.getCurrentDate()
                    || user.getLockRemovalTime() >= DateTimeFormatter.getCurrentTime()){
                responseToClient.setUserIsLocked();
                return true;
            }
            user.setFailedLoginAttempts(0);
            user.setLocked(false);
            userRepository.save(user);
            return false;
        }
        return false;
    }

    private boolean isPasswordCorrect(User user, LoginRequest loginRequest, LoginResponse responseToClient) {
        if(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            return true;
        }
        user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
        userRepository.save(user);
        responseToClient.setWrongPassword();
        return false;
    }

    private boolean reachedMaxFailAttempts(User user, LoginResponse responseToClient) {
        if(user.getFailedLoginAttempts() >= 4){
            responseToClient.setUserIsLocked();
            return true;
        }
        return false;
    }

    private void createLoginAudit(User user,LoginResponse responseToClient, String ipAddress, String userAgent) {
        LoginAudit loginAudit = LoginAudit.builder()
                .userEmail(user.getEmail())
                .ipAddress(ipAddress)
                .loginUserAgent(userAgent)
                .loginStatus(responseToClient.getResponseStatus())
                .loginMessage(responseToClient.getResponseMessage())
                .loginDate(DateTimeFormatter.getCurrentDate())
                .loginTime(DateTimeFormatter.getCurrentTime())
                .build();

        try {
            loginAuditRepository.save(loginAudit);
        } catch (Exception e){
            System.out.println("Error Saving Login Audit");

        }
    }

}