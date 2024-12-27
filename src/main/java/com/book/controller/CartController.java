package com.book.controller;

import com.book.model.Book;
import com.book.model.Cart;
import com.book.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public Cart addtocart(@RequestAttribute("id") Long id, @RequestParam Long BookId,@RequestParam int quantity) {
        return cartService.addtocart(id,BookId,quantity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> RemoveFromCart(@PathVariable Long id){
        return cartService.RemoveFromCartbyId(id);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> RemoveByUserIDToken(@RequestAttribute("id") Long id){
        return cartService.RemoveByUserIDToken(id);
    }
    @GetMapping("/userCart")
    private List<Cart> getallcartsforuser(@RequestAttribute("id") Long id){
        return cartService.getAllCartsForUser(id);
    }

    @GetMapping("/all")
    private List<Cart> getallcartitems(){
        return cartService.getallcartitems();
    }










}
