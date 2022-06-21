package com.example.demo.controller;

import com.example.demo.repository.DependenceRepository;
import com.example.demo.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class CaseController {
    @Autowired
    public DependenceRepository dependenceRepository;

    @Autowired
    public RecipeRepository recipeRepository;
    @GetMapping("Case/Expenses")
    public Long getTotalExpenses(){
        return this.dependenceRepository.getTotalExpenses();
    }
    @GetMapping("Case/Income")
    public Long getTotalIncome(){
        return this.recipeRepository.getTotalIncome();
    }
    @GetMapping("Case/Income/Paid")
    public Long getPaidIncome(){
        return this.recipeRepository.getTotalPaid();
    }
    @GetMapping("Case/Income/Unpaid")
    public Long getUnPaidIncome(){
        return this.recipeRepository.getTotalUnpaid();
    }
    @GetMapping("Case/Income/Cash")
    public Long getTotalCash(){
        return this.recipeRepository.getTotalCash();
    }
    @GetMapping("Case/Income/Cheque")
    public Long getTotalCheque(){
        return this.recipeRepository.getTotalCheque();
    }


}
