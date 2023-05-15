package com.example.librarymanagement.controllers;

import com.example.librarymanagement.dto.request.LoginRequest;
import com.example.librarymanagement.dto.request.RegisterRequest;
import com.example.librarymanagement.dto.response.Response;
import com.example.librarymanagement.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
