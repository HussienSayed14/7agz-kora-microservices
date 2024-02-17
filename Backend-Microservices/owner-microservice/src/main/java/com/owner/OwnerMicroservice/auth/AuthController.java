package com.owner.OwnerMicroservice.auth;

import com.owner.OwnerMicroservice.auth.requests.CreateOwnerRequest;
import com.owner.OwnerMicroservice.util.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<GenericResponse> register(CreateOwnerRequest createOwnerRequest) {
        return authService.register(createOwnerRequest);
    }
}
