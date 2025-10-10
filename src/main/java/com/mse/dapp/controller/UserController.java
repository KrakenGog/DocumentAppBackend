package com.mse.dapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mse.dapp.model.User;
import com.mse.dapp.repositories.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo repo;

    @GetMapping("/getById")
    public ResponseEntity<User> getMethodName(@RequestParam Long id) {
        return repo.findById(id)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/newUser")
    public ResponseEntity<User> postMethodName(@RequestBody User user) {
        var savedUser = repo.save(user);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }   
    
}
