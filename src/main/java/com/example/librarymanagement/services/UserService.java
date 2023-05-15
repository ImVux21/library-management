package com.example.librarymanagement.services;

import com.example.librarymanagement.dto.response.Response;
import com.example.librarymanagement.entities.Review;
import com.example.librarymanagement.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails getUserByEmail(String userEmail);

    ResponseEntity<Response> orderBook(Long id, int quantity, User user);

    ResponseEntity<Response> reviewBook(Long id, Review review, User currentUser);

    ResponseEntity<Response> getOrder(User currentUser);

    ResponseEntity<Response> deleteOrder(User currentUser, Long id);
}
