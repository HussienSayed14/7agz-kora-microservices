package com.accountmicroservice.accounts;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts/api/v1/test")
public class TestController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}
