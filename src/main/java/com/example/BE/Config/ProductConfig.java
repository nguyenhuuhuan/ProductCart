package com.example.BE.Config;

import com.example.BE.Entity.Customer;
import com.example.BE.Entity.Order;
import com.example.BE.Entity.Products;
import com.example.BE.Repository.CustomerRepository;
import com.example.BE.Repository.OrderRepository;
import com.example.BE.Repository.ProductsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.List;

@Configuration
public class ProductConfig {
//    @Bean
//    CommandLineRunner commandLineRunner(ProductsRepository productsRepository,
//                                        OrderRepository orderRepository,
//                                        CustomerRepository customerRepository ){
//        return args -> {
//            Products Nemeziz = new Products(
//                    1,
//                    "Giay Adidas Nemeziz",
//                    1800000,
//                    13
//            );
//            Products Predator = new Products(
//                    2,
//                    "Giay Adidas Predator",
//                    2000000,
//                    20
//            );
//            Products Phantom = new Products(
//                    3,
//                    "Giay Nike Phantom",
//                    2000000,
//                    3
//            );
//
//
////            Customer customer1 = new Customer(
////                    1,
////                    "Nguyen Huu Huan",
////                    LocalDate.of(1999, Month.MARCH,19),
////                    "huuhuan19031999@gmail.com",
////                    "0827017761",
////                    null
////            );
//            Customer customer1 = new Customer(
//                    1,
//                    "Nguyen Van Tai",
//                    LocalDate.of(1994, Month.JANUARY,4),
//                    "vantai@gmail.com",
//                    "0827147661"
//            );
//
//            Order order1 = new Order(
//                    1,
//                    2000000,
//                    LocalDateTime.of(2021, Month.MARCH,19, 15, 45 ),
//                    "pending",
//                    customer1
//                    );
//
//            productsRepository.saveAll(List.of(Nemeziz, Predator, Phantom));
//            orderRepository.saveAll(List.of(order1));
//            customerRepository.saveAll(List.of(customer1));
//
//        };
//    }

}
