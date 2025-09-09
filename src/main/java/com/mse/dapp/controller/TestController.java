package com.mse.dapp.controller;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("greeting")
    public String greeting() {
        return "Hello world";
    }
    
}
