package com.example.demo.controller;

import com.example.demo.dto.EntryFormCreatingDto;
import com.example.demo.model.*;
import com.example.demo.repository.EntryFormRepository;
import com.example.demo.repository.ItemEntryFormRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.util.mostItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class StockController {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    EntryFormRepository entryFormRepository;

    @Autowired
    ItemEntryFormRepository itemEntryFormRepository;

    @PostMapping("Stock/Item/Add")
    public Item addItem(@RequestBody Item item){
        System.out.println(item.getDesignation());
        return this.itemRepository.save(item);
    }

    @GetMapping("Stock/Item/List")
    public List<Item> getItemList(){
        return this.itemRepository.findAll();
    }

    @PutMapping("Stock/Item/Update/{id}")
    public Item updateItem(@RequestBody Item item){
        return this.itemRepository.saveAndFlush(item);
    }
    @DeleteMapping("Stock/Item/Delete/{id}")
    public void deleteItem(@PathVariable Long id){
        this.itemRepository.deleteById(id);
    }

    @PostMapping("Stock/EntryForm/Add")
    public EntryForm addEntryForm(@RequestBody EntryFormCreatingDto entryFormDto){
        EntryForm entryForm = new EntryForm();
        entryForm.setEntrepot(entryFormDto.getEntrepot());
        entryForm.setDate(entryFormDto.getDate());
        entryForm.setDescription(entryFormDto.getDescription());
        entryForm.setInfirmiers(entryFormDto.getInfirmiers());
        entryForm.setCategory(entryFormDto.getCategory());
        entryForm.setType("ENTRY");
        entryForm= this.entryFormRepository.save(entryForm);
        EntryForm toSaveEntryForm = entryForm;
        entryFormDto.getStockLine().stream().forEach(
                (p -> {
                    ItemEntryForm itemEntryForm = new ItemEntryForm();
                    itemEntryForm.setEntryForm(toSaveEntryForm);
                    itemEntryForm.setItem(p);
                    itemEntryForm.setQuantity(p.getQuantit());
                    itemEntryForm.setType("ENTRY");
                    itemEntryForm.setDate(java.time.LocalDate.now());
                    Long newQuantity = this.itemRepository.getById(p.getId()).getQuantit() + p.getQuantit();
                    Item article = this.itemRepository.getById(p.getId());
                    article.setQuantit(newQuantity);
                    this.itemRepository.saveAndFlush(article);
                    itemEntryFormRepository.save(itemEntryForm);
                })
        );
        return this.entryFormRepository.save(entryForm);
    }

    @GetMapping("Stock/EntryForm/List")
    public List<EntryFormCreatingDto> getEntryFormList(){
        List<EntryFormCreatingDto> EntryFormCreatingDtoList =  this.entryFormRepository
                .getEntryFormList()
                .stream()
                .map(EntryForm->{
            EntryFormCreatingDto entryFormCreatingDto = new EntryFormCreatingDto();
            entryFormCreatingDto.setId(EntryForm.getId());
            entryFormCreatingDto.setEntrepot(EntryForm.getEntrepot());
            entryFormCreatingDto.setDate(EntryForm.getDate());
            entryFormCreatingDto.setCategory(EntryForm.getCategory());
            entryFormCreatingDto.setDescription(EntryForm.getDescription());
            entryFormCreatingDto.setInfirmiers(EntryForm.getInfirmiers());
//            List<Item> items = this.itemRepository.getItemsByFormId(EntryForm.getId());
//            entryFormCreatingDto.setStockLine(items);
            return entryFormCreatingDto;
        }).collect(Collectors.toList());
        return  EntryFormCreatingDtoList;
    }

    @PutMapping("Stock/EntryForm/Update/{id}")
    public EntryForm updateEntryForm(@RequestBody EntryForm entryForm){
        entryForm.setType("ENTRY");
        return this.entryFormRepository.saveAndFlush(entryForm);
    }

    @DeleteMapping("Stock/EntryForm/Delete/{id}")
    public void deleteEntryForm(@PathVariable Long id){
        this.entryFormRepository.deleteById(id);
    }

//  STOCK EXIT FORM

    @PostMapping("Stock/ExitForm/Add")
    public EntryForm addExitForm(@RequestBody EntryFormCreatingDto entryFormDto){
        EntryForm entryForm = new EntryForm();
        entryForm.setEntrepot(entryFormDto.getEntrepot());
        entryForm.setDate(entryFormDto.getDate());
        entryForm.setDescription(entryFormDto.getDescription());
        entryForm.setInfirmiers(entryFormDto.getInfirmiers());
        entryForm.setCategory(entryFormDto.getCategory());
        entryForm.setType("EXIT");
        entryForm= this.entryFormRepository.save(entryForm);
        EntryForm toSaveEntryForm = entryForm;
        entryFormDto.getStockLine().stream().forEach(
                (p -> {
                    ItemEntryForm itemEntryForm = new ItemEntryForm();
                    itemEntryForm.setEntryForm(toSaveEntryForm);
                    itemEntryForm.setItem(p);
                    itemEntryForm.setQuantity(p.getQuantit());
                    itemEntryForm.setType("EXIT");
                    itemEntryForm.setDate(java.time.LocalDate.now());
                    Long newQuantity = this.itemRepository.getById(p.getId()).getQuantit() - p.getQuantit();
                    Item article = this.itemRepository.getById(p.getId());
                    article.setQuantit(newQuantity);
                    this.itemRepository.saveAndFlush(article);
                    itemEntryFormRepository.save(itemEntryForm);
                })
        );
        return entryForm;
    }

    @GetMapping("Stock/ExitForm/List")
    public List<EntryFormCreatingDto> getExitFormList(){
        List<EntryFormCreatingDto> EntryFormCreatingDtoList =  this.entryFormRepository
                .getExitFormList()
                .stream()
                .map(EntryForm->{
            EntryFormCreatingDto entryFormCreatingDto = new EntryFormCreatingDto();
            entryFormCreatingDto.setId(EntryForm.getId());
            entryFormCreatingDto.setEntrepot(EntryForm.getEntrepot());
            entryFormCreatingDto.setDate(EntryForm.getDate());
            entryFormCreatingDto.setCategory(EntryForm.getCategory());
            entryFormCreatingDto.setDescription(EntryForm.getDescription());
            entryFormCreatingDto.setInfirmiers(EntryForm.getInfirmiers());
//            List<Item> items = this.itemRepository.getItemsByFormId(EntryForm.getId());
//            entryFormCreatingDto.setStockLine(items);
            return entryFormCreatingDto;
        }).collect(Collectors.toList());
        return  EntryFormCreatingDtoList;
    }

    @PutMapping("Stock/ExitForm/Update/{id}")
    public EntryForm updateExitForm(@RequestBody EntryForm entryForm){
        entryForm.setType("EXIT");
        return this.entryFormRepository.saveAndFlush(entryForm);
    }

    @DeleteMapping("Stock/ExitForm/Delete/{id}")
    public void deleteExitForm(@PathVariable Long id){
        this.entryFormRepository.deleteById(id);
    }

    @GetMapping("Stock/Item/OutOfStock")
    public List<Item> outOfStockItems(){
        List<Item> outOfStockItems ;

        outOfStockItems = this.itemRepository.findAll().stream()
                .filter(item -> {
                    return item.getSeuilAlerte()> item.getQuantit();
                }).collect(Collectors.toList());
        return outOfStockItems;
    }

    @GetMapping("Stock/Item/mostConsumed")
    public List<Item> mostConsumedItems(){
        List<ItemEntryForm> itemEntryFormList = this.itemEntryFormRepository.getMostConsumedItems();
        List<Item> mostConsumedItems = itemEntryFormList.stream().map(
                (item)->{
                    return this.itemRepository.findById(item.getItem().getId()).get();
                }
        ).collect(Collectors.toList());
        return  mostConsumedItems;
    }
    @GetMapping("Stock/Item/mostPurchased")
    public List<Item> mostPurchasedItems(){
        List<ItemEntryForm> itemEntryFormList = this.itemEntryFormRepository.getMostPurchasedItems();
        List<Item> mostConsumedItems = itemEntryFormList.stream().map(
                (item)->{
                    return this.itemRepository.findById(item.getItem().getId()).get();
                }
        ).collect(Collectors.toList());
        return  mostConsumedItems;
    }
    @GetMapping("Stock/Item/Latest")
    public List<ItemEntryForm> latestActivity(){
        return this.itemEntryFormRepository.getLatestActivity();
    }
    @GetMapping("Stock/Item/mostPurchased/Quantity")
    public List<Long> getMostPurchasedItems(){
        return this.itemEntryFormRepository.getBestPurchased();
    }
    @GetMapping("Stock/Item/mostConsumed/Quantity")
    public List<Long> getMostConsumedItems(){
        return this.itemEntryFormRepository.getBestPurchased();
    }

}