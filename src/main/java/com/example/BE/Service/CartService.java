package com.example.BE.Service;

import com.example.BE.Config.PaymentConfig;
import com.example.BE.DTO.CartDTO;
import com.example.BE.Entity.Products;
import com.example.BE.Payment.PaymentRequest;
import com.example.BE.Repository.CustomerRepository;
import com.example.BE.Repository.ProductsRepository;
import com.example.BE.Security.Encoder;
import com.example.BE.utils.LogUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SessionScope
@Service
public class CartService implements CartServiceImp{

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PaymentService paymentService;

    Map<Integer, CartDTO> maps = new HashMap<>();

    @Override
    public void addCartItem(CartDTO cartDTO) {
        CartDTO item = maps.get(cartDTO.getProId());
        System.out.println(PaymentConfig.ENDPOINT);

        if(item == null){
            maps.put(cartDTO.getProId(), cartDTO);
        }else{
            Products products = productsRepository.getById(item.getProId());
            if(item.getQuantity() > products.getQuantity()){
                throw new IllegalStateException("Qua so luong");
            }else {
                item.setQuantity(item.getQuantity()+1);
            }
        }
    }

    @Override
    public void removeCartItem(int proId) {
        maps.remove(proId);
    }

    @Override
    public CartDTO updateCartItem(int proId, int quantity) {
        CartDTO cartDTO = maps.get(proId);
        cartDTO.setQuantity(quantity);
        return cartDTO;
    }

    @Override
    public Collection<CartDTO> getAllCartItem() {
        return maps.values();
    }

    @Override
    public int getCount() {
        return maps.values().size();
    }

    @Override
    public double getTotalMoney() {
        return maps.values().stream()
                .mapToDouble(item -> (double) item.getQuantity() * item.getPrice())
                .sum();
    }

    @Override
    public void removeAll() {
        maps.clear();
    }

    private CartService cartService;

    private Encoder encoder;


}
