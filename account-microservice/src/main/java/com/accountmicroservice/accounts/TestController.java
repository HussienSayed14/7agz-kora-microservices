package com.accountmicroservice.accounts;

import com.accountmicroservice.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    JwtService jwtService;
    @GetMapping
    String test(@RequestHeader("Authorization") String token){
        String jwt = token.substring(7);
        System.out.println(jwtService.extractExpiration(jwt));
        return jwt;
    }
}
