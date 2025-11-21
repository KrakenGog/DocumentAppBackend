package com.mse.dapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mse.dapp.model.User;
import com.mse.dapp.repository.UserRepo;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name = "User Management", description = "API для управления пользователями")
public class UserController {

    @Autowired
    private UserRepo repo;

    @Operation(
        summary = "Получить пользователя по ID",
        description = "Возвращает информацию о пользователе по его идентификатору"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Пользователь найден",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = User.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Пользователь не найден"
        )
    })
    @GetMapping("/getById")
    public ResponseEntity<User> getMethodName(
            @Parameter(
                description = "ID пользователя",
                example = "1",
                required = true
            )
            @RequestParam("id") Long id) {
        return repo.findById(id)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Создать нового пользователя",
        description = "Создает нового пользователя в системе"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Пользователь успешно создан",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = User.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Неверные данные пользователя"
        )
    })
    @PostMapping("/newUser")
    public ResponseEntity<User> postMethodName(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Данные пользователя",
                required = true,
                content = @Content(schema = @Schema(implementation = User.class))
            )
            @RequestBody User user) {
        var savedUser = repo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }   
}