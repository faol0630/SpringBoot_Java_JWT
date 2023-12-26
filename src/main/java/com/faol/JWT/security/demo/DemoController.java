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

    //despues de ingresar user y password entra aca por defecto si tiene @GetMapping:

    @PostMapping("/message")
    public String getMessage(){
        return "Message from protected endpoint";
    }

}
