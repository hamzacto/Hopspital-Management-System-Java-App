package com.example.demo.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.lang.annotation.Annotation;

@Entity
@Getter@Setter
@NoArgsConstructor
public class Dependence implements Serializable{
    @Id
    @GeneratedValue
    Long id;
    String agent;
    String date;
    String category;
    String objet;
    String amount;
    String description;

}
