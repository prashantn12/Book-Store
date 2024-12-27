package com.book.controller;

import com.book.dto.BookDTO;
import com.book.model.Book;
import com.book.service.BookService;
import com.book.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private TokenUtility tokenUtility;

    @PostMapping ("/add")
    public ResponseEntity<?> getUserById(@RequestAttribute("role") String role, @RequestBody Book book) {

        if ("ADMIN".equalsIgnoreCase(role)) {
            return ResponseEntity.ok(bookService.addBook(book));
        } else {
            return ResponseEntity.status(403).body("Access Denied");
        }
    }

    @PutMapping ("/update/{id}")
    public ResponseEntity<?> updatePriceQuantity(@RequestAttribute("role") String role,@PathVariable Long id, @RequestBody BookDTO bookDTO) {

        if ("ADMIN".equalsIgnoreCase(role)) {
            return ResponseEntity.ok(bookService.updatePriceQuantity(id,bookDTO));
        } else {
            return ResponseEntity.status(403).body("Access Denied");
        }
    }

    @GetMapping("/all")
    private ResponseEntity<?> getAllBook(@RequestAttribute("role") String role){
        if ("ADMIN".equalsIgnoreCase(role)) {
            return ResponseEntity.ok(bookService.getAllBook());
        } else {
            return ResponseEntity.status(403).body("Access Denied");
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getBookByID(@RequestAttribute("role") String role,@PathVariable Long id){
        if ("ADMIN".equalsIgnoreCase(role)) {
            return ResponseEntity.ok(bookService.getBookById(id));
        } else {
            return ResponseEntity.status(403).body("Access Denied");
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteBookById(@RequestAttribute("role") String role,@PathVariable Long id){
        if("ADMIN".equalsIgnoreCase(role)){
            return ResponseEntity.ok(bookService.deleteByID(id));
        }else {
            return ResponseEntity.status(403).body("Access Denied");
        }
    }



}
