package com.example.BE.Service;

import com.example.BE.DTO.CartDTO;
import com.example.BE.Entity.Order;

import java.util.Collection;

public interface CartServiceImp {

    void addCartItem(CartDTO cartDTO);

    void removeCartItem(int proId);

    CartDTO updateCartItem(int proId, int quantity);

    Collection<CartDTO> getAllCartItem();

    int getCount();

    double getTotalMoney();

    void removeAll();

//    void saveOrder(Order order);
}
