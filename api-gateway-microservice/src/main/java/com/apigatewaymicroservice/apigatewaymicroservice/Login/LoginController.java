package com.apigatewaymicroservice.apigatewaymicroservice.Login;

import com.apigatewaymicroservice.apigatewaymicroservice.Login.Requests.LoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class LoginController {


    private final LoginService loginService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest){
        System.out.println("login request: " + loginRequest.getEmail());
        return loginService.login(loginRequest);
    }

}
