package com.accountmicroservice.accounts;

import com.accountmicroservice.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class TestController {

    @Autowired
    JwtService jwtService;
    @GetMapping("/test")
    String test(){
          return "Hello from Accounts Microservice";
    }
}
