package com.accountmicroservice.accounts;

import com.accountmicroservice.accounts.login.LoginService;
import com.accountmicroservice.accounts.register.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts/api/v1")
public class AccountsController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private LoginService loginService;
    @GetMapping("/register")
    public ResponseEntity<?> register() {
        return null;
    }
}
