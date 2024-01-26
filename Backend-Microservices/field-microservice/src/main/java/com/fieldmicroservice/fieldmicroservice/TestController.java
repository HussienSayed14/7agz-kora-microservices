package com.fieldmicroservice.fieldmicroservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/field/api/v1/test")
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "Field Microservice is working";

    }
}
