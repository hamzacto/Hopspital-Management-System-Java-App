package com.example.demo.dto;

import com.example.demo.model.BilledProduct;
import com.example.demo.model.Patient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter@Setter
@NoArgsConstructor
public class InvoiceCreatingDto {
    Long id ;
    String billNumber;
    String admissionID;
    String patientCin;
    String patientName;
    String admissionDate;
    String dischargeDate;
    Long discountValue;
    Long total;
    String paymentMethod;
    String paymentStatus;
    List<BilledProduct> products;
    Patient patient;
}
