package com.example.demo.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter@Setter
@NoArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue
    private Long id;
    private String medNo;
    private String medName;
    private String medCategory;
    private String companyName;
    private String purchaseDate;
    private Long price;
    private String date;
    private Long stock;


}
