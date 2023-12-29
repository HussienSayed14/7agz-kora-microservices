package com.accountmicroservice.accounts;


import com.accountmicroservice.accounts.forgotPassword.ForgotPasswordService;
import com.accountmicroservice.accounts.login.LoginService;
import com.accountmicroservice.accounts.login.requests.LoginRequest;
import com.accountmicroservice.accounts.login.responses.LoginResponse;
import com.accountmicroservice.accounts.register.RegisterService;
import com.accountmicroservice.accounts.register.requests.EmailVerificationRequest;
import com.accountmicroservice.accounts.register.requests.GetOtpRequest;
import com.accountmicroservice.accounts.register.requests.RegisterRequest;
import com.accountmicroservice.accounts.register.responses.RequestRegisterResponse;
import com.accountmicroservice.accounts.register.responses.VerifyOtpResponse;
import com.accountmicroservice.aws.AwsService;
import com.accountmicroservice.util.GenericResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/accounts/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Accounts", description = "Apis That Handle User Authentication and Authorization and does not require token")
public class AccountsController {

    private final RegisterService registerService;
    private final LoginService loginService;
    private final ForgotPasswordService forgotPasswordService;
    private final AwsService awsService;




    @Operation(summary = "Verify Registeration Email", description = "Verify the OTP sent to the user's email.")
    @PostMapping("/verifyOtp")
    public ResponseEntity<VerifyOtpResponse> verifyOtp(@RequestBody @Valid EmailVerificationRequest emailVerificationRequest) {
        return registerService.verifyOtp(emailVerificationRequest);
    }

    @Operation(summary = "Register", description = "This Api is used to register a new user after verifying their email.")
    @PostMapping("/register")
    public ResponseEntity<GenericResponses> register(@RequestBody @Valid RegisterRequest registerRequest,
                                                     @RequestParam("profilePic") MultipartFile profilePic) {
        return registerService.register(registerRequest,profilePic);
    }
    @Operation(summary = "Resend OTP", description = "This Api is used to resend the OTP to the user's email.")
    @PostMapping("/resendOtp")
    public void resendRegisterationOtp(@RequestBody @Valid GetOtpRequest otpRequest) {
         registerService.createEmailVerificationOtp(otpRequest.getEmail());
    }

    @Operation(summary = "Login", description = "This Api is used to login a user and return a JWT token.")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest, HttpServletRequest request) {
        return loginService.login(loginRequest,request);
    }

    @Operation(summary = "Forgot Password Request", description = "This Api is used to send a link to the user's email to reset their password.")
    @PostMapping("/forgotPasswordRequest")
    public ResponseEntity<RequestRegisterResponse> forgotPasswordRequest(@RequestBody GetOtpRequest request){
        return forgotPasswordService.forgotPasswordRequest(request);
    }

}
