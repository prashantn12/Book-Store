package com.book.service;

import com.book.dto.UserDTO;
import com.book.exception.CustomException;
import com.book.model.LoginRequest;
import com.book.model.User;
import com.book.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private  UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
       User user  = userRepository.findById(id).orElseThrow(()->new CustomException("Id Not Found Exception"));
       return user;
    }

    @Override
    public String addUser(UserDTO userDTO) {
        User user= mapToEntity(userDTO);
        user.setUpdatedDate(LocalDate.now());
         userRepository.save(user);
         return "User add Successfully !";
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> userLogin(LoginRequest loginRequest) {
        Optional<User> userLogin = userRepository.findByEmailIdAndPassword(loginRequest.getEmailId(), loginRequest.getPassword());

        if (userLogin.isPresent()) {
            return userLogin;
        } else {
            return null;
        }
    }

    @Override
    public String deleteUserByID(Long id) {
        getUserById(id);
        userRepository.deleteById(id);
        return "User Deleted Successfully !";
    }

    @Override
    public String updateUserByID(Long id, UserDTO userDTO) {
        User u = getUserById(id);
        u.setFName(userDTO.getFName());
        u.setLName(userDTO.getLName());
        u.setDob(userDTO.getDob());
        u.setPassword(userDTO.getPassword());
        u.setRole(userDTO.getRole());
        u.setEmailId(userDTO.getEmailId());
        u.setUpdatedDate(LocalDate.now());
        userRepository.save(u);
        return "User Updated Successfully !";
    }

    public User mapToEntity(UserDTO userDTO){
        User user = new User();
        user.setFName(userDTO.getFName());
        user.setLName(userDTO.getLName());
        user.setDob(userDTO.getDob());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        user.setEmailId(userDTO.getEmailId());
        return user;


    }

}
