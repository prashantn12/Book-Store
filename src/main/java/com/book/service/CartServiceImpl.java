package com.book.service;

import com.book.exception.CustomException;
import com.book.model.Book;
import com.book.model.Cart;
import com.book.model.User;
import com.book.repository.BookRepository;
import com.book.repository.CartRepository;
import com.book.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public ResponseEntity<String> RemoveFromCartbyId(Long id) {
        cartRepository.findById(id).orElseThrow(() -> new CustomException("Book not found By id !"));
        cartRepository.deleteById(id);

        return ResponseEntity.ok("delete successfully ");
    }

    @Override
    public Cart addtocart(Long id, Long bookId, int  quantity) {
        Cart c = new Cart();
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new CustomException("Book not found By id !"));
        User user= userRepository.findById(id).orElseThrow(() -> new CustomException("User not found By id !"));
        c.setBook(book);
        c.setUser(user);
        c.setPrice(book.getPrice()*quantity);
        if(book.getQuantity()-quantity >=0){
            c.setQuantity(quantity);
        }else {
            throw new CustomException("Book out of Stock !");
        }
        cartRepository.save(c);
        return c;
    }

    @Override
    public ResponseEntity<String> RemoveByUserIDToken(Long id) {
//        List<Cart> carts= cartRepository.findByUser_Id(id);
        cartRepository.deleteByUser_Id(id);
        return ResponseEntity.ok("Cart is removed from user");
    }

    @Override
    public List<Cart> getallcartitems() {
        List<Cart> carts = cartRepository.findAll();
        return carts;
    }

    @Override
    public List<Cart> getAllCartsForUser(Long id) {
        return cartRepository.findByUser_Id(id);
    }





}
