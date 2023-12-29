package com.accountmicroservice.accounts;


import com.accountmicroservice.accounts.forgotPassword.ForgotPasswordService;
import com.accountmicroservice.accounts.forgotPassword.requests.ForgotPasswordRequest;
import com.accountmicroservice.accounts.profile.ProfileService;
import com.accountmicroservice.accounts.profile.requests.ChangePasswordRequest;
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
    private final ProfileService profileService;



    @Operation(summary = "Forgot Password", description = "Validate the Token sent to the user and change their password.")
    @PostMapping("/forgotPassword")
    public ResponseEntity<GenericResponses> forgotPassword(@RequestBody ForgotPasswordRequest request,
                                                           @RequestHeader("Authorization") String bearerToken) {
        return forgotPasswordService.validateAndChangePassword(request, bearerToken);
    }


    @Operation(summary = "Change Password", description = "Change the user's password.")
    @PostMapping("/changePassword")
    public ResponseEntity<GenericResponses> changePassword(@RequestBody ChangePasswordRequest request,
                                                           @RequestHeader("Authorization") String bearerToken) {
        return profileService.changePassword(request, bearerToken);
    }

}
