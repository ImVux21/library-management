package com.example.librarymanagement.entities;

import com.example.librarymanagement.dto.response.UserInfoResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int rating;

    private String comment;

    @ManyToOne
    @JsonIgnore
    private Book book;

    @ManyToOne
    private User user;

    public UserInfoResponse getUser() {
        return UserInfoResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }
}
