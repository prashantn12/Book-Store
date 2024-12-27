package com.book.controller;

import com.book.dto.UserDTO;
import com.book.model.JwtResponse;
import com.book.model.LoginRequest;
import com.book.model.User;
import com.book.service.UserService;
import com.book.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenUtility tokenUtility;
    @GetMapping("/testok")
    public String test(){
        return "hii";
    }

    @GetMapping("/details")
    public ResponseEntity<?> getUser(@RequestAttribute("role") String role,@RequestAttribute("id") Long id) {
        System.out.println("user");
        if ("USER".equalsIgnoreCase(role)||"ADMIN".equalsIgnoreCase(role)) {
            return ResponseEntity.ok(userService.getUserById(id));
        } else {
            return ResponseEntity.status(403).body("Access Denied");
        }
    }
    @GetMapping("/admin/{id}")
    public ResponseEntity<?> getUserById(@RequestAttribute("role") String role,@PathVariable Long id) {

        if ("ADMIN".equalsIgnoreCase(role)) {
            return ResponseEntity.ok(userService.getUserById(id));
        } else {
            return ResponseEntity.status(403).body("Access Denied");
        }
    }
    @GetMapping("/admin/all")
    public ResponseEntity<?> getAllUser(@RequestAttribute("role") String role) {

        if ("ADMIN".equalsIgnoreCase(role)) {
            return ResponseEntity.ok(userService.getAllUser());
        } else {
            return ResponseEntity.status(403).body("Access Denied");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@RequestAttribute("role") String role,@PathVariable Long id) {

        if ("ADMIN".equalsIgnoreCase(role)) {
            return ResponseEntity.ok(userService.deleteUserByID(id));
        } else {
            return ResponseEntity.status(403).body("Access Denied");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUserById(@RequestAttribute("role") String role,@PathVariable Long id,@RequestBody UserDTO userDTO) {

        if ("ADMIN".equalsIgnoreCase(role)) {
            return ResponseEntity.ok(userService.updateUserByID(id,userDTO));
        } else {
            return ResponseEntity.status(403).body("Access Denied");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUserByIdUser(@RequestAttribute("role") String role,@RequestAttribute("id") Long id,@RequestBody UserDTO userDTO) {

        if ("USER".equalsIgnoreCase(role)) {
            return ResponseEntity.ok(userService.updateUserByID(id,userDTO));
        } else {
            return ResponseEntity.status(403).body("Access Denied");
        }
        
    }

}
