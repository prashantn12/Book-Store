package com.book.repository;

import com.book.model.Cart;
import com.book.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findByUser_Id(Long user_Id);
    void deleteByUser_Id(Long user_Id);

}
