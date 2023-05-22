package com.example.librarymanagement.services;

import com.example.librarymanagement.dto.request.LoginRequest;
import com.example.librarymanagement.dto.request.RegisterRequest;
import com.example.librarymanagement.dto.response.Response;
import com.example.librarymanagement.entities.User;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<Response> register(RegisterRequest registerRequest);

    ResponseEntity<Response> login(LoginRequest loginRequest);

    ResponseEntity<Response> getUserInfo(User user);
}
