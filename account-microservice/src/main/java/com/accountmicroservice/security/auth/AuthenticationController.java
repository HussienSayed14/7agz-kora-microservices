package com.accountmicroservice.security.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<Doctor> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthenticationRequest request) {
        return authService.authenticate(request);

    }

    @GetMapping("/test/{id}")
    public Optional<Doctor> test(@PathVariable int id) {
        return authService.test(id);
    }
}

