package com.example.BE.Controller;

import com.example.BE.Entity.Customer;
import com.example.BE.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/")
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    @GetMapping("/{cusId}")
    public Optional<Customer> getCusById(@PathVariable(name = "cusId") Integer cusId){
        return customerService.getCusById(cusId);
    }

    @PostMapping("/")
    public void addCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
    }

    @PutMapping("/{cusId}")
    public ResponseEntity updateCustomer(@PathVariable(name = "cusId") Integer cusId, @RequestBody Customer customer){
        customerService.updateCustomer(cusId, customer);
        return ResponseEntity.status(200).body(customer);

    }

}
