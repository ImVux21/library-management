package com.example.librarymanagement.services;

import org.springframework.http.ResponseEntity;
import com.example.librarymanagement.dto.response.Response;


public interface BookService {
    ResponseEntity<Response> viewAllBooks();

    ResponseEntity<Response> viewBookDetails(Long id);
}
