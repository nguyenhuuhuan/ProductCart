package com.example.BE.Service;

import com.example.BE.Entity.Products;
import com.example.BE.Repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductsService implements ProductsServiceImp{


    private final ProductsRepository productRepo;

    @Autowired
    public ProductsService(ProductsRepository productsRepo){
        this.productRepo = productsRepo;
    }

    @Override
    public List<Products> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public void addProduct(Products product) {
        Optional<Products> productsOptional = productRepo.getProByName(product.getNamePro());
        if(productsOptional.isPresent()){
            throw new IllegalStateException("Name Taken");
        }
        productRepo.saveAndFlush(product);
    }

    @Override
    public Optional<Products> getProductById(int proId) {
        return productRepo.findById(proId);
    }

    @Override
    public void deleteProduct(int proId) {
        boolean exist = productRepo.existsById(proId);
        if(!exist){
            throw new IllegalStateException("product with id" + proId + "does not exist");
        }
        productRepo.deleteById(proId);
    }

    @Transactional
    @Override
    public void updateProduct(int proId, Products product) {
        Products existPro = productRepo.findById(proId)
                .orElseThrow(() -> new IllegalStateException(
                        "Product with id" + proId + "does not exist"
                ));

        Optional<Products> productsOptional = productRepo.getProByName(product.getNamePro());

        if(product.getNamePro() != null
                && product.getNamePro().length() > 0
                && !Objects.equals(existPro.getNamePro(), product.getNamePro()))
        {
            if(productsOptional.isPresent())
            {
               throw new IllegalStateException("Name Taken");
            }
            existPro.setNamePro(product.getNamePro());
            productRepo.save(product);
        }
    }

    @Override
    public Products getProById(int proId) {
        return productRepo.getById(proId);
    }

    @Override
    public Page<Products> pageProducts(Pageable pageable) {
        return productRepo.findAll(pageable);
    }


}
