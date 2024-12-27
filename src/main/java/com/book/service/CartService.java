package com.book.service;

import com.book.model.Cart;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {

    ResponseEntity<String> RemoveFromCartbyId(Long id) ;

    Cart addtocart(Long id, Long bookId, int  quantity);

    ResponseEntity<String> RemoveByUserIDToken(Long id);

    List<Cart> getallcartitems();

    List<Cart> getAllCartsForUser(Long id);
}

