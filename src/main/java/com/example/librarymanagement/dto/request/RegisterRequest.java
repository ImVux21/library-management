package com.example.librarymanagement.dto.request;

import com.example.librarymanagement.dto.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
