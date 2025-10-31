package com.mse.dapp.controller;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mse.dapp.model.User;
import com.mse.dapp.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserRepo repo;

    @GetMapping("greeting")
    public String greeting() {
        return "Hello world";
    }
    
    @GetMapping("/user")
    public String postMethodName(@RequestParam String login, @RequestParam String password) {
        repo.save(new User(login, password));
        
        return "Saved";
    }

    @GetMapping("/admin")
    public String getMethodName() {
        return "Admin";
    }

    
    
}
