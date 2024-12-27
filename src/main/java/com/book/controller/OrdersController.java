package com.book.controller;

import com.book.dto.AddressDTO;
import com.book.dto.OrderDTO;
import com.book.model.Orders;
import com.book.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.LinkOption;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @PostMapping("/cart/{id}")
    public ResponseEntity<String> orderByCartID(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        return ordersService.orderByCartID(id, addressDTO);
    }

    @PostMapping("/orderr")
    public ResponseEntity<String> orderByUserId(@RequestBody OrderDTO orderDTO){
        return ordersService.orderbyUserId(orderDTO);
    }
    @PutMapping("/cancel/{id}")
    public ResponseEntity<String> cancelOrderByOrderId(@PathVariable("id") Long id){
        return ordersService.cancelOrderByOrderId(id);
    }

    @GetMapping("/gelall")
    public List<Orders> getallOrder(){
        return ordersService.getallorder();
    }

    @GetMapping("/getalltoken")
    public List<Orders> getAllOrder(@RequestAttribute("id") Long id){
        return ordersService.getallOrderForUserByToken(id);
    }








}
