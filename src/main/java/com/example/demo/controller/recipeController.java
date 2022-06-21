package com.example.demo.controller;


import com.example.demo.dto.InvoiceCreatingDto;
import com.example.demo.dto.RecipeCreatingDto;
import com.example.demo.model.BilledProduct;
import com.example.demo.model.Patient;
import com.example.demo.model.Recipe;
import com.example.demo.repository.BilledProductRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RestController
public class recipeController {
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    BilledProductRepository billedProductRepository;

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @PostMapping("Recipe/Add")
    public Recipe addRecipe(@RequestBody RecipeCreatingDto recipeCreatingDto){
        Recipe recipe = new Recipe();
        AtomicReference<Long> totalAmount = new AtomicReference<>(Long.valueOf(0));
        Patient patient = this.patientRepository.findPatientByCin(recipeCreatingDto.getPatientCin());
        recipe.setPatient(patient);
        recipe.setAmount(recipeCreatingDto.getAmount());
        recipe.setPaymentMethod(recipeCreatingDto.getPaymentMethod());
        recipe.setDateOfSettlement(recipeCreatingDto.getDateOfSettlement());
        recipe.setAmount(recipeCreatingDto.getAmount());
        recipeCreatingDto.getProducts().stream().forEach(
                (p -> {
                    BilledProduct billedProduct = new BilledProduct();
                    billedProduct.setRecipe(recipe);
                    billedProduct.setName(p.getName());
                    billedProduct.setPrice(p.getPrice());
                    billedProduct.setQty(p.getQty());
                    this.billedProductRepository.saveAndFlush(billedProduct);
                })
        );

        recipeCreatingDto.getProducts().stream().forEach(
                (p -> {
                    totalAmount.updateAndGet(v -> v + p.getPrice());
                })
        );
        Long TA = totalAmount.get();
        recipe.setTotalAmount(TA);
        Long amountToPay = TA - recipeCreatingDto.getAmount();
        recipe.setAmountToPay(amountToPay);

        return this.recipeRepository.save(recipe);
    }
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @GetMapping("Recipe/All")
    public List<RecipeCreatingDto> getAllRecipes(){
        List<RecipeCreatingDto> recipeCreatingDtos =  this.recipeRepository.findAll().stream().map(recipe->{
            RecipeCreatingDto recipeCreatingDto = new RecipeCreatingDto();
            recipeCreatingDto.setId(recipe.getId());
            recipeCreatingDto.setPatientName(recipe.getPatient().getName());
            recipeCreatingDto.setDateOfSettlement(recipe.getDateOfSettlement());
            recipeCreatingDto.setAmount(recipe.getAmount());
            recipeCreatingDto.setTotalAmount(recipe.getTotalAmount());
            recipeCreatingDto.setPaymentMethod(recipe.getPaymentMethod());
            recipeCreatingDto.setAmountToPay(recipe.getAmountToPay());
            return recipeCreatingDto;
        }).collect(Collectors.toList());
        return recipeCreatingDtos;
    }

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @DeleteMapping("Recipe/Delete/{id}")
    public void deleteInvoice(@PathVariable("id") Long id){
        this.recipeRepository.deleteById(id);
    }
}
