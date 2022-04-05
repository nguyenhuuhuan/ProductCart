package com.example.BE.Controller;

import com.example.BE.Entity.Products;
import com.example.BE.Service.ProductsService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/products")
@CrossOrigin("*")
public class ProductsController {

    private ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService){
        this.productsService = productsService;
    }

    @GetMapping
    public List<Products> getAllProducts(){
        return productsService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity addProduct(@RequestBody Products product){
        productsService.addProduct(product);
        System.out.println(product);
        return ResponseEntity.status(200).body(product);
    }

    @GetMapping(path = "/{productId}")
    public Optional<Products> getProById(@PathVariable("productId") Integer proId){
        return productsService.getProductById(proId);
    }

    @GetMapping(path = "/paging")
    public ResponseEntity Paginate(@RequestBody Products products, @RequestParam("p") Optional<Integer> page)
    {
        Pageable pageable = PageRequest.of(page.orElse(0),4);
        Page<Products> productsPage = productsService.pageProducts(pageable);
        return ResponseEntity.status(200).body(productsPage);
    }

    @GetMapping(path = "/paging/sortByName")
    public ResponseEntity PaginateSortByName(@RequestBody Products products,
                                             @RequestParam("p") Optional<Integer> page)
    {
        Pageable pageable = PageRequest.of(page.orElse(0),4, Sort.sort(products.getClass()).by("namePro").descending());
        Page<Products> productsPage = productsService.pageProducts(pageable);
        return ResponseEntity.status(200).body(productsPage);
    }

    @PutMapping(path = "/{productId}")
    public ResponseEntity updateProduct(@PathVariable("productId") Integer proId, @RequestBody Products product)
    {
        productsService.updateProduct(proId, product);
        return ResponseEntity.status(200).body(product);
    }

    @DeleteMapping(path = "/{productId}")
    public ResponseEntity deleteProduct(@PathVariable("productId") Integer proId){
        productsService.deleteProduct(proId);
        return ResponseEntity.status(200).body(getAllProducts());
    }

}
