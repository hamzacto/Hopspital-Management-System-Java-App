package com.example.demo.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class ItemEntryForm {

    @EmbeddedId
    private ItemEntryFormId id = new ItemEntryFormId();

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @MapsId("entryFormId")
    private EntryForm entryForm;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @MapsId("itemId")
    private Item item;

    private Long quantity;
    private String type;
    private LocalDate date;
}
