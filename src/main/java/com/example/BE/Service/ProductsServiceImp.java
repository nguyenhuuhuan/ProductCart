package com.example.BE.Service;

import com.example.BE.Entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProductsServiceImp {
    List<Products> getAllProducts();

    void addProduct(Products product);

    Optional<Products> getProductById(int proId);

    void deleteProduct(int proId);

    void updateProduct(int proId, Products product);

    Products getProById(int proId);

    Page<Products> pageProducts(Pageable pageable);
}