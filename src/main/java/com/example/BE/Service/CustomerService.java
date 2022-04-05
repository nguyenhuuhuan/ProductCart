package com.example.BE.Service;

import com.example.BE.Entity.Customer;
import com.example.BE.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService implements CustomerServiceImp{

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCusById(Integer cusId) {
        return customerRepository.findById(cusId);
    }

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void updateCustomer(Integer cusId, Customer customer) {
        Customer customerExist = customerRepository.findById(cusId)
                .orElseThrow(() -> new IllegalStateException(
                        "Customer with" + cusId + "does not exist"
                ));

        Optional<Customer> customerOptional = customerRepository.findByEmail(customer.getEmail());

        if(customer.getEmail() != null
            && customer.getEmail().length()>0
            && !Objects.equals(customer.getEmail(), customerExist.getEmail())){
            if(customerOptional.isPresent())
            {
                throw new IllegalStateException("Email Taken");
            }
            customerRepository.saveAndFlush(customer);
        }

    }
}
