package com.example.librarymanagement.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoResponse {
    private String email;
    private String name;
    private String role;
}
