package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String cat;
    private String name;
    private String purchasedDate;
    private String catSuivie;
    private String designation;
    private String unit;
    private String priceHt;
    private String type;
    private Long tva;
    private Long seuilAlerte;
    private Long quantitMax;
    private Long quantit;

//    @ManyToOne(cascade = CascadeType.ALL )
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JoinColumn(name = "entry_form_id")
//    private EntryForm entryForm;

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private Set<ItemEntryForm> itemEntryForms = new HashSet<>();
}
