package com.accountmicroservice.accounts.login;

import com.accountmicroservice.accounts.login.requests.LoginRequest;
import com.accountmicroservice.accounts.login.responses.LoginResponse;
import com.accountmicroservice.entities.User;
import com.accountmicroservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;
    public ResponseEntity login(LoginRequest loginRequest) {
        LoginResponse responseToClient = new LoginResponse();
        User user = userRepository.findByEmail(loginRequest.getEmail());

        if(user == null){
           responseToClient.setEmailDoesNotExist();
            return ResponseEntity.status(responseToClient.getHttpStatus()).body(responseToClient);
        }

        if(!isUserLocked(user, responseToClient)
                && !isUserBlocked(user, responseToClient)
                && isUserActive(user, responseToClient)){

            if(isPasswordCorrect(user, loginRequest, responseToClient)) {
                responseToClient.setSuccessful();
                //TODO: generate token
                responseToClient.setToken(null);

            } else {
                if(reachedMaxFailAttempts(user, responseToClient)){
                    user.setLocked(true);
                    userRepository.save(user);
                    return ResponseEntity.status(responseToClient.getHttpStatus()).body(responseToClient);
                }
                responseToClient.setWrongPassword();
                user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
                userRepository.save(user);
            }
        }

        return ResponseEntity.status(responseToClient.getHttpStatus()).body(responseToClient);
    }

    private boolean isUserActive(User user, LoginResponse responseToClient) {
        if(!user.isActive()){
            responseToClient.setUserNotActive();
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
            responseToClient.setUserIsLocked();
            return true;
        }
        return false;
    }

    private boolean isPasswordCorrect(User user, LoginRequest loginRequest, LoginResponse responseToClient) {
        if(BCrypt.checkpw(loginRequest.getPassword(), user.getPassword())){
            return true;
        }
        return false;
    }

    private boolean reachedMaxFailAttempts(User user, LoginResponse responseToClient) {
        if(user.getFailedLoginAttempts() >= 4){
            responseToClient.setUserIsLocked();
            return true;
        }
        return false;
    }
}
