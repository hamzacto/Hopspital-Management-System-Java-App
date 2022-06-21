package com.example.demo.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue
    Long id;
    String dateOfSettlement;
    String paymentMethod;
    Long amount;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    Patient patient;
    Long totalAmount;
    Long amountToPay;
}
