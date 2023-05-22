package com.example.librarymanagement.services.implementations;

import com.example.librarymanagement.dto.response.Response;
import com.example.librarymanagement.entities.Book;
import com.example.librarymanagement.repositories.BookRepository;
import com.example.librarymanagement.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public ResponseEntity<Response> viewAllBooks() {
        return ResponseEntity.ok(
                Response
                        .builder()
                        .status(200)
                        .message("Lấy toàn bộ sách thành công!")
                        .data(bookRepository.findAll())
                        .build()
        );
    }

    @Override
    public ResponseEntity<Response> viewBookDetails(Long id) {
        Book book = bookRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Sách không tồn tại!"));

        return ResponseEntity.ok(
                Response
                        .builder()
                        .status(200)
                        .message("Lấy sách thành công!")
                        .data(book)
                        .build()
        );
    }
}
