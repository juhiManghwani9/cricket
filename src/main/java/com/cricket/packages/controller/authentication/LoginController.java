package com.cricket.packages.controller.authentication;

import com.cricket.packages.response.LoginResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    @GetMapping(path = "/login")
    public LoginResponse login() {
        return new LoginResponse("You are authenticated");
    }
}
