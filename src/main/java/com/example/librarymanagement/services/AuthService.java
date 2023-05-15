package com.example.librarymanagement.services;

import com.example.librarymanagement.dto.request.LoginRequest;
import com.example.librarymanagement.dto.request.RegisterRequest;
import com.example.librarymanagement.dto.response.Response;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<Response> register(RegisterRequest registerRequest);

    ResponseEntity<Response> login(LoginRequest loginRequest);
}
