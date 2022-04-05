package com.example.BE.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "tblCustomer",
        uniqueConstraints = {
        @UniqueConstraint(name = "customer_email_unique", columnNames = "email")
}

)
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cusId", nullable = false)
    private int cusId;

    @Column(nullable = false)
    private String fullName;

    private LocalDate dob;

    @Column(
            columnDefinition = "TEXT",
            nullable = false)
    private String email;

    private String phoneNumber;

}
