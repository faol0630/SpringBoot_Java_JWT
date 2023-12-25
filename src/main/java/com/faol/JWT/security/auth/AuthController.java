package com.faol.JWT.security.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor //lombok, contructor por defecto con los atributos final.
public class AuthController {

    @PostMapping("/login")
    public String login (){
        return  "Login from public endpoint";
    }

    @PostMapping("/register")
    public String register(){
        return "Register from public endPoint";
    }
}
