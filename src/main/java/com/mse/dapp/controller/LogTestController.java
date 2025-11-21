package com.mse.dapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RestController
@RequestMapping("/test/logs")
@Tag(name = "Log Testing", description = "API для тестирования системы логирования и ротации логов")
public class LogTestController {
    
    private final AtomicLong counter = new AtomicLong(0);
    
    @Operation(
        summary = "Генерация тестовых логов",
        description = "Генерирует различные типы логов (TRACE, DEBUG, INFO, WARN, ERROR) для тестирования системы логирования"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Логи успешно сгенерированы"
        )
    })
    @GetMapping("/generate")
    public String generateLogs() {
        long count = counter.incrementAndGet();
        
        // Генерация разных типов логов
        log.trace("Trace message #{} - Detailed debugging information", count);
        log.debug("Debug message #{} - Debug information", count);
        log.info("Info message #{} - Application events", count);
        log.warn("Warning message #{} - Warning events", count);
        log.error("Error message #{} - Error events", count);
        
        // Генерация большого количества логов для тестирования ротации
        for (int i = 0; i < 100; i++) {
            log.info("Bulk log message #{} - This is message {} of 100 in bulk operation for testing log rotation. User action: {}, Timestamp: {}", 
                    count, i, "test-action", System.currentTimeMillis());
        }
        
        return String.format("Generated logs #%d. Check logs directory for rolling files.", count);
    }
    
    @Operation(
        summary = "Генерация ошибок",
        description = "Генерирует исключения и ошибки для тестирования логирования ошибок"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Ошибки успешно сгенерированы"
        )
    })
    @GetMapping("/error")
    public String generateErrors() {
        long count = counter.incrementAndGet();
        
        try {
            // Имитация ошибки
            if (count % 2 == 0) {
                throw new RuntimeException("Simulated error for testing error log rotation #" + count);
            }
        } catch (Exception e) {
            log.error("Caught exception in error generation endpoint", e);
        }
        
        return String.format("Generated error log #%d", count);
    }
}