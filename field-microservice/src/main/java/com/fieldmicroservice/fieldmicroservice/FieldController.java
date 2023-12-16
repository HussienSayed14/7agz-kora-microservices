package com.fieldmicroservice.fieldmicroservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/field")
public class FieldController {

        @RequestMapping("/test")
        String test(){
            return "Hello from Field Microservice";
        }
}
