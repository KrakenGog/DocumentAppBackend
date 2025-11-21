package com.mse.dapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mse.dapp.model.User;
import com.mse.dapp.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/test")
@Tag(name = "Testing", description = "Тестовые API endpoints для проверки функциональности")
public class TestController {

    @Autowired
    private UserRepo repo;

    @Operation(
        summary = "Приветственное сообщение",
        description = "Возвращает простое приветственное сообщение для проверки работы API"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Сообщение успешно возвращено"
        )
    })
    @GetMapping("/greeting")
    public String greeting() {
        return "Hello world";
    }
    
    @Operation(
        summary = "Создание пользователя",
        description = "Создает нового пользователя с указанными логином и паролем"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Пользователь успешно создан"
        )
    })
    @GetMapping("/user")
    public String postMethodName(
            @Parameter(description = "Логин пользователя", example = "user123", required = true)
            @RequestParam String login,
            
            @Parameter(description = "Пароль пользователя", example = "password123", required = true)
            @RequestParam String password) {
        repo.save(new User(login, password));
        
        return "Saved";
    }

    @Operation(
        summary = "Admin endpoint",
        description = "Тестовый endpoint для проверки доступа администратора"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Доступ разрешен"
        )
    })
    @GetMapping("/admin")
    public String getMethodName() {
        return "Admin";
    }
}