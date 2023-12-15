package com.accountmicroservice.accounts;

import com.accountmicroservice.accounts.forgotPassword.ForgotPasswordService;
import com.accountmicroservice.accounts.forgotPassword.requests.ForgotPasswordRequest;
import com.accountmicroservice.accounts.login.LoginService;
import com.accountmicroservice.accounts.login.requests.LoginRequest;
import com.accountmicroservice.accounts.register.RegisterService;
import com.accountmicroservice.accounts.register.requests.EmailVerificationRequest;
import com.accountmicroservice.accounts.register.requests.GetOtpRequest;
import com.accountmicroservice.accounts.register.requests.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts/api/v1")
@Tag(name = "Accounts", description = "The Accounts API")
public class AccountsController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @Operation(summary = "Create User Register Request", description = "When a user wants to register, they send their email and receive an OTP to verify their email.")
    @PostMapping("/registerRequest")
    public ResponseEntity registerRequest(@RequestBody @Valid GetOtpRequest otpRequest) {
        return registerService.registerRequest(otpRequest);
    }
    @Operation(summary = "Verify Registeration Email", description = "Verify that the OTP sent to the user is correct.")
    @PostMapping("/verifyRegisterationEmail")
    public ResponseEntity verifyRegisterationEmail(@RequestBody @Valid EmailVerificationRequest emailVerificationRequest) {
        return registerService.verifyOtp(emailVerificationRequest);
    }
    @Operation(summary = "Register", description = "Register the user with the given information.")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequest registerRequest) {
        return registerService.register(registerRequest);
    }

    @Operation(summary = "Login", description = "Login with the given email and password.")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest){
        System.out.println("login request: " + loginRequest.getEmail());
        return loginService.login(loginRequest);
    }

    @Operation(summary = "Forgot Password Request", description = "When a user forgets their password, they send their email and receive an OTP to verify their email.")
    @PostMapping("forgotPasswordRequest")
    public ResponseEntity forgotPasswordRequest(@RequestBody GetOtpRequest request){
        return forgotPasswordService.forgotPasswordRequest(request);
    }

    @Operation(summary = "Forgot Password", description = "Validate the Token sent to the user and change their password.")
    @PostMapping("/forgotPassword")
    public ResponseEntity forgotPassword(@RequestBody ForgotPasswordRequest request) {
        return forgotPasswordService.validateAndChangePassword(request, null);
    }


}
