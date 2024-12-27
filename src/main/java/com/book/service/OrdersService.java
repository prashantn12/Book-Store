package com.book.service;

import com.book.dto.AddressDTO;
import com.book.dto.OrderDTO;
import com.book.model.Orders;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrdersService {
    ResponseEntity<String> orderByCartID(Long id, AddressDTO addressDTO);

    ResponseEntity<String> orderbyUserId(OrderDTO orderDTO);

    List<Orders> getallOrderForUserByToken(Long id);

    List<Orders> getallorder();

    ResponseEntity<String> cancelOrderByOrderId(Long id);
}
