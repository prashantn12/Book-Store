package com.book.repository;

import com.book.model.Cart;
import com.book.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long > {
    List<Orders> findByUser_Id(Long user_id);
    List<Orders> findByCancelFalse();
}
