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
public class RegisterController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenUtility tokenUtility;

    @GetMapping("/test")
    public String test(){
        return "test ok";
    }
    @GetMapping("/push")
    public String push(){
        return "ok123puhpak";
    }
    @PostMapping("/register")
    public ResponseEntity<?> addUser(@Validated @RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.addUser(userDTO), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Optional<User> login = userService.userLogin(loginRequest);
        if (login != null) {
            return ResponseEntity.ok(new JwtResponse(tokenUtility.createToken(login.get().getId(), login.get().getRole())));
        } else {
            return new ResponseEntity<>("User login not successfully", HttpStatus.ACCEPTED);
        }
    }
}
