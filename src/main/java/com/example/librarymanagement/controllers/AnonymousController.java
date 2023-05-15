package com.example.librarymanagement.controllers;

import com.example.librarymanagement.dto.response.Response;
import com.example.librarymanagement.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AnonymousController {
    private final BookService bookService;

    @GetMapping("/view-all-books")
    public ResponseEntity<Response> viewAllBooks() {
        return bookService.viewAllBooks();
    }

    @GetMapping("/view-book-details/{id}")
    public ResponseEntity<Response> viewBookDetails(@PathVariable Long id) {
        return bookService.viewBookDetails(id);
    }
}
