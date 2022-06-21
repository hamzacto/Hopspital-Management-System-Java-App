package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class ItemEntryFormId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long itemId;
    private Long entryFormId;
}
