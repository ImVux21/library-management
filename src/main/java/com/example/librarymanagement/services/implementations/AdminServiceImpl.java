package com.example.librarymanagement.services.implementations;

import com.example.librarymanagement.dto.response.Response;
import com.example.librarymanagement.entities.Book;
import com.example.librarymanagement.repositories.BookRepository;
import com.example.librarymanagement.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final BookRepository bookRepository;

    @Override
    public ResponseEntity<Response> addBook(Book bookRequest) {
        if (bookRepository.findByTitle(bookRequest.getTitle()) != null) {
            throw new RuntimeException("Sách đã tồn tại trong thư viện");
        }

        return ResponseEntity.ok(
                Response
                        .builder()
                        .status(200)
                        .message("Sách đã được thêm thành công!")
                        .data(bookRepository.save(bookRequest))
                        .build()
        );
    }

    @Override
    public ResponseEntity<Response> modifyBook(Book book, Long id) {
        Book oldBook = bookRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Sách này không tồn tại!"));

        oldBook.setTitle(book.getTitle());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setCoverImage(book.getCoverImage());
        oldBook.setReleaseYear(book.getReleaseYear());
        oldBook.setDescription(book.getDescription());
        oldBook.setCategory(book.getCategory());
        oldBook.setTotalPageNum(book.getTotalPageNum());

        return ResponseEntity.ok(
                Response.builder()
                        .status(200)
                        .message("Cập nhật thông tin sách thành công")
                        .data(oldBook)
                        .build()
        );
    }

    @Override
    public ResponseEntity<Response> deleteBook(Long id) {
        bookRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Sách không tồn tại!"));
        return ResponseEntity.ok(
                Response.builder()
                        .status(200)
                        .message("Xóa thành công!")
                        .build()
        );
    }
}
