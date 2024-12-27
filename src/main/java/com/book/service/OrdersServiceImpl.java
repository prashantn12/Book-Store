package com.book.service;

import com.book.dto.AddressDTO;
import com.book.dto.OrderDTO;
import com.book.exception.CustomException;
import com.book.model.Book;
import com.book.model.Cart;
import com.book.model.Orders;
import com.book.model.User;
import com.book.repository.BookRepository;
import com.book.repository.CartRepository;
import com.book.repository.OrderRepository;
import com.book.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public ResponseEntity<String> orderByCartID(Long id, AddressDTO addressDTO) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new CustomException("Cart of id not Found"));

        Book book = bookRepository.findById(cart.getBook().getId())
                .orElseThrow(() -> new CustomException("Book of the id not found"));

        if (book.getQuantity() < cart.getQuantity()) {
            throw new CustomException("Insufficient stock for the book");
        }

        book.setQuantity(book.getQuantity() - cart.getQuantity());
        bookRepository.save(book);

        User user = userRepository.findById(cart.getUser().getId())
                .orElseThrow(() -> new CustomException("User not found by id"));

        Orders orders = new Orders();
        orders.setBook(book);
        orders.setUser(user);
        orders.setOrderDateTime(LocalDateTime.now());
        orders.setPrice(cart.getPrice());
        orders.setAddress(addressDTO.getAddress());
        orders.setCancel(false);
        orders.setQuantity(cart.getQuantity());

        orderRepository.save(orders);

        return ResponseEntity.ok("Order placed successfully");
    }

    @Override
    public ResponseEntity<String> orderbyUserId(OrderDTO orderDTO) {
        Book book = bookRepository.findById(orderDTO.getBookId())
                .orElseThrow(() -> new CustomException("Book of the id not found"));
        if (book.getQuantity() < orderDTO.getQuantity()) {
            throw new CustomException("Insufficient stock for the book");
        }
        book.setQuantity(book.getQuantity() - orderDTO.getQuantity());
        bookRepository.save(book);
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new CustomException("User not found by id"));


        Orders orders = new Orders();
        orders.setBook(book);
        orders.setUser(user);
        orders.setOrderDateTime(LocalDateTime.now());
        orders.setPrice(orderDTO.getQuantity()*book.getPrice());
        orders.setAddress(orderDTO.getAddress());
        orders.setCancel(false);
        orders.setQuantity(orderDTO.getQuantity());

        orderRepository.save(orders);

        return ResponseEntity.ok("Order placed successfully");
    }

    @Override
    public List<Orders> getallOrderForUserByToken(Long id) {
        return orderRepository.findByUser_Id(id);
    }

    @Override
    public List<Orders> getallorder() {
        return orderRepository.findByCancelFalse();
    }

    @Override
    public ResponseEntity<String> cancelOrderByOrderId(Long id) {
        Orders orders =orderRepository.findById(id).orElseThrow(()-> new CustomException("Order By Id Not Found"));
        orders.setCancel(true);
        Book book = bookRepository.findById(orders.getBook().getId()).orElseThrow(()-> new CustomException("book not found by id"));
        book.setQuantity(book.getQuantity()+orders.getQuantity());
        bookRepository.save(book);
        orderRepository.save(orders);
        return ResponseEntity.ok("order cancel successfully !");
    }


}
