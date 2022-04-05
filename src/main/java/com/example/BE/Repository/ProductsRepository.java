package com.example.BE.Repository;

import com.example.BE.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer>, PagingAndSortingRepository<Products, Integer> {

    @Query("SELECT p FROM Products p WHERE p.namePro = ?1")
    Optional<Products> getProByName(String name);

}
