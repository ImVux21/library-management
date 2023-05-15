package com.example.librarymanagement.controllers;

import com.example.librarymanagement.dto.response.Response;
import com.example.librarymanagement.entities.Review;
import com.example.librarymanagement.entities.User;
import com.example.librarymanagement.services.UserService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private final UserService userService;

    @PostMapping("/order-book/{id}")
    public ResponseEntity<Response> orderBook(@PathVariable Long id,
                                              @RequestParam int quantity) {
        return userService.orderBook(id, quantity, getCurrentUser());
    }

    @PostMapping("/review-book/{id}")
    public ResponseEntity<Response> reviewBook(@PathVariable Long id, @RequestBody Review review) {
        return userService.reviewBook(id, review, getCurrentUser());
    }

    @GetMapping("/get-order")
    public ResponseEntity<Response> getOrder() {
        return userService.getOrder(getCurrentUser());
    }

    @DeleteMapping("/delete-order/{id}")
    public ResponseEntity<Response> deleteOrder(@PathVariable Long id) {
        return userService.deleteOrder(getCurrentUser(), id);
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
