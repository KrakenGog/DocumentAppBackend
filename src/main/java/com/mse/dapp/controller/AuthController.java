package com.mse.dapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mse.dapp.dto.AuthRequest;
import com.mse.dapp.dto.AuthResponse;
import com.mse.dapp.service.UserService;
import com.mse.dapp.util.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "API для аутентификации и получения JWT токенов")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService,
                          JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @Operation(
        summary = "Аутентификация пользователя",
        description = "Выполняет аутентификацию пользователя и возвращает JWT токен"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Успешная аутентификация",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = AuthResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Неверные учетные данные"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Внутренняя ошибка сервера"
        )
    })

    @PostMapping("/test")
    public void test(@RequestBody AuthRequest authRequest){
        
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {
        log.debug("Trying to auth");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        
        final UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}