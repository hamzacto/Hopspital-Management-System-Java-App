package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter@Setter
@NoArgsConstructor

public class BilledProduct implements Serializable {
    @Id
    @Column(name="Billed_Product_id")
    @GeneratedValue
    Long id;
    String name;
    Long price;
    Long qty;
    @ManyToOne(cascade = CascadeType.ALL )
    @OnDelete(action = OnDeleteAction.CASCADE)

    @JoinColumn(name = "invoice_id")
    Invoice invoice;


    @ManyToOne(cascade = CascadeType.ALL )
    @OnDelete(action = OnDeleteAction.CASCADE)

    @JoinColumn(name = "recipe_id")
    Recipe recipe;
}
