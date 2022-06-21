package com.example.demo.dto;

import com.example.demo.model.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EntryFormCreatingDto {
    private Long id;
    private String entrepot;
    private String category;
    private String date;
    private String infirmiers;
    private String description;
    private List<Item> stockLine;
}
