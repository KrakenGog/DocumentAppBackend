package com.mse.dapp.controller;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mse.dapp.model.User;
import com.mse.dapp.repositories.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;




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
    
}
