package com.plata.Plata.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @GetMapping("test-123")
    public String getAccount() {
        return "Authenticated??";
    }
}
