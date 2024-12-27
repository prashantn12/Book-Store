package com.book.service;

import com.book.dto.UserDTO;
import com.book.model.LoginRequest;
import com.book.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
     String updateUserByID(Long id, UserDTO userDTO);


    User getUserById(Long id) ;

    String addUser(UserDTO userDTO);

    List<User> getAllUser();

    Optional<User> userLogin(LoginRequest loginRequest);

    String deleteUserByID(Long id);
}
