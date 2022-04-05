package com.example.BE.Controller;

import com.example.BE.Config.PaymentConfig;
import com.example.BE.DTO.CartDTO;
import com.example.BE.DTO.PaymentDTO;
import com.example.BE.Entity.Products;
import com.example.BE.Payment.PaymentRequest;
import com.example.BE.Security.Encoder;
import com.example.BE.Service.PaymentService;
import com.example.BE.Service.CartService;
import com.example.BE.Service.ProductsService;
import com.example.BE.utils.LogUtils;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    ProductsService productsService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping("/")
    public ResponseEntity getAllCartItem(){
        try {
            cartService.getAllCartItem();
            cartService.getTotalMoney();
            System.out.println(cartService.getTotalMoney());
            return ResponseEntity.status(200).body(cartService.getAllCartItem());
        }catch (Exception e){
            e.getMessage();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(path = "/add/{productId}")
    public ResponseEntity addCartItem(@PathVariable(name = "productId") Integer proId){
        Optional<Products> productsOptional = productsService.getProductById(proId);
        CartDTO cartDTO = new CartDTO();
        if(productsOptional != null){
            cartDTO.setProId(productsOptional.get().getProductId());
            cartDTO.setNamePro(productsOptional.get().getNamePro());
            cartDTO.setPrice(productsOptional.get().getPrice());
            cartDTO.setQuantity(1);
            productsOptional.get().setQuantity(productsOptional.get().getQuantity()-cartDTO.getQuantity());
            cartService.addCartItem(cartDTO);
        }
        System.out.println(cartDTO);

        return ResponseEntity.status(200).body(cartDTO);
    }

    @DeleteMapping("/{proId}")
    public ResponseEntity removeCartItem(@PathVariable(name = "proId") Integer proId){
        cartService.removeCartItem(proId);
        return ResponseEntity.status(200).body("Success");
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestParam("id") Integer id, @RequestParam("") Integer quantity)
    {
        cartService.updateCartItem(id, quantity);
        return ResponseEntity.status(200).body("success");
    }

    @DeleteMapping
    public ResponseEntity removeAllCartItem(){
        cartService.removeAll();
        return ResponseEntity.status(200).body("success");
    }

    @GetMapping("/pay/momo")
    public String Payment() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
//        paymentService.Payment();
        return paymentService.returnUrl(paymentService.Payment());
    }
}
