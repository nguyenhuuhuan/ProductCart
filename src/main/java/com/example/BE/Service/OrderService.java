package com.example.BE.Service;

import com.example.BE.Entity.Customer;
import com.example.BE.Entity.Order;
import com.example.BE.Repository.CustomerRepository;
import com.example.BE.Repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderServiceImp{

    private final OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void addOrder(Order order) {
        Optional<Customer> customer1 = customerRepository.findById(order.getCustomer().getCusId());
        if(!customer1.isPresent()){
            throw new IllegalStateException("Customer with "+ order.getCustomer().getCusId() +"not exist");
        }
        order.setCustomer(customer1.get());
        orderRepository.saveAndFlush(order);
    }

    @Override
    public Optional<Order> getOrderById(int orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public Collection<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Transactional
    @Override
    public List<Order> getOrderByCusId(Integer cusId) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findById(cusId)
                .orElseThrow(() -> new IllegalStateException(
                        "Customer with " + cusId + " does exist"
                )));
        return orderRepository.getListOrderByCusId(cusId);
    }
}
