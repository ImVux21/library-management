package com.example.librarymanagement.services.implementations;

import com.example.librarymanagement.dto.response.Response;
import com.example.librarymanagement.entities.Book;
import com.example.librarymanagement.entities.Order;
import com.example.librarymanagement.entities.Review;
import com.example.librarymanagement.entities.User;
import com.example.librarymanagement.repositories.BookRepository;
import com.example.librarymanagement.repositories.OrderRepository;
import com.example.librarymanagement.repositories.ReviewRepository;
import com.example.librarymanagement.repositories.UserRepository;
import com.example.librarymanagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public User getUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public ResponseEntity<Response> orderBook(Long id, int quantity, User user) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book haven't added"));

        Order order = Order
                .builder()
                .book(book)
                .quantity(quantity)
                .build();

        User currentUser = getUserByEmail(user.getEmail());
        order.setUser(currentUser);
        currentUser.getOrders().add(order);

        book.setSoldQuantity(book.getSoldQuantity() + quantity);

        userRepository.save(currentUser);
        orderRepository.save(order);
        bookRepository.save(book);

        return ResponseEntity.ok(
                Response
                        .builder()
                        .status(200)
                        .message("Order successfully!")
                        .build()
        );
    }

    @Override
    public ResponseEntity<Response> reviewBook(Long id, Review review, User currentUser) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book haven't added"));

        review.setUser(currentUser);
        review.setBook(book);

        book.getReviews().add(review);
        bookRepository.save(book);
        reviewRepository.save(review);

        return ResponseEntity.ok(
                Response
                        .builder()
                        .status(200)
                        .build()
        );
    }

    @Override
    public ResponseEntity<Response> getOrder(User currentUser) {
        return ResponseEntity.ok(
                Response
                        .builder()
                        .status(200)
                        .message("Get order successfully!")
                        .data(currentUser.getOrders())
                        .build()
        );
    }

    @Override
    public ResponseEntity<Response> deleteOrder(User currentUser, Long id) {
        Order order = currentUser.getOrders().stream().filter(o -> o.getId()==id).findFirst().get();
        currentUser.getOrders().remove(order);

        Book book = bookRepository.findByTitle(order.getBook().getTitle());
        book.setSoldQuantity(order.getBook().getSoldQuantity() - order.getQuantity());

        bookRepository.save(book);
        orderRepository.delete(order);
        userRepository.save(currentUser);

        return ResponseEntity.ok(
                Response.builder().status(200).message("Cancel order successfully").build()
        );
    }
}
