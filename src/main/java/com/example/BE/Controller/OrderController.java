package com.example.BE.Controller;

import com.example.BE.Entity.Order;
import com.example.BE.Service.CartService;
import com.example.BE.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    CartService cartService;

    @PostMapping
    public ResponseEntity addOrder(@Validated @RequestBody Order order)
    {
        orderService.addOrder(order);
        return ResponseEntity.status(200).body(order);
    }

    @GetMapping("/")
    public ResponseEntity getAllOrder(){
        return ResponseEntity.status(200).body(orderService.getAllOrder());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity getOrderById(@PathVariable(name = "orderId") Integer orderId)
    {
        orderService.getOrderById(orderId);
        return ResponseEntity.status(200).body(orderService.getOrderById(orderId));
    }

    @GetMapping("/cus/{cusId}")
    public ResponseEntity getOrderByCusId(@PathVariable(name = "cusId") Integer cusId)
    {
        orderService.getOrderByCusId(cusId);
        return ResponseEntity.status(200).body(orderService.getOrderByCusId(cusId));
    }

}
