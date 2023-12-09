package com.accountmicroservice.accounts;

import com.accountmicroservice.accounts.login.LoginService;
import com.accountmicroservice.accounts.login.requests.LoginRequest;
import com.accountmicroservice.accounts.register.RegisterService;
import com.accountmicroservice.accounts.register.requests.EmailVerificationRequest;
import com.accountmicroservice.accounts.register.requests.GetOtpRequest;
import com.accountmicroservice.accounts.register.requests.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts/api/v1")
public class AccountsController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private LoginService loginService;

    @PostMapping("/registerRequest")
    public ResponseEntity registerRequest(@RequestBody @Valid GetOtpRequest otpRequest) {
        return registerService.registerRequest(otpRequest);
    }
    @PostMapping("/verifyRegisterationEmail")
    public ResponseEntity verifyRegisterationEmail(@RequestBody @Valid EmailVerificationRequest emailVerificationRequest) {
        return registerService.verifyOtp(emailVerificationRequest);
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequest registerRequest) {
        return registerService.register(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest){
        return loginService.login(loginRequest);
    }

}
