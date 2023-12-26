package com.faol.JWT.security.demo;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/demo")
public class DemoController {

    //after entering user and password, it shows this endpoint due to @GetMapping:

    @GetMapping("/message")
    public String getMessage(){
        return "Message from protected endpoint";
    }

}
