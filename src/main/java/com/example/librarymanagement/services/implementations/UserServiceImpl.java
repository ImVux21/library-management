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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public User getUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("Người dùng không tồn tại!"));
    }

    @Override
    public ResponseEntity<Response> orderBook(Long id, int quantity, User user) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Sách không tồn tại!"));

        List<Order>  orders = user.getOrders();
        boolean alreadyOrdered = orders.stream().anyMatch(order -> order.getBook().getId().equals(id));

//        if (alreadyOrdered) {
//            Order oldOrder = orders.stream().filter(order -> order.getBook().getId().equals(id)).findFirst().get();
//            oldOrder.setQuantity(oldOrder.getQuantity() + quantity);
//            orderRepository.save(oldOrder);
//        } else {
//            Order order = Order
//                    .builder()
//                    .book(book)
//                    .quantity(quantity)
//                    .user(user)
//                    .build();
//            orderRepository.save(order);
//        }


        Order order = Order
                .builder()
                .book(book)
                .quantity(quantity)
                .user(user)
                .build();
        orderRepository.save(order);

        book.setSoldQuantity(book.getSoldQuantity() + quantity);
        bookRepository.save(book);

        return ResponseEntity.ok(
                Response
                        .builder()
                        .status(200)
                        .message("Đặt hàng thành công!")
                        .build()
        );
    }

    @Override
    public ResponseEntity<Response> reviewBook(Long id, Review review, User currentUser) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Sách không tồn tại!"));

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
//        List<Order> orders = orderRepository.findAllByUser(currentUser);
        return ResponseEntity.ok(
                Response
                        .builder()
                        .status(200)
                        .message("Lấy danh sách đặt hàng thành công!")
                        .data(currentUser.getOrders())
                        .build()
        );
    }

    @Override
    @Transactional
    public ResponseEntity<Response> deleteOrder(User currentUser, Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Đơn hàng không tồn tại!"));
        currentUser.getOrders().removeIf(o -> Objects.equals(o.getId(), id));

        Book book = bookRepository.findById(order.getBook().getId()).get();
        book.setSoldQuantity(order.getBook().getSoldQuantity() - order.getQuantity());

        bookRepository.save(book);
        orderRepository.deleteById(id);
        userRepository.save(currentUser);

        return ResponseEntity.ok(
                Response.builder()
                        .status(200)
                        .message("Hủy đặt hàng thành công")
                        .build()
        );
    }
}
