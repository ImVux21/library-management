package com.example.librarymanagement.controllers;

import com.example.librarymanagement.dto.response.Response;
import com.example.librarymanagement.entities.Book;
import com.example.librarymanagement.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/add-book")
    public ResponseEntity<Response> addBook(@RequestBody Book book) {
        return adminService.addBook(book);
    }

    @PutMapping("/modify-book/{id}")
    public ResponseEntity<Response> modifyBook(@RequestBody Book book, @PathVariable Long id) {
        return adminService.modifyBook(book, id);
    }

    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<Response> deleteBook(@PathVariable Long id) {
        return adminService.deleteBook(id);
    }

}
