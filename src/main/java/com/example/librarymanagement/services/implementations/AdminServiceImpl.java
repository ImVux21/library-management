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

        if (book.getTitle() != null) oldBook.setTitle(book.getTitle());
        if (book.getAuthor() != null) oldBook.setAuthor(book.getAuthor());
        if (book.getCoverImage() != null) oldBook.setCoverImage(book.getCoverImage());
        if (book.getReleaseDate() != null) oldBook.setReleaseDate(book.getReleaseDate());
        if (book.getDescription() != null) oldBook.setDescription(book.getDescription());
        if (book.getCategory() != null) oldBook.setCategory(book.getCategory());
        if (book.getTotalPageNum() != null) oldBook.setTotalPageNum(book.getTotalPageNum());

        bookRepository.save(oldBook);
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
        bookRepository.deleteById(id);

        return ResponseEntity.ok(
                Response.builder()
                        .status(200)
                        .message("Xóa thành công!")
                        .build()
        );
    }
}
