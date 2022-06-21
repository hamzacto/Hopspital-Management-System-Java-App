package com.example.demo.controller;


import com.example.demo.model.Dependence;
import com.example.demo.repository.DependenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class DependenceController {
    @Autowired
    DependenceRepository dependenceRepository;

    @PostMapping("Dependence/Add")
    public Dependence addDependence(@RequestBody Dependence dependence){
        Dependence myDependence = dependence;
        return this.dependenceRepository.save(myDependence);
    }
    @GetMapping("Dependence/All")
    public List<Dependence> getDependenceList(){
        return this.dependenceRepository.findAll();
    }
    @DeleteMapping("Dependence/Delete/{id}")
    public void deleteDependence(@PathVariable Long id){
        this.dependenceRepository.deleteById(id);
    }
}
