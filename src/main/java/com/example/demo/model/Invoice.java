package com.example.demo.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter@Setter
@NoArgsConstructor
@OnDelete(action = OnDeleteAction.CASCADE)
public class Invoice {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
    private String billNo;
    private String admissionId;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    private String admitDate;
    private String dischargeDate;
    private Long discount;
    private Long totalAmount;
    private String paymentMethod;
    private String paymentStatus;





}
