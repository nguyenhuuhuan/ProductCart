package com.example.BE.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tblProducts")
public class Products implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId", nullable = false)
    private int productId;

    @Column(name = "namePro")
    private String namePro;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "quantity")
    private int quantity;

}
