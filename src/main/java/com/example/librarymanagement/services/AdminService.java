package com.example.librarymanagement.services;

import com.example.librarymanagement.dto.response.Response;
import com.example.librarymanagement.entities.Book;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    ResponseEntity<Response> addBook(Book book);

    ResponseEntity<Response> modifyBook(Book book, Long id);

    ResponseEntity<Response> deleteBook(Long id);
}
