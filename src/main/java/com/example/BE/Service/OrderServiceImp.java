package com.example.BE.Service;

import com.example.BE.Entity.Order;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OrderServiceImp {
    void addOrder(Order order);

    Optional<Order> getOrderById(int orderId);

    Collection<Order> getAllOrder();

    List<Order> getOrderByCusId(Integer cusId);
}
