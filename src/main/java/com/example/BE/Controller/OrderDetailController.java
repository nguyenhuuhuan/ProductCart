package com.example.BE.Controller;

import com.example.BE.Entity.OrderDetail;
import com.example.BE.Service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderDetail")
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;

    @PostMapping
    public ResponseEntity addOrderDetail(@RequestBody OrderDetail orderDetail)
    {
        orderDetailService.addOrderDetail(orderDetail);
        return ResponseEntity.status(200).body(orderDetail);
    }
}
