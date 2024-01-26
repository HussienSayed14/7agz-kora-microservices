package com.owner.OwnerMicroservice;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner/api/v1/test")
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "Owner Microservice is working";
    }
}
