package com.example.BE.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "tblOrders",
        uniqueConstraints = {
                @UniqueConstraint(name = "order_cusId_unique", columnNames = "cusId")
        }
)
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int orderId;

    @Column(nullable = false)
    private double totalMoney;

    @Column(nullable = false)
    private LocalDateTime date;

    private String status;

    @ManyToOne
    @JoinColumn(name = "cusId")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    @JsonIgnore
    private Customer customer;

}
