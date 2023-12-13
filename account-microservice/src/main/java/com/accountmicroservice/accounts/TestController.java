package com.accountmicroservice.accounts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping
    String test(@RequestHeader("Authorization") String token){
        String jwt = token.substring(7);
        return jwt;
    }
}
