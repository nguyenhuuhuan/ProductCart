package com.example.BE.Service;

import com.example.BE.Entity.Order;
import com.example.BE.Entity.OrderDetail;
import com.example.BE.Entity.Products;
import com.example.BE.Repository.OrderDetailRepository;
import com.example.BE.Repository.OrderRepository;
import com.example.BE.Repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailService implements OrderDetailServiceImp{

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void addOrderDetail(OrderDetail orderDetail) {
        Optional<Products> products = productsRepository.findById(orderDetail.getProducts().getProductId());
        Optional<Order> order = orderRepository.findById(orderDetail.getOrder().getOrderId());
        if(!products.isPresent() || !order.isPresent()){
            throw new IllegalStateException(
                    "Order with Id " + orderDetail.getOrder().getOrderId() + "or" +
                            "Product with Id " + orderDetail.getProducts().getProductId() +"does exist"
            );
        }
        orderDetailRepository.saveAndFlush(orderDetail);
    }
}
