package com.example.demo.dto;

import com.example.demo.model.BilledProduct;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class RecipeCreatingDto {
    Long id;
    String patientCin;
    String patientName;
    String dateOfSettlement;
    String paymentMethod;
    Long amount;
    List<BilledProduct> products;
    Long totalAmount;
    Long amountToPay;
}
