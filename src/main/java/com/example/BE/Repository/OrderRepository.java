package com.example.BE.Repository;

import com.example.BE.Entity.Order;
import com.example.BE.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, PagingAndSortingRepository<Order, Integer> {

    @Query("SELECT o FROM Order o WHERE o.customer.cusId = ?1")
    List<Order> getListOrderByCusId(Integer cusId);

}
