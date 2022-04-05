package com.example.BE.Service;


import com.example.BE.Entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerServiceImp {

    List<Customer> getAllCustomer();

    Optional<Customer> getCusById(Integer cusId);

    void addCustomer(Customer customer);

    void updateCustomer(Integer cusId, Customer customer);

}
