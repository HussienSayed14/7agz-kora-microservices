package com.accountmicroservice.accounts;


import com.accountmicroservice.accounts.forgotPassword.ForgotPasswordService;
import com.accountmicroservice.accounts.forgotPassword.requests.ForgotPasswordRequest;
import com.accountmicroservice.accounts.register.requests.GetOtpRequest;
import com.accountmicroservice.util.GenericResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts/api/v1/secured")
@RequiredArgsConstructor
public class SecuredAccountsController {

    private final ForgotPasswordService forgotPasswordService;



    @Operation(summary = "Forgot Password", description = "Validate the Token sent to the user and change their password.")
    @PostMapping("/forgotPassword")
    public ResponseEntity<GenericResponses> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        return forgotPasswordService.validateAndChangePassword(request, null);
    }

}
