package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EntryForm implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String entrepot;
    private String date;
    private String category;
    private String infirmiers;
    private String description;
    private String type;

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private Set<ItemEntryForm> itemEntryForms = new HashSet<>();
}
